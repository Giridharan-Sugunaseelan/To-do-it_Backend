package com.gl.todo_app.security.service;

import com.gl.todo_app.dto.*;
import com.gl.todo_app.entities.PasswordResetToken;
import com.gl.todo_app.entities.User;
import com.gl.todo_app.exception.ExistingUserException;
import com.gl.todo_app.exception.UserNotFoundException;
import com.gl.todo_app.repository.PasswordResetRepository;
import com.gl.todo_app.service.ProjectService;
import com.gl.todo_app.util.PasswordResetUtil;
import com.gl.todo_app.util.Principal;
import com.gl.todo_app.util.RegisterMapper;
import com.gl.todo_app.repository.UserRepository;
import com.gl.todo_app.security.configuration.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;

    private PasswordEncoder encoder;

    private JwtTokenProvider tokenProvider;

    private AuthenticationManager authenticationManager;

    private ProjectService projectService;

    private EmailService emailService;

    private PasswordResetUtil passwordTokenUtil;

    private PasswordResetRepository passwordResetRepository;

    public String registerUser(RegisterDto dto){
        String email = dto.getEmail();
        if(this.userRepository.existsByEmail(email)){
           throw new ExistingUserException("User with the email id exists!");
        }
        User user = RegisterMapper.dtoToUser(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        User saved = this.userRepository.save(user);
        this.projectService.homeProject(saved);
        User byEmail = this.userRepository.findByEmail(email);
        System.out.println(byEmail);
        System.out.println("Projects: " + byEmail.getProjects());
        return "User Created Successfully";
    }

    public JwtTokenDto loginUser(LoginDto dto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return new JwtTokenDto(token);
    }

    public Boolean checkPassword(ResetPasswordDto password){
        String email = Principal.getAuthentication().getName();
        String existingPassword = this.userRepository.findByEmail(email).getPassword();
        return this.encoder.matches(password.getPassword(), existingPassword);
    }

    public String resetPassword(ResetPasswordDto password){
        String email = Principal.getAuthentication().getName();
        User user = this.userRepository.findByEmail(email);
        user.setPassword(this.encoder.encode(password.getPassword()));
        this.userRepository.save(user);
        return "Password Changed Successfully";
    }

    public String sendResetLink(String email){
        User user = this.userRepository.findByEmail(email);
        if(user == null){
            throw new UserNotFoundException("No account is registered with the " + email);
        }
        PasswordResetToken token = this.passwordTokenUtil.createToken(user);
        String resetUrl = "https://todoiit.netlify.app/resetPassword?token=" + token.getToken();
        String mailBody = "Hi " + user.getFirstName() + ", Click the link to reset password - " + resetUrl;
        String mailSubject = "Todo-Application: Reset Password";
        emailService.sendResetPasswordMail(email, mailSubject, mailBody);
        return "Password reset link sent to " + email;
    }

    public String resetPassword(String token, ResetPasswordDto dto){
        PasswordResetToken resetToken = this.passwordTokenUtil.getByToken(token);
        if(resetToken == null || resetToken.getExpireAt().isBefore(LocalDateTime.now())){
            return "The link is invalid or expired";
        }
        String password = dto.getPassword();
        User user = resetToken.getUser();
        user.setPassword(this.encoder.encode(password));
        this.userRepository.save(user);
        this.passwordTokenUtil.deleteToken(resetToken);
        return "Password Changed Successfully";
    }
}

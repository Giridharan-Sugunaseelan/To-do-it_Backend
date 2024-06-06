package com.gl.todo_app.security.controller;

import com.gl.todo_app.dto.*;
import com.gl.todo_app.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    public AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto dto){
        String registerStatus = this.service.registerUser(dto);
        return ResponseEntity.ok(registerStatus);
    }

    @GetMapping("/alive")
    public ResponseEntity<Boolean> keepAlive(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> loginUser(@RequestBody LoginDto loginDto) {
        JwtTokenDto tokenDto = this.service.loginUser(loginDto);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Boolean> checkPassword(@RequestBody ResetPasswordDto password){
        Boolean status = this.service.checkPassword(password);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto password){
        String status = this.service.resetPassword(password);
        System.out.println("Password Changed");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/forgotPassword/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email){
        String status = this.service.sendResetLink(email);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/resetPassword/{token}")
    public ResponseEntity<String> resetpassword(@PathVariable String token, @RequestBody ResetPasswordDto passwordDto){
        String status = this.service.resetPassword(token, passwordDto);
        System.out.println(status);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}

package com.gl.todo_app.util;

import com.gl.todo_app.entities.PasswordResetToken;
import com.gl.todo_app.entities.User;
import com.gl.todo_app.repository.PasswordResetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
public class PasswordResetUtil {

    private PasswordResetRepository resetRepository;

    public PasswordResetToken createToken(User user) {
        PasswordResetToken existingToken = this.resetRepository.findByUser(user);
        if (existingToken != null) {
            this.resetRepository.delete(existingToken);
        }
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setTimeStamp(LocalDateTime.now());
        resetToken.setExpireAt(LocalDateTime.now().plusMinutes(15));
        resetToken.setUser(user);
        return this.resetRepository.save(resetToken);
    }

    public PasswordResetToken getByToken(String token) {
        return this.resetRepository.findByToken(token);
    }

    public void deleteToken(PasswordResetToken token) {
        this.resetRepository.delete(token);
    }

}

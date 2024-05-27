package com.gl.todo_app.repository;

import com.gl.todo_app.entities.PasswordResetToken;
import com.gl.todo_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByUser(User user);

    PasswordResetToken findByToken(String token);
}

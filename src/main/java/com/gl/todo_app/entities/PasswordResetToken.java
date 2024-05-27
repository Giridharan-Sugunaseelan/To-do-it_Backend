package com.gl.todo_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime timeStamp;
    private LocalDateTime expireAt;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    private User user;

    public PasswordResetToken(String token, LocalDateTime timeStamp, LocalDateTime expireAt, User user) {
        this.token = token;
        this.timeStamp = timeStamp;
        this.expireAt = expireAt;
        this.user = user;
    }
}

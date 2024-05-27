package com.gl.todo_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timeStamp;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime expireAt;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public PasswordResetToken(String token, LocalDateTime timeStamp, LocalDateTime expireAt, User user) {
        this.token = token;
        this.timeStamp = timeStamp;
        this.expireAt = expireAt;
        this.user = user;
    }
}

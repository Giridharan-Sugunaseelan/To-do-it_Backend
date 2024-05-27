package com.gl.todo_app.dto;

import lombok.Data;

@Data
public class JwtTokenDto {
    private final String tokenType = "Bearer ";
    private String token;

    public JwtTokenDto(String token){
        this.token = token;
    }
}

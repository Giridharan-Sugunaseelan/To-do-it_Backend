package com.gl.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    public Long id = 0L;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String role;
}

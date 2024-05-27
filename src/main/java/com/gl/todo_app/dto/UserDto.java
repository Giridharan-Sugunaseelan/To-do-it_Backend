package com.gl.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String role;
}

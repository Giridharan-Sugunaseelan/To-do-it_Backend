package com.gl.todo_app.util;

import com.gl.todo_app.dto.RegisterDto;
import com.gl.todo_app.entities.User;

public class RegisterMapper {
    public static User dtoToUser(RegisterDto dto){
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole("user");
        return user;
    }

    public static RegisterDto userToDto(User user){
        RegisterDto dto = new RegisterDto();
        dto.setId(user.getUser_id());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }
}

package com.gl.todo_app.util;

import com.gl.todo_app.dto.UserDto;
import com.gl.todo_app.entities.User;

public class UserMapper {
    public static UserDto userToDto(User user){
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }
}

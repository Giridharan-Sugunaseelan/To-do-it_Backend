package com.gl.todo_app.service;

import com.gl.todo_app.dto.UserDto;
import com.gl.todo_app.entities.User;
import com.gl.todo_app.repository.UserRepository;
import com.gl.todo_app.util.Principal;
import com.gl.todo_app.util.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public UserDto getUser(){
        String email = Principal.getAuthentication().getName();
        User user = this.userRepository.findByEmail(email);
        return UserMapper.userToDto(user);
    }

}

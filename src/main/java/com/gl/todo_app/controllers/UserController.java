package com.gl.todo_app.controllers;

import com.gl.todo_app.dto.UserDto;
import com.gl.todo_app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUser(){
        UserDto user = this.userService.getUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

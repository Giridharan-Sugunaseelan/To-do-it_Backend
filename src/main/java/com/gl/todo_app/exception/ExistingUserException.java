package com.gl.todo_app.exception;

public class ExistingUserException extends RuntimeException{
    public ExistingUserException(String message){
        super(message);
    }
}

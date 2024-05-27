package com.gl.todo_app.exception;

public class SectionNotFoundException extends RuntimeException{
    public SectionNotFoundException(String message){
        super(message);
    }
}

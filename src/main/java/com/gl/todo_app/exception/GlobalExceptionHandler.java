package com.gl.todo_app.exception;

import com.gl.todo_app.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ExistingUserException.class, UserNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Error> exceptionHandler(WebRequest request, Exception exception){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(exception instanceof ExistingUserException || exception instanceof BadCredentialsException){
            status = HttpStatus.BAD_REQUEST;
        }
        if(
                exception instanceof UserNotFoundException || exception instanceof ProjectNotFoundException ||
                exception instanceof TaskNotFoundException || exception instanceof SectionNotFoundException
        ){
            status = HttpStatus.NOT_FOUND;
        }
        Error error = new Error(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, status);
    }
}

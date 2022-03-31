package com.moviehub.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {

    @ExceptionHandler(value = {com.moviehub.movieservice.exception.ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(com.moviehub.movieservice.exception.ResourceNotFoundException e){
        com.moviehub.movieservice.exception.Exception exception = new com.moviehub.movieservice.exception.Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}

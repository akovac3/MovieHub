package com.moviehub.watchlistservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {

    @ExceptionHandler(value = {com.moviehub.watchlistservice.exceptions.ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(com.moviehub.watchlistservice.exceptions.ResourceNotFoundException e){
        com.moviehub.watchlistservice.exceptions.Exception exception = new com.moviehub.watchlistservice.exceptions.Exception(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}

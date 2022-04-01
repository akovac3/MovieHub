package com.moviehub.watchlistservice.exceptions;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class ExceptionResponse {
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private Integer status;
    private String message;
    private String path;
    private String error;
}

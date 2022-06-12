package com.moviehub.movieservice.rabbitMq;

import com.moviehub.movieservice.model.Movie;

public class RabbitMQStatus {
    private int status;
    private Long movie;

    public RabbitMQStatus() {
    }

    public RabbitMQStatus(int status, Long id) {
        this.status = status;
        this.movie = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getMovie() {
        return movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }
}
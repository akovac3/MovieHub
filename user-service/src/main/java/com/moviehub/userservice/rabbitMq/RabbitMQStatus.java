package com.moviehub.userservice.rabbitMq;

public class RabbitMQStatus {
    private int status;
    private Long watchlistId;
    private String userId;

    public RabbitMQStatus() {
    }

    public RabbitMQStatus(int status, Long id, String userId) {
        this.status = status;
        this.watchlistId = id;
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(Long watchlistId) {
        this.watchlistId = watchlistId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
package com.moviehub.watchlistservice.POJO.Watchlist;

public record AddWatchlistRequest(
    String name,
    Long userId
)
{}

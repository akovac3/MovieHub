package com.moviehub.watchlistservice.POJO.Watchlist;

public record AddMovieToWatchlistRequest(
        Long movieId,
        String name,
        String textDescription,
        Double grade
) {
}

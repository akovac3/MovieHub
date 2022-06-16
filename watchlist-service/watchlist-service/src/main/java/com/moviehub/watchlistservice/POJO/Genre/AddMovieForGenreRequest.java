package com.moviehub.watchlistservice.POJO.Genre;

public record AddMovieForGenreRequest(
        Long movieId,
        String name,
        String textDescription,
        Float grade
) {
}

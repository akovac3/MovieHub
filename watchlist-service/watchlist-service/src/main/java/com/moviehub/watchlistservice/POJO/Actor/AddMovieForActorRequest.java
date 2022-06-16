package com.moviehub.watchlistservice.POJO.Actor;

public record AddMovieForActorRequest(
        Long movieId,
        String name,
        String textDescription,
        Float grade
){}

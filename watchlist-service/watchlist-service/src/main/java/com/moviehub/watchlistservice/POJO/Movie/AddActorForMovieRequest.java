package com.moviehub.watchlistservice.POJO.Movie;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public record AddActorForMovieRequest(
        Long actorId,
        String name,
        String lastname

) {
}

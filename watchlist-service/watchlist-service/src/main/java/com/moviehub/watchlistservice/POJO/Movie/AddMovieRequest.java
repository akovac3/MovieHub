package com.moviehub.watchlistservice.POJO.Movie;

import javax.persistence.Column;

public record AddMovieRequest(
        String name,
        Double grade,
        String textDescription
) {
}

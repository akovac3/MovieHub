package com.moviehub.watchlistservice.repository;

import com.moviehub.watchlistservice.entity.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}

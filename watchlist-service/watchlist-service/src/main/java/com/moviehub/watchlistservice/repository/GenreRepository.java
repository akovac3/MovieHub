package com.moviehub.watchlistservice.repository;

import com.moviehub.watchlistservice.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

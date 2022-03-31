package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Genre.AddGenreRequest;
import com.moviehub.watchlistservice.POJO.Movie.AddMovieRequest;
import com.moviehub.watchlistservice.entity.Genre;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.repository.GenreRepository;
import com.moviehub.watchlistservice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/genre/all")
    public ResponseEntity<Iterable<Genre>> getAll() {
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(genreService.findById(id));
    }

    @PostMapping("/genre/add")
    public ResponseEntity<Genre> addNewGenre(@RequestBody AddGenreRequest request) {
        return ResponseEntity.ok(genreService.add(request));
    }
}

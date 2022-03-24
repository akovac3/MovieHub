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
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Genre>> getAll() {
        return ResponseEntity.ok(genreService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(genreService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewGenre(@RequestBody AddGenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.name());
        genreService.save(genre);
        return ResponseEntity.ok("Genre added successfully.");
    }
}

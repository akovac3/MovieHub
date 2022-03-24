package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Movie.AddMovieRequest;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewMovie(@RequestBody AddMovieRequest request) {
        Movie movie = new Movie();
        movie.setName(request.name());
        movie.setGrade(request.grade());
        movie.setTextDescription(request.textDescription());
        movieService.save(movie);
        return ResponseEntity.ok("Movie added successfully.");
    }
}

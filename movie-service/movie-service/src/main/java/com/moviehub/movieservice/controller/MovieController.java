package com.moviehub.movieservice.controller;

import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Movie;
import com.moviehub.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Movie>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie newMovie = movieService.findById(id);
        return ResponseEntity.ok().body(newMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id,@Valid @RequestBody Movie movieDetails) {
        Movie updateMovie = movieService.findById(id);
        updateMovie.setTitle(movieDetails.getTitle());
        updateMovie.setGrade(movieDetails.getGrade());
        updateMovie.setDescription(movieDetails.getDescription());
        updateMovie.setYear(movieDetails.getYear());
        updateMovie.setActors(movieDetails.getActors());
        updateMovie.setGenres(movieDetails.getGenres());

        movieService.save(updateMovie);

        return  ResponseEntity.ok(updateMovie);
    }

    @PostMapping("/")
    public ResponseEntity<Movie> addNewMovie(@Valid @RequestBody Movie movie) {
        return new ResponseEntity<Movie>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id){
        movieService.remove(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}

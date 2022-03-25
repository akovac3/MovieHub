package com.moviehub.movieservice.controllers;

import com.moviehub.movieservice.models.Actor;
import com.moviehub.movieservice.models.Movie;
import com.moviehub.movieservice.services.ActorService;
import com.moviehub.movieservice.services.MovieService;
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

    @GetMapping("/all")
    public Iterable<Movie> getAll() {
        return movieService.getAll();
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

    @PostMapping("/add")
    public ResponseEntity<Movie> addNewMovie(@Valid @RequestBody Movie movie) {
        Movie newMovie = movieService.save(movie);
        return new ResponseEntity<Movie>(newMovie, HttpStatus.CREATED);
    }

   /* @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable long id){
        movieService.remove(id);
        return ResponseEntity.ok().build();
    }*/
}

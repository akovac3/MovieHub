package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Movie.AddActorForMovieRequest;
import com.moviehub.watchlistservice.POJO.Movie.AddMovieRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/all")
    public ResponseEntity<Iterable<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping("/movie/add")
    public ResponseEntity<Movie> addNewMovie(@RequestBody AddMovieRequest request) {
        return ResponseEntity.ok(movieService.add(request));
    }

//    @PostMapping("/movie/add/actor")
//    public ResponseEntity<Movie> addActorForMovie(@RequestBody AddActorForMovieRequest request) {
//
//        return ResponseEntity.ok(movieService.addActorForMovie(request));
//    }

    @PostMapping("/movie/{movieId}/actor")
    public ResponseEntity<Actor> addActorForMovie(@PathVariable(value = "movieId")Long movieId, @RequestBody AddActorForMovieRequest request) {
        Actor actor =  movieService.addActorForMovie(movieId, request);
        return new ResponseEntity<Actor>(actor, HttpStatus.CREATED);
    }
}

package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Actor.AddActorRequest;
import com.moviehub.watchlistservice.POJO.Actor.AddMovieRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.repository.ActorRepostory;
import com.moviehub.watchlistservice.service.ActorService;
import com.moviehub.watchlistservice.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    ActorService actorService;
    @Autowired
    MovieService movieService;


    @GetMapping("/all")
    @Operation(summary = "Get all actors", responses = {
            @ApiResponse(description = "Get all actors success", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Actor.class))),
            @ApiResponse(description = "Get all actors fail", responseCode = "403", content = @Content)
    })
    public ResponseEntity<Iterable<Actor>> getAll() {
        return ResponseEntity.ok(actorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(actorService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewActor(@RequestBody AddActorRequest request) {
        Actor actor = new Actor();
        actor.setName(request.name());
        actor.setLastname(request.lastname());
        actorService.save(actor);
        return ResponseEntity.ok("Actor added successfully.");
    }

    @PostMapping("/add/movie")
    public ResponseEntity<String> addMovieForActor(@RequestBody AddMovieRequest request) {
        Movie movie = movieService.findById(request.movieId());
        Actor actor = actorService.findById(request.actorId());
        actor.getMovies().add(movie);
        return ResponseEntity.ok("Movie for actor added successfully");
    }
}

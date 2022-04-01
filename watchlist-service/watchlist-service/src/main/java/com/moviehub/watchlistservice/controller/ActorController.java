package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Actor.AddActorRequest;
import com.moviehub.watchlistservice.POJO.Actor.AddMovieForActorRequest;
import com.moviehub.watchlistservice.POJO.Movie.AddActorForMovieRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ActorController {

    @Autowired
    ActorService actorService;
    @GetMapping("/actor/all")
    /*@Operation(summary = "Get all actors", responses = {
            @ApiResponse(description = "Get all actors success", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Actor.class))),
            @ApiResponse(description = "Get all actors fail", responseCode = "403", content = @Content)
    })*/
    public ResponseEntity<Iterable<Actor>> getAll() {
        return ResponseEntity.ok(actorService.getAll());
    }

    @GetMapping("/actor/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(actorService.findById(id));
    }

    @PostMapping("/actor/add")
    public ResponseEntity<Actor> addNewActor(@RequestBody AddActorRequest request) {
        return ResponseEntity.ok(actorService.add(request));
    }

    @PostMapping("/actor/{actorId}/movie")
    public ResponseEntity<Movie> addActorForMovie(@PathVariable(value = "actorId")Long actorId, @RequestBody AddMovieForActorRequest request) {
        Movie movie =  actorService.addMovieForActor(actorId, request);
        return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
    }
}

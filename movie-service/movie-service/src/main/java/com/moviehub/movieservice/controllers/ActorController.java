package com.moviehub.movieservice.controllers;

import com.moviehub.movieservice.models.Actor;
import com.moviehub.movieservice.services.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    ActorService actorService;

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
        Actor newActor = actorService.findById(id);
        return ResponseEntity.ok().body(newActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable long id,@Valid @RequestBody Actor actorDetails) {
        Actor updateActor = actorService.findById(id);
        updateActor.setFirstName(actorDetails.getFirstName());
        updateActor.setLastName(actorDetails.getLastName());
        updateActor.setMovies(actorDetails.getMovies());

        actorService.save(updateActor);

        return  ResponseEntity.ok(updateActor);
    }

    @PostMapping("/add")
    public ResponseEntity<Actor> addNewActor(@Valid @RequestBody Actor actor) {
        Actor newActor = actorService.save(actor);
        return new ResponseEntity<Actor>(newActor, HttpStatus.CREATED);
    }

   /* @DeleteMapping("/{id}")
    public ResponseEntity deleteActor(@PathVariable long id){
        actorService.remove(id);
        return ResponseEntity.ok().build();
    }*/

}

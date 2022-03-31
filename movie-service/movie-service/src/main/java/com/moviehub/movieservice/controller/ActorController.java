package com.moviehub.movieservice.controller;

import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.service.ActorService;
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

    @GetMapping("/")
    public ResponseEntity<Iterable<Actor>> getAll() {
        return ResponseEntity.ok(actorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") Long id) {
        Actor newActor = actorService.findById(id);
        return ResponseEntity.ok().body(newActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id,@Valid @RequestBody Actor actorDetails) {
        Actor updateActor = actorService.findById(id);
        updateActor.setFirstName(actorDetails.getFirstName());
        updateActor.setLastName(actorDetails.getLastName());
        updateActor.setMovies(actorDetails.getMovies());

        actorService.save(updateActor);

        return  ResponseEntity.ok(updateActor);
    }

    @PostMapping("/")
    public ResponseEntity<Actor> addNewActor(@Valid @RequestBody Actor actor) {
        return new ResponseEntity<Actor>(actorService.addActor(actor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id){
        actorService.remove(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}

package com.moviehub.watchlistservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/actor")
public class ActorController {

    @Autowired
    ActorService actorService;

    ObjectMapper objectMapper = new ObjectMapper();

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
    public ResponseEntity<String> updateActor(@PathVariable Long id,@Valid @RequestBody Actor actorDetails) {
        Actor updateActor = actorService.findById(id);
        updateActor.setFirstName(actorDetails.getFirstName());
        updateActor.setLastName(actorDetails.getLastName());

        actorService.save(updateActor);

        return  new ResponseEntity<>("Actor with id = " + id +" successfully updated!", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addNewActor(@Valid @RequestBody Actor actor) {
        actorService.addActor(actor);
        return new ResponseEntity<>("Actor successfully added!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Long id){
        actorService.remove(id);
        return new ResponseEntity<>("Actor successfully deleted!", HttpStatus.OK);
    }

}

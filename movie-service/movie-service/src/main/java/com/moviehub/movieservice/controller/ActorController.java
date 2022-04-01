package com.moviehub.movieservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Genre;
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
        updateActor.setMovies(actorDetails.getMovies());

        actorService.save(updateActor);

        return  new ResponseEntity<>("Actor with id = " + id +" successfully updated!", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addNewActor(@Valid @RequestBody Actor actor) {
        actorService.addActor(actor);
        return new ResponseEntity<>("Actor successfully added!", HttpStatus.CREATED);
    }

    private Actor applyPatchToActor(
            JsonPatch patch, Actor targetActor) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetActor, JsonNode.class));
        return objectMapper.treeToValue(patched, Actor.class);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity updateActor(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            Actor actor = actorService.findById(id);
            Actor actorPatched = applyPatchToActor(patch, actor);
            actorService.save(actorPatched);
            return new ResponseEntity<>("Actor with id = " + id + " successfully updated!", HttpStatus.OK);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Long id){
        actorService.remove(id);
        return new ResponseEntity<>("Actor successfully deleted!", HttpStatus.OK);
    }

}

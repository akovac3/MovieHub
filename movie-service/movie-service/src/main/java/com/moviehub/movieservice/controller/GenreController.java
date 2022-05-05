package com.moviehub.movieservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Genre;
import com.moviehub.movieservice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    GenreService genreService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/")
    public ResponseEntity<Iterable<Genre>> getAll() {
        return ResponseEntity.ok(genreService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        Genre newGenre = genreService.findById(id);
        return ResponseEntity.ok().body(newGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGenre(@PathVariable Long id,@Valid @RequestBody Genre genreDetails) {
        Genre updateGenre = genreService.findById(id);
        updateGenre.setName(genreDetails.getName());
        updateGenre.setMovies(genreDetails.getMovies());
        genreService.save(updateGenre);

        return new ResponseEntity<String>("Genre with id = " + id + " successfully updated!", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addNewGenre(@Valid @RequestBody Genre genre) {
        Genre newGenre = genreService.save(genre);
        return new ResponseEntity<>("Genre successfully added!", HttpStatus.CREATED);
    }

    private Genre applyPatchToGenre(
            JsonPatch patch, Genre targetGenre) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetGenre, JsonNode.class));
        return objectMapper.treeToValue(patched, Genre.class);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity updateGenre(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            Genre genre = genreService.findById(id);
            Genre genrePatched = applyPatchToGenre(patch, genre);
            genreService.save(genrePatched);
            return new ResponseEntity<>("Genre with id = " + id + " successfully updated!", HttpStatus.OK);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable long id){
        genreService.remove(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}

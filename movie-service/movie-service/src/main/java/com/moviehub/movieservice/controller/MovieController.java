package com.moviehub.movieservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.moviehub.movieservice.exception.ResourceNotFoundException;
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
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    ObjectMapper objectMapper = new ObjectMapper();

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
    public ResponseEntity<String> updateMovie(@PathVariable long id,@Valid @RequestBody Movie movieDetails) {
        Movie updateMovie = movieService.findById(id);
        updateMovie.setTitle(movieDetails.getTitle());
        updateMovie.setGrade(movieDetails.getGrade());
        updateMovie.setDescription(movieDetails.getDescription());
        updateMovie.setYear(movieDetails.getYear());
        updateMovie.setActors(movieDetails.getActors());
        updateMovie.setGenres(movieDetails.getGenres());

        movieService.save(updateMovie);

        return  new ResponseEntity<>("Movie with id = " + id +" successfully updated!", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addNewMovie(@Valid @RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>("Movie successfully added!", HttpStatus.CREATED);
    }

    private Movie applyPatchToMovie(
            JsonPatch patch, Movie targetMovie) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetMovie, JsonNode.class));
        return objectMapper.treeToValue(patched, Movie.class);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity updateMovie(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            Movie movie = movieService.findById(id);
            Movie moviePatched = applyPatchToMovie(patch, movie);
            movieService.save(moviePatched);
            return new ResponseEntity<>("Movie with id = " + id + " successfully updated!", HttpStatus.OK);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable long id) throws JsonProcessingException {
        movieService.remove(id);
        return new ResponseEntity<>("Movie successfully deleted!", HttpStatus.OK);
    }
}

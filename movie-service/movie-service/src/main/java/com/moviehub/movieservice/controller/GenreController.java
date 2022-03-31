package com.moviehub.movieservice.controller;

import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Genre;
import com.moviehub.movieservice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

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
    public ResponseEntity<Genre> updateGenre(@PathVariable long id,@Valid @RequestBody Genre genreDetails) {
        Genre updateGenre = genreService.findById(id);
        updateGenre.setName(genreDetails.getName());
        updateGenre.setMovies(genreDetails.getMovies());
        genreService.save(updateGenre);

        return  ResponseEntity.ok(updateGenre);
    }

    @PostMapping("/")
    public ResponseEntity<Genre> addNewGenre(@Valid @RequestBody Genre genre) {
        Genre newGenre = genreService.save(genre);
        return new ResponseEntity<Genre>(newGenre, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable long id){
        genreService.remove(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}

package com.moviehub.movieservice.controllers;

import com.moviehub.movieservice.models.Actor;
import com.moviehub.movieservice.models.Genre;
import com.moviehub.movieservice.services.ActorService;
import com.moviehub.movieservice.services.GenreService;
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

    @GetMapping("/all")
    public Iterable<Genre> getAll() {
        return genreService.getAll();
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

    @PostMapping("/add")
    public ResponseEntity<Genre> addNewGenre(@Valid @RequestBody Genre genre) {
        Genre newGenre = genreService.save(genre);
        return new ResponseEntity<Genre>(newGenre, HttpStatus.CREATED);
    }

   /* @DeleteMapping("/{id}")
    public ResponseEntity deleteGenre(@PathVariable long id){
        genreService.remove(id);
        return ResponseEntity.ok().build();
    }*/

}

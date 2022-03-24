package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Genre.AddGenreRequest;
import com.moviehub.watchlistservice.POJO.Movie.AddMovieRequest;
import com.moviehub.watchlistservice.entity.Genre;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreRepository genreRepository;

    @GetMapping("/all")
    public Iterable<Genre> getAll() {
        return genreRepository.findAll();
    }
    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable("id") Long id) {
        return genreRepository.findById(id).get();
    }

    @PostMapping("/add")
    public @ResponseBody String addNewGenre(@RequestBody AddGenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.name());
        genreRepository.save(genre);
        return "OK";
    }
}

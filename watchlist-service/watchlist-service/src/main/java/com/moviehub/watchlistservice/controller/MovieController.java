package com.moviehub.watchlistservice.controller;

import com.moviehub.watchlistservice.POJO.Movie.AddMovieRequest;
import com.moviehub.watchlistservice.POJO.Watchlist.AddWatchlistRequest;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/all")
    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") Long id) {
        return movieRepository.findById(id).get();
    }

    @PostMapping("/add")
    public @ResponseBody
    String addNewWatchlist(@RequestBody AddMovieRequest request) {
        Movie movie = new Movie();
        movie.setName(request.name());
        movie.setGrade(request.grade());
        movie.setTextDescription(request.textDescription());
        movieRepository.save(movie);
        return "OK";
    }
}

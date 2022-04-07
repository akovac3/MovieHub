package com.moviehub.watchlistservice.controller;


import com.moviehub.watchlistservice.POJO.Actor.AddMovieForActorRequest;
import com.moviehub.watchlistservice.POJO.Watchlist.AddMovieToWatchlistRequest;
import com.moviehub.watchlistservice.POJO.Watchlist.AddWatchlistRequest;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping(path = "/watchlist/all")
    public ResponseEntity<Iterable<Watchlist>> getAll() {
        return ResponseEntity.ok(watchlistService.findAll());
    }

    @GetMapping(path = "/watchlist/{id}")
    public ResponseEntity<Watchlist> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(watchlistService.findById(id));
    }

    @PostMapping(path = "/watchlist/add")
    public ResponseEntity<Watchlist> addNewWatchlist(@RequestBody AddWatchlistRequest request) {
        return ResponseEntity.ok(watchlistService.add(request));
    }

    @PostMapping(path = "/watchlist/{watchlistId}/movie")
    public ResponseEntity<Movie> addMovieToWatchlist(@PathVariable(value = "watchlistId")Long watchlistId, @RequestBody AddMovieToWatchlistRequest request) {
        Movie movie = watchlistService.addMovieToWatchlist(watchlistId, request);
        return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
    }

}

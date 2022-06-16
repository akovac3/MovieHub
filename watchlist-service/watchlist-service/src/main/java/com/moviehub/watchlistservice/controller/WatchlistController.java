package com.moviehub.watchlistservice.controller;


import com.moviehub.watchlistservice.POJO.Watchlist.AddWatchlistRequest;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping(path = "/watchlist")
    public ResponseEntity<Iterable<Watchlist>> getAll() {
        return ResponseEntity.ok(watchlistService.findAll());
    }

    @GetMapping(path = "/watchlist/{id}")
    public ResponseEntity<Watchlist> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(watchlistService.findById(id));
    }

    @GetMapping(path = "/watchlist/{id}/movies")
    public ResponseEntity<Set<Movie>> getMovies(@PathVariable("id") Long id) {
        Watchlist watchlist = watchlistService.findById(id);

        return ResponseEntity.ok(watchlist.getMovies());
    }

    @PostMapping(path = "/watchlist/add")
    public ResponseEntity<Watchlist> addNewWatchlist(@RequestBody AddWatchlistRequest request) {
        return ResponseEntity.ok(watchlistService.addNewWatchlist(request));
    }

    @PostMapping(path = "/watchlist/{watchlistId}/movie/{movieId}")
    public ResponseEntity<String> addMovieToWatchlist(@PathVariable(value = "watchlistId")Long watchlistId, @PathVariable(value = "movieId") Long movieId) {
        watchlistService.addMovieToWatchlist(watchlistId, movieId);
        return new ResponseEntity<String>("Movie is saved to watchlist!", HttpStatus.CREATED);
    }

   /* @GetMapping(path = "/watchlist/user/{userId}")
    public ResponseEntity<List<Watchlist>> getAllWatchlistsForUser(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(watchlistService.getWatchlistByUserId(userId));
    }*/

}

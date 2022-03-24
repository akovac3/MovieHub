package com.moviehub.watchlistservice.controller;


import com.moviehub.watchlistservice.POJO.Watchlist.AddWatchlistRequest;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Watchlist>> getAll() {
        return ResponseEntity.ok(watchlistService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Watchlist> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(watchlistService.findById(id));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewWatchlist(@RequestBody AddWatchlistRequest request) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUserId(request.userId());
        watchlist.setName(request.name());
        watchlistService.save(watchlist);
        return ResponseEntity.ok("Watchlist added successfully.");
    }

}

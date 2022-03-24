package com.moviehub.watchlistservice.controller;


import com.moviehub.watchlistservice.POJO.Watchlist.AddWatchlistRequest;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Watchlist> getAll() {
        return watchlistRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Iterable<Watchlist> getById(@PathVariable("id") Long id) {
        return watchlistRepository.findById(id).stream().toList();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewWatchlist(@RequestBody AddWatchlistRequest request) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUserId(request.userId());
        watchlist.setName(request.name());
        watchlistRepository.save(watchlist);
        return "OK";
    }

}

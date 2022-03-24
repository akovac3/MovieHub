package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.repository.MovieRepository;
import com.moviehub.watchlistservice.repository.WatchlistRepository;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;


    public Iterable<Watchlist> findAll() {
        return watchlistRepository.findAll();
    }

    public Watchlist findById(Long id) {

        Optional<Watchlist> res = watchlistRepository.findById(id);
        if(res.isPresent()) {
            return res.get();
        } else {
            throw new BadRequestException("There is no watchlist with id = " + id);
        }
    }

    public void save(Watchlist watchlist) {
        watchlistRepository.save(watchlist);
    }
}

package com.moviehub.watchlistservice.repository;

import com.moviehub.watchlistservice.entity.Watchlist;
import org.springframework.data.repository.CrudRepository;

public interface WatchlistRepository extends CrudRepository<Watchlist, Long> {

}

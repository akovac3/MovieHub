package com.moviehub.watchlistservice.repository;

import com.moviehub.watchlistservice.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

}

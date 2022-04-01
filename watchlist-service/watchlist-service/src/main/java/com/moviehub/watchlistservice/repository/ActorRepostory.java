package com.moviehub.watchlistservice.repository;

import com.moviehub.watchlistservice.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepostory extends JpaRepository<Actor, Long> {
}

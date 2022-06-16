package com.moviehub.watchlistservice.repository;

import com.moviehub.watchlistservice.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}

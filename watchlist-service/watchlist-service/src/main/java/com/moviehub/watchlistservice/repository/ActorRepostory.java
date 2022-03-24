package com.moviehub.watchlistservice.repository;

import com.moviehub.watchlistservice.entity.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepostory extends CrudRepository<Actor, Long> {
}

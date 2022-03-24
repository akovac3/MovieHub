package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.repository.ActorRepostory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ActorService {

    @Autowired
    private ActorRepostory actorRepostory;

    public Iterable<Actor> getAll() {
        return actorRepostory.findAll();
    }

    public Actor findById(Long id) {
        return actorRepostory.findById(id).get();
    }

    public void save(Actor actor) {
        actorRepostory.save(actor);
    }
}

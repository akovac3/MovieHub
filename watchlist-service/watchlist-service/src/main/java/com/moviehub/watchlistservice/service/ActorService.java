package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.repository.ActorRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepostory actorRepostory;

    public Iterable<Actor> getAll() {
        return actorRepostory.findAll();
    }

    public Actor findById(Long id) {
        Optional<Actor> res = actorRepostory.findById(id);
        if(res.isPresent()) {
            return res.get();
        } else {
            throw new BadRequestException("There is no actor with id = " + id);
        }
    }

    public void save(Actor actor) {
        actorRepostory.save(actor);
    }
}

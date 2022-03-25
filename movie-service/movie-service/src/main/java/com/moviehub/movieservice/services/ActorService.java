package com.moviehub.movieservice.services;

import com.moviehub.movieservice.models.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import com.moviehub.movieservice.repositories.ActorRepository;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public Iterable<Actor> getAll() {
        return actorRepository.findAll();
    }

    public Actor findById(Long id) {
        return actorRepository.findById(id).get();
    }

    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }
}

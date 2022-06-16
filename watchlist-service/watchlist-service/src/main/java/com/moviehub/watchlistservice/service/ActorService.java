package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.exceptions.ResourceNotFoundException;
import com.moviehub.watchlistservice.repository.ActorRepository;
import com.moviehub.watchlistservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Iterable<Actor> getAll() {
        return actorRepository.findAll();
    }

    public Actor findById(Long id) {
        return actorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Actor with provided id not found!"));
    }

    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor addActor(Actor actor){
        return actorRepository.save(actor);
    }

    public void remove(Long id){
        Actor actor = actorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Actor with id = " + id + " does not exists!"));
        actorRepository.deleteById(id);
    }
}

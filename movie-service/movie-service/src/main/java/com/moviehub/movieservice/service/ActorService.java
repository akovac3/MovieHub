package com.moviehub.movieservice.service;

import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Movie;
import com.moviehub.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.moviehub.movieservice.repository.ActorRepository;
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
        for(Movie movie : actor.getMovies()) {
            movieRepository.findById(movie.getId()).orElseThrow(()->new ResourceNotFoundException("Actor with id = " + movie.getId() + " does not exists!"));
        }
        for(Movie movie : actor.getMovies()){
            Movie newMovie = movieRepository.findById(movie.getId()).get();
            newMovie.getActors().add(actor);
        }
        return actorRepository.save(actor);
    }

    public void remove(Long id){
        Actor actor = actorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Actor with id = " + id + " does not exists!"));
        for(Movie movie : actor.getMovies()){
            movie.getActors().remove(actor);
            actor.getMovies().remove(movie);
            movieRepository.save(movie);
        }
        actorRepository.deleteById(id);
    }
}

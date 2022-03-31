package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.POJO.Actor.AddActorRequest;
import com.moviehub.watchlistservice.POJO.Actor.AddMovieForActorRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.repository.ActorRepostory;
import com.moviehub.watchlistservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepostory actorRepostory;

    @Autowired
    private MovieRepository movieRepository;

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

    public Movie addMovieForActor(Long actorId, AddMovieForActorRequest request) {

        Optional<Movie> movieOptional = movieRepository.findById(request.movieId());
        Optional<Actor> actorOptional = actorRepostory.findById(actorId);


        if(request.movieId() != 0 && !movieOptional.isPresent()) {
            throw new BadRequestException("Movie with id=" + request.movieId() + " is missing");
        }
        if(!actorOptional.isPresent()) {
            throw new BadRequestException("Actor with id=" + actorId + " is missing");
        }

        Actor actor = actorOptional.get();

        if(request.movieId() != 0) {
            Movie _movie = movieRepository.findById(request.movieId())
                    .orElseThrow(() -> new BadRequestException("Not found movie with id = " + request.movieId()));
            actor.getMovies().add(_movie);
            actorRepostory.save(actor);
            return _movie;
        }
        Movie m = new Movie();
        m.setName(request.name());
        m.setGrade(request.grade());
        m.setTextDescription(request.textDescription());
        actor.getMovies().add(m);
        actorRepostory.save(actor);
        return m;
    }

    public Actor add(AddActorRequest request) {
        Actor actor = new Actor();
        actor.setName(request.name());
        actor.setLastname(request.lastname());
        return actorRepostory.save(actor);
    }
}

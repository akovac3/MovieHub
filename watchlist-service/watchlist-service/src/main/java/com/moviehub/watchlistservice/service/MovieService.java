package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.POJO.Movie.AddActorForMovieRequest;
import com.moviehub.watchlistservice.POJO.Movie.AddMovieRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.repository.ActorRepostory;
import com.moviehub.watchlistservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepostory actorRepostory;

    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {

        Optional<Movie> res = movieRepository.findById(id);
        if(res.isPresent()) {
            return res.get();
        } else {
            throw new BadRequestException("There is no Movie with id = " + id);
        }
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie add(AddMovieRequest request) {
        Movie movie = new Movie();
        movie.setName(request.name());
        movie.setGrade(request.grade());
        movie.setTextDescription(request.textDescription());
        return movieRepository.save(movie);
    }

    public void remove(Long movie) {
        Movie movie1 = movieRepository.getById(movie);

        movieRepository.delete(movie1);
    }

    public Actor addActorForMovie(Long movieId, AddActorForMovieRequest request) {

        Optional<Actor> actorOptional = actorRepostory.findById(request.actorId());
        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if(request.actorId() != 0 && !actorOptional.isPresent()) {
            throw new BadRequestException("Actor with id=" + request.actorId() + " is missing");
        }
        if(!movieOptional.isPresent()) {
            throw new BadRequestException("Movie with id=" + movieId + " is missing");
        }

        Movie movie = movieOptional.get();

        if(request.actorId() != 0) {
            Actor _actor = actorRepostory.findById(request.actorId())
                .orElseThrow(() -> new BadRequestException("Not found actor with id = " + request.actorId()));
            movie.getActors().add(_actor);
            movieRepository.save(movie);
            return _actor;
        }
        Actor a = new Actor();
        a.setName(request.name());
        a.setLastname(request.lastname());
        movie.getActors().add(a);
        movieRepository.save(movie);
        return a;
    }
}

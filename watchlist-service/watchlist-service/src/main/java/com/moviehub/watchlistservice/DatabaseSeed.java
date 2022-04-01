package com.moviehub.watchlistservice;

import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.repository.ActorRepostory;
import com.moviehub.watchlistservice.repository.GenreRepository;
import com.moviehub.watchlistservice.repository.MovieRepository;
import com.moviehub.watchlistservice.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DatabaseSeed {
    @Autowired
    private ActorRepostory actorRepostory;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private WatchlistRepository watchlistRepository;

    @EventListener
    public void seed(final ContextRefreshedEvent event) {
        seedDatabase();
    }

    private void seedDatabase() {
        if(actorRepostory.count() == 0 && movieRepository.count() == 0) {

            createActor("Tom", "Hanks");
            createActor("Tom", "Cruise");
            createActor("Timothy", "Dalton");
            createActor("Angelina", "Jolie");
            createActor("Meryl", "Streep");
            createActor("Will", "Smith");
            createActor("Harrison", "Ford");
            createActor("John", "Travolta");

            createMovie("Kill Bill 1", 8D, "short description");
            createMovie("Kill Bill 2", 7.5, "short description");
            createMovie("Pulp Fiction", 9.4, "short description");
            createMovie("Indiana Jones 1", 8.2, "short description");
            createMovie("Indiana Jones 2", 8.4, "short description");
            createMovie("Indiana Jones 3", 8D, "short description");
            createMovie("Indiana Jones 4", 8D, "short description");
            createMovie("Die Hard 1", 8.5, "short description");
            createMovie("Die Hard 2", 8.2, "short description");
            createMovie("Die Hard 3", 8.9, "short description");

        }
    }

    public Actor createActor(String name, String lastname) {
        Actor actor = new Actor();
        actor.setName(name);
        actor.setLastname(lastname);
        actor.setMovies(new ArrayList<>());
        actorRepostory.save(actor);
        return actor;
    }
    public Movie createMovie(String name, Double grade, String textDescription) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setGrade(grade);
        movie.setTextDescription(textDescription);
        movieRepository.save(movie);
        return movie;
    }
}

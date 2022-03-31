package com.moviehub.movieservice.service;
import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Genre;
import com.moviehub.movieservice.model.Movie;
import com.moviehub.movieservice.repositorie.ActorRepository;
import com.moviehub.movieservice.repositorie.GenreRepository;
import com.moviehub.movieservice.repositorie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ActorRepository actorRepository;

    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).get();
    }

    public Movie addMovie(Movie movie){
        for(Genre genre : movie.getGenres()) {
            genreRepository.findById(genre.getId()).orElseThrow(()->new ResourceNotFoundException("Genre with id = " + genre.getId() + "does not exists!"));
        }
        for(Genre genre : movie.getGenres()){
            Genre newGenre = genreRepository.findById(genre.getId()).get();
            newGenre.getMovies().add(movie);
        }
        return movieRepository.save(movie);
    }


    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void remove(Long id){
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie with id = " + id + " does not exists!"));
        for(Actor actor : movie.getActors()){
            movie.getActors().remove(actor);
            actor.getMovies().remove(movie);
            actorRepository.save(actor);
        }
        movieRepository.deleteById(id);
    }
}
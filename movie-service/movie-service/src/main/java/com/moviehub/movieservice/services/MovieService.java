package com.moviehub.movieservice.services;
import com.moviehub.movieservice.models.Movie;
import com.moviehub.movieservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).get();
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }
}

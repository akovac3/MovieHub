package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

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
}

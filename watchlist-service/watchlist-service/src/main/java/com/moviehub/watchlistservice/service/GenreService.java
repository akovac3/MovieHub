package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.POJO.Genre.AddGenreRequest;
import com.moviehub.watchlistservice.POJO.Genre.AddMovieForGenreRequest;
import com.moviehub.watchlistservice.entity.Genre;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.repository.GenreRepository;
import com.moviehub.watchlistservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Iterable<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(Long id) {
        Optional<Genre> res = genreRepository.findById(id);
        if(res.isPresent()) {
            return res.get();
        } else {
            throw new BadRequestException("There is no Genre with id = " + id);
        }
    }

    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public Genre add(AddGenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.name());
        return genreRepository.save(genre);
    }

    public Movie addMovieForGenre(Long genreId, AddMovieForGenreRequest request) {
        Optional<Movie> movieOptional = movieRepository.findById(request.movieId());
        Optional<Genre> genreOptional = genreRepository.findById(genreId);

        if(request.movieId() != 0 && !movieOptional.isPresent()) {
            throw new BadRequestException("Moview with id=" + request.movieId() + " is missing");
        }
        if(!genreOptional.isPresent()) {
            throw new BadRequestException("Genre with id=" + genreId + " is missing");
        }

        Genre genre = genreOptional.get();
        if(request.movieId() != 0) {
            Movie _movie = movieRepository.findById(request.movieId())
                    .orElseThrow(() ->new BadRequestException("Not found movie with id = " + request.movieId()));
            genre.getMovies().add(_movie);
            genreRepository.save(genre);
            return _movie;
        }
        Movie m = new Movie();
        m.setName(request.name());
        m.setGrade(request.grade());
        m.setTextDescription(request.textDescription());
        genre.getMovies().add(m);
        genreRepository.save(genre);

        return m;
    }
}

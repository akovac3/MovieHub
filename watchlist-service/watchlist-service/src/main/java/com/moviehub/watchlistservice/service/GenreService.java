package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.POJO.Genre.AddGenreRequest;
import com.moviehub.watchlistservice.entity.Genre;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

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
}

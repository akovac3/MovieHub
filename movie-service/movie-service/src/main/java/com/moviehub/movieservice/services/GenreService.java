package com.moviehub.movieservice.services;
import com.moviehub.movieservice.models.Genre;
import com.moviehub.movieservice.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Iterable<Genre> getAll() {
        return genreRepository.findAll();
    }

    public Genre findById(Long id) {
        return genreRepository.findById(id).get();
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
}

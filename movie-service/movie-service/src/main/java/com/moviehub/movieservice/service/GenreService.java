package com.moviehub.movieservice.service;
import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Genre;
import com.moviehub.movieservice.repositorie.GenreRepository;
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
        return genreRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Genre with provided id not found!"));
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public void remove(Long id){
        genreRepository.deleteById(id);
    }
}

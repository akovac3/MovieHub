package com.moviehub.watchlistservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Genre;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.repository.ActorRepository;
import com.moviehub.watchlistservice.repository.GenreRepository;
import com.moviehub.watchlistservice.request.MovieRequest;
import com.moviehub.watchlistservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    GenreRepository genreRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<Movie>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie newMovie = movieService.findById(id);
        return ResponseEntity.ok().body(newMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable long id,@Valid @RequestBody MovieRequest movieDetails) {
        Movie updateMovie = movieService.findById(id);
        updateMovie.setTitle(movieDetails.getTitle());
        updateMovie.setGrade(movieDetails.getGrade());
        updateMovie.setDescription(movieDetails.getDescription());
        updateMovie.setYear(movieDetails.getYear());

        Set<Long> actorsSet = movieDetails.getActors();
        Set<Long> genresSet = movieDetails.getGenres();

        Set<Actor> actors = new HashSet<>();
        actorsSet.forEach(actor -> {
            Actor novi = actorRepository.findById(actor)
                    .orElseThrow(() -> new RuntimeException("Error: Actor not found."));
            actors.add(novi);
        });
        updateMovie.setActors(actors);

        Set<Genre> genres = new HashSet<>();
        genresSet.forEach(genre -> {
            Genre novi = genreRepository.findById(genre)
                    .orElseThrow(() -> new RuntimeException("Error: Genre not found."));
            genres.add(novi);
        });
        updateMovie.setGenres(genres);

        movieService.save(updateMovie);

        return  new ResponseEntity<>("Movie with id = " + id +" successfully updated!", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> addNewMovie(@Valid @RequestBody MovieRequest movieRequest) {
        Movie movie = new Movie(movieRequest.getTitle(),
                movieRequest.getGrade(),
                movieRequest.getDescription(),
                movieRequest.getYear(),
                movieRequest.getImage()
        );
        Set<Long> actorsSet = movieRequest.getActors();
        Set<Long> genresSet = movieRequest.getGenres();

        Set<Actor> actors = new HashSet<>();
        actorsSet.forEach(actor -> {
            Actor novi = actorRepository.findById(actor)
                    .orElseThrow(() -> new RuntimeException("Error: Actor not found."));
            actors.add(novi);
        });
        movie.setActors(actors);

        Set<Genre> genres = new HashSet<>();
        genresSet.forEach(genre -> {
            Genre novi = genreRepository.findById(genre)
                    .orElseThrow(() -> new RuntimeException("Error: Genre not found."));
            genres.add(novi);
        });
        movie.setGenres(genres);
        movieService.addMovie(movie);
        return new ResponseEntity<>("Movie successfully added!", HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable long id) throws JsonProcessingException {
        movieService.remove(id);
        return new ResponseEntity<>("Movie successfully deleted!", HttpStatus.OK);
    }
}

package com.moviehub.movieservice.service;
import com.moviehub.movieservice.exception.BadRequestException;
import com.moviehub.movieservice.exception.ResourceNotFoundException;
import com.moviehub.movieservice.model.Actor;
import com.moviehub.movieservice.model.Movie;
import com.moviehub.movieservice.model.Role;
import com.moviehub.movieservice.model.User;
import com.moviehub.movieservice.repository.ActorRepository;
import com.moviehub.movieservice.repository.GenreRepository;
import com.moviehub.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private RestTemplate restTemplate ;

    public Iterable<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Movie with provided id not found!"));
    }

    public Movie addMovie(Movie movie){
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://user-service/user/"+movie.getUserId(), User.class);
        User user = responseEntity.getBody();
        if(user.getRole()!= Role.ROLE_ADMIN) throw new ResourceNotFoundException("This user can not add movie!");
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

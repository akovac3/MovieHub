package com.moviehub.watchlistservice.service;

import com.moviehub.watchlistservice.POJO.Actor.AddMovieForActorRequest;
import com.moviehub.watchlistservice.POJO.User;
import com.moviehub.watchlistservice.POJO.Watchlist.AddMovieToWatchlistRequest;
import com.moviehub.watchlistservice.POJO.Watchlist.AddWatchlistRequest;
import com.moviehub.watchlistservice.entity.Actor;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.exceptions.BadRequestException;
import com.moviehub.watchlistservice.exceptions.ServiceUnavailableException;
import com.moviehub.watchlistservice.repository.MovieRepository;
import com.moviehub.watchlistservice.repository.WatchlistRepository;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RestTemplate restTemplate;


    public Iterable<Watchlist> findAll() {
        return watchlistRepository.findAll();
    }

    public Watchlist findById(Long id) {

        Optional<Watchlist> res = watchlistRepository.findById(id);
        if(res.isPresent()) {
            return res.get();
        } else {
            throw new BadRequestException("There is no watchlist with id = " + id);
        }
    }

    public void save(Watchlist watchlist) {
        watchlistRepository.save(watchlist);
    }

    public Watchlist add(AddWatchlistRequest request) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUserId(request.userId());
        watchlist.setName(request.name());
        return watchlistRepository.save(watchlist);
    }

    public Movie addMovieToWatchlist(Long watchlistId, AddMovieToWatchlistRequest request) {
        Optional<Movie> movieOptional = movieRepository.findById(request.movieId());
        Optional<Watchlist> watchlistOptional = watchlistRepository.findById(watchlistId);

        if(request.movieId() != 0 && !movieOptional.isPresent()) {
            throw new BadRequestException("Movie with id=" + request.movieId() + " is missing");
        }
        if(!watchlistOptional.isPresent()) {
            throw new BadRequestException("Watchlist with id=" + watchlistId + " is missing");
        }

        Watchlist watchlist = watchlistOptional.get();

        if(request.movieId() != 0) {
            Movie _movie = movieRepository.findById(request.movieId())
                    .orElseThrow(() -> new BadRequestException("Not found movie with id = " + request.movieId()));
            watchlist.getMovies().add(_movie);
            watchlistRepository.save(watchlist);
            return _movie;
        }
        Movie m = new Movie();
        m.setName(request.name());
        m.setGrade(request.grade());
        m.setTextDescription(request.textDescription());
        watchlist.getMovies().add(m);
        watchlistRepository.save(watchlist);
        return m;
    }

    public Watchlist addNewWatchlist(AddWatchlistRequest request) {
        User user = null;
        if(request.userId() != null) {
            try {
                user = restTemplate.getForObject(
                        "http://user-service/user?id=" + request.userId().toString(),
                        User.class
                );
            } catch (ResourceAccessException exception) {
                throw new ServiceUnavailableException("Error in service communication");
            }
        }
        if(user != null) {
            Watchlist watchlist = new Watchlist();
            watchlist.setUserId(request.userId());
            watchlist.setName(request.name());
            watchlistRepository.save(watchlist);
            return watchlist;
        }
        throw new BadRequestException("User not found");
    }
}

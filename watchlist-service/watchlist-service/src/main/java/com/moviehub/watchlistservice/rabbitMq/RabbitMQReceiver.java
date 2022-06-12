package com.moviehub.watchlistservice.rabbitMq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.repository.MovieRepository;
import com.moviehub.watchlistservice.repository.WatchlistRepository;
import com.moviehub.watchlistservice.service.MovieService;
import com.moviehub.watchlistservice.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class RabbitMQReceiver {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final MovieRepository movieRepository;
    private final WatchlistRepository watchlistRepository;
    private final WatchlistService service;
    private final RabbitMQSender rabbitMQSender;
    private final MovieService movieService;

    public RabbitMQReceiver(MovieRepository movieRepository, WatchlistRepository watchlistRepository, WatchlistService service, RabbitMQSender rabbitMQSender, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.watchlistRepository = watchlistRepository;
        this.service = service;
        this.rabbitMQSender = rabbitMQSender;
        this.movieService = movieService;
    }

    public void receiveMessage(String message) {
        logger.info("Received (String) " + message);
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        logger.info("Received (No String) " + strMessage);
        processMessage(strMessage);
    }

    private void processMessage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Long id = objectMapper.readValue(message, Long.class);
            try {
               /*List<Watchlist> allWatchlists = watchlistRepository.findAll();
                Movie movie = movieRepository.getById(id);
                logger.info(String.valueOf(movie.getMovieId()));
                for(int i=0; i<allWatchlists.size(); i++){
                    Watchlist watchlist = allWatchlists.get(i);
                    logger.info(String.valueOf(watchlist.getWatchlistId()));
                    System.out.println("nesto");
                    System.out.println(watchlist);
                    Set<Movie> movies = watchlist.getMovies();
                    for(Movie movie2 : movies){
                        logger.info(String.valueOf(movie2.getMovieId()));
                        if(Objects.equals(movie2.getMovieId(), id)) {
                            service.removeMovieFromWatchlist(movie.getMovieId(), watchlist.getWatchlistId());
                            logger.info(String.valueOf(movie.getMovieId()));
                        }
                    }

                    if(movies.contains(movie)) {
                        service.removeMovieFromWatchlist(movie.getMovieId(), watchlist.getWatchlistId());
                        logger.info(String.valueOf(movie.getMovieId()));
                    }
                }*/
                movieService.remove(id);
                rabbitMQSender.send();
            } catch (Exception ignored) {
                rabbitMQSender.send(id);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

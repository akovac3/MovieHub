package com.moviehub.watchlistservice.rabbitMq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviehub.watchlistservice.entity.Watchlist;
import com.moviehub.watchlistservice.repository.MovieRepository;
import com.moviehub.watchlistservice.repository.WatchlistRepository;
import com.moviehub.watchlistservice.service.MovieService;
import com.moviehub.watchlistservice.service.WatchlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final MovieRepository movieRepository;
    private final WatchlistRepository watchlistRepository;
    private final WatchlistService service;
    private final RabbitMQSender rabbitMQSender;
    private final MovieService movieService;
    private final WatchlistService watchlistService;

    public RabbitMQReceiver(MovieRepository movieRepository, WatchlistRepository watchlistRepository, WatchlistService service, RabbitMQSender rabbitMQSender, MovieService movieService, WatchlistService watchlistService) {
        this.movieRepository = movieRepository;
        this.watchlistRepository = watchlistRepository;
        this.service = service;
        this.rabbitMQSender = rabbitMQSender;
        this.movieService = movieService;
        this.watchlistService = watchlistService;
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
            String uuid = objectMapper.readValue(message, String.class);
            try {
               Watchlist watchlist = watchlistService.addWatchlist(uuid);
                rabbitMQSender.send(watchlist);
            } catch (Exception ignored) {
                rabbitMQSender.send(message);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

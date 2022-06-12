package com.moviehub.movieservice.rabbitMq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviehub.movieservice.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMQReceiver {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final MovieRepository movieRepository;

    public RabbitMQReceiver(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
            RabbitMQStatus rabbitMQStatus = objectMapper.readValue(message, RabbitMQStatus.class);
            if (rabbitMQStatus.getStatus() == 0) {
                logger.info(String.valueOf(rabbitMQStatus.getMovie()));
                logger.info("Nije uspjesno brisanje u watchlist");
            } else {
                logger.info("Async communication completed successfully.");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

package com.moviehub.watchlistservice.rabbitMq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviehub.watchlistservice.entity.Movie;
import com.moviehub.watchlistservice.entity.Watchlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private String directExchange;
    private final RabbitTemplate rabbitTemplate;
    private String routingKeyInfo;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${routing.keyInfo}")
    public void setRoutingKeyInfo(String routingKeyInfo) {
        this.routingKeyInfo = routingKeyInfo;
    }

    @Value("${direct.exchange1}")
    public void setDirectExchange(String directExchange) {
        this.directExchange = directExchange;
    }

    public void send(String userId) throws JsonProcessingException {
        logger.info("Storing notification (routing key = info, status = 0)");
        rabbitTemplate.setExchange(directExchange);
        ObjectMapper objectMapper = new ObjectMapper();
        rabbitTemplate.convertAndSend(routingKeyInfo, objectMapper.writeValueAsString(new RabbitMQStatus(0, null, userId)));
        logger.info("Notification stored in queue sucessfully");
    }

    public void send(Watchlist watchlistId) throws JsonProcessingException {
        logger.info("Storing notification (routing key = info, status = 1)");
        rabbitTemplate.setExchange(directExchange);
        ObjectMapper objectMapper = new ObjectMapper();
        rabbitTemplate.convertAndSend(routingKeyInfo, objectMapper.writeValueAsString(new RabbitMQStatus(1, watchlistId.getWatchlistId(), watchlistId.getName())));
        logger.info("Notification stored in queue sucessfully");
    }
}

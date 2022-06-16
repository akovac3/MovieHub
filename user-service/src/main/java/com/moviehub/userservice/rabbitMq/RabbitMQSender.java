package com.moviehub.userservice.rabbitMq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    private String routingKeySuccess;
    private String routingKeyInfo;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${direct.exchange2}")
    public void setDirectExchange(String directExchange) {
        this.directExchange = directExchange;
    }

    @Value("${routing.keySuccess}")
    public void setRoutingKeySuccess(String routingKeySuccess) {
        this.routingKeySuccess = routingKeySuccess;
    }

    @Value("${routing.keyInfo}")
    public void setRoutingKeyInfo(String routingKeySuccess) {
        this.routingKeyInfo = routingKeySuccess;
    }


    public void send(String string) throws JsonProcessingException {
        logger.info("Storing movie (routing key = success)");
        rabbitTemplate.setExchange(directExchange);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        rabbitTemplate.convertAndSend(routingKeySuccess, objectMapper.writeValueAsString(string));
        logger.info("Movie stored in queue sucessfully");
    }
}


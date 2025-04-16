package com.learn.photo.rabbitmq;

import com.learn.photo.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing_key.json.name}")
    private String routingKeyJson;

    private final RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    public RabbitMqJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Send message
    public void sendMessage(UserDto userDto) {
        LOGGER.info(String.format("Message sent -> %s", userDto.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKeyJson, userDto);
    }
}

package com.learn.photo.rabbitmq;

import com.learn.photo.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);

    // Listen message
    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void consume(UserDto userDto) {
        LOGGER.info(String.format("Received message -> %s", userDto.toString()));
    }
}

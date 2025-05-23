package com.learn.photo.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    // Listen message
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(String message) {
        LOGGER.info(String.format("Received message -> %s", message));
    }
}

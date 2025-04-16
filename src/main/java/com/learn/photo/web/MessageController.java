package com.learn.photo.web;

import com.learn.photo.rabbitmq.RabbitMqProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {

    private final RabbitMqProducer rabbitMqProducer;

    public MessageController(RabbitMqProducer rabbitMqProducer) {
        this.rabbitMqProducer = rabbitMqProducer;
    }

    @GetMapping("/produce/{message}")
    public ResponseEntity<String> get(@PathVariable String message) {
        rabbitMqProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ in Controller");
    }
}

package com.learn.photo.web;

import com.learn.photo.dto.UserDto;
import com.learn.photo.model.Photo;
import com.learn.photo.rabbitmq.RabbitMqJsonProducer;
import com.learn.photo.rabbitmq.RabbitMqProducer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final RabbitMqProducer rabbitMqProducer;
    private final RabbitMqJsonProducer rabbitMqJsonProducer;

    public MessageController(RabbitMqProducer rabbitMqProducer, RabbitMqJsonProducer rabbitMqJsonProducer) {
        this.rabbitMqProducer = rabbitMqProducer;
        this.rabbitMqJsonProducer = rabbitMqJsonProducer;
    }

    @GetMapping("/produce/{message}")
    public ResponseEntity<String> get(@PathVariable String message) {
        rabbitMqProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ in Controller");
    }

    @PostMapping("/produce/json")
    public ResponseEntity<String> message(@RequestBody UserDto userDto) {
        rabbitMqJsonProducer.sendMessage(userDto);
        return ResponseEntity.ok("Message sent to RabbitMQ in Controller");
    }
}

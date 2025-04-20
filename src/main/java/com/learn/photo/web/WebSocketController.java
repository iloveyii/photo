package com.learn.photo.web;

import com.learn.photo.model.Greeting;
import com.learn.photo.model.HelloMessage;
import com.learn.photo.model.UserMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws  Exception {
        Thread.sleep(1000);
        return new Greeting( message.getName() + " says: " + "message");
    }

    @MessageMapping("/messaging")
    @SendTo("/topic/chat")
    public UserMessage userMessage(UserMessage userMessage) throws Exception {
        Thread.sleep(1000);
        return userMessage;
    }

    @GetMapping("/message")
    public String message() {
        return "pages/message";
    }
}

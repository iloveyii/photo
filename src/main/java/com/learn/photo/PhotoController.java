package com.learn.photo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoController {

    @GetMapping("/")
    public String hello() {
        return "Hello world";
    }
}

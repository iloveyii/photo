package com.learn.photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotoController {

    private Map<String, Photo> db = new HashMap<>(){{
        put("1", new Photo("1", "hello.jpg"));
    }};

    @GetMapping("/")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/photo")
    public Collection<Photo> get() {
        return db.values();
    }

    @GetMapping("/photo/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = db.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return  photo;
    }

    @DeleteMapping("/photo/{id}")
    public void remove(@PathVariable String id) {
        Photo photo = db.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/photo")
    public Photo create(@RequestBody @Valid Photo photo) {
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(), photo);
        return photo;
    }

    @PostMapping("/fileupload")
    public Photo fileupload(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setData(file.getBytes());
        db.put(photo.getId(), photo);
        return photo;
    }
}

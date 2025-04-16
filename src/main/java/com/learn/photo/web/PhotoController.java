package com.learn.photo.web;
import com.learn.photo.service.PhotoService;
import com.learn.photo.model.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotoController {

    private  final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photo")
    public Iterable<Photo> get() {
        return photoService.get();
    }

    @GetMapping("/photo/{id}")
    public Photo get(@PathVariable Integer id) {
        Photo photo = photoService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return  photo;
    }

    @DeleteMapping("/photo/{id}")
    public void remove(@PathVariable Integer id) {
        photoService.remove(id);
    }

    @PostMapping("/photo")
    public Photo create(@RequestBody @Valid Photo photo) {
        photoService.create(photo.getFileName(), photo.getContentType(), photo.getData());
        return photo;
    }

    @PostMapping("/fileupload")
    public Photo fileupload(@RequestPart("data") MultipartFile file) throws IOException {
        return photoService.create(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}

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
public class PosController {


    @GetMapping("/photo")
    public Iterable<Photo> get() {
        return photoService.get();
    }
}

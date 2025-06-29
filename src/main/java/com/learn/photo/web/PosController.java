package com.learn.photo.web;

import com.learn.photo.service.PhotoService;
import com.learn.photo.model.Photo;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.*;

@RestController
public class PosController {


    @GetMapping("/api/v1/products")
    public Iterable<Photo> get() {
        return null;
    }
}

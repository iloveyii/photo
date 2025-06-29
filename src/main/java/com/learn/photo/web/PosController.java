package com.learn.photo.web;

import com.learn.photo.model.Product;
import com.learn.photo.service.PhotoService;
import com.learn.photo.model.Photo;
import com.learn.photo.service.ProductService;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
public class PosController {
    private  final ProductService productService;

    public PosController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> get() {
        return productService.get();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Integer id) {
        Product product = productService.get(id);
        if(product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return  product;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id) {
        productService.remove(id);
    }

    @PostMapping
    public Product create(@RequestBody @Valid Product product) {
        productService.create(product.getName(), product.getPrice(), product.getQuantity());
        return product;
    }
}

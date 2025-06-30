package com.learn.photo.service;


import com.learn.photo.model.Photo;
import com.learn.photo.model.Product;
import com.learn.photo.repository.PhotoRepository;
import com.learn.photo.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> get() {
        return productRepository.findAll();
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void remove(Long id) {

        productRepository.deleteById(id);
    }

    public Product create(String name, Float price, Integer quantity) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        productRepository.save(product);
        return product;
    }
}

package com.learn.photo.repository;

import com.learn.photo.model.Photo;
import com.learn.photo.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {}

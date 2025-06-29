package com.learn.photo.dto;

import lombok.Data;

@Data
public class ProductDto {
    private  int id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
}

package com.learn.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("PRODUCTS")
public class Product {
    @Id private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
}

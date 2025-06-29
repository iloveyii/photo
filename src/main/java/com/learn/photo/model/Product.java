package com.learn.photo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("PRODUCT")
public class Product {
    @Id
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Float price;

    @NotNull
    private Integer quantity;

    public Product() {
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description ;
    }

    public String getDescription() {
        return this.description;
    }
}

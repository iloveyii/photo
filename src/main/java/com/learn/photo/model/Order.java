package com.learn.photo.model;


import org.springframework.data.annotation.Id;
import javax.persistence.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    private Integer id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "order_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDate;

    private String status = "PENDING";

    @Column(name = "total_amount", nullable = false)
    private Float totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    // Constructors
    public Order() {}

    public Order(String customerName, String customerEmail, String status, Float totalAmount) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    // Helper method to add product to order
    public void addProduct(Product product, Integer quantity) {
        OrderProduct orderProduct = new OrderProduct(this, product, quantity, product.getPrice());
        orderProducts.add(orderProduct);
    }

    // Standard getters and setters...

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
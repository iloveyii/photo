package com.learn.photo.dto;

import com.learn.photo.model.Order;
import com.learn.photo.model.Product;
import com.learn.photo.repository.ProductRepository;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.learn.photo.model.Order;
import com.learn.photo.model.OrderProduct;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String customerName;
    private String customerEmail;
    private LocalDateTime orderDate;
    private String status;
    private Float totalAmount;
    private List<OrderProductDto> items;
    @Autowired
    private ProductRepository productRepository;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.customerName = order.getCustomerName();
        this.customerEmail = order.getCustomerEmail();
        this.orderDate = order.getOrderDate();
        this.status = order.getStatus();
        this.totalAmount = order.getTotalAmount();
        this.items = order.getOrderProducts().stream()
                .map(product -> {
                    return new OrderProductDto(
                            product.getId(),
                            productRepository.findById(product.getId())
                                    .orElseThrow().getName(),
                            product.getQuantity(),
                            product.getPriceAtPurchase()
                    );
                })
                .collect(Collectors.toList());
    }
}
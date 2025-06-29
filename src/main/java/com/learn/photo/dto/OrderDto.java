package com.learn.photo.dto;

import com.learn.photo.model.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {
    private Integer id;
    private String customerName;
    private String customerEmail;
    private LocalDateTime orderDate;
    private String status;
    private Float totalAmount;
    private List<OrderProductDto> items;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.customerName = order.getCustomerName();
        this.customerEmail = order.getCustomerEmail();
        this.orderDate = order.getOrderDate();
        this.status = order.getStatus();
        this.totalAmount = order.getTotalAmount();
        this.items = order.getOrderProducts().stream()
                .map(OrderProductDto::new)
                .collect(Collectors.toList());
    }

    public List<OrderProductDto> getItems() {
        return items;
    }

    public void setItems(List<OrderProductDto> items) {
        this.items = items;
    }
}

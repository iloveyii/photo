package com.learn.photo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Table("ORDERS")
public class Order {
    @Id
    private Long id;

    @Column("customer_name")
    private String customerName;

    @Column("customer_email")
    private String customerEmail;

    @Column("order_date")
    private LocalDateTime orderDate = LocalDateTime.now();

    private String status = "PENDING";

    @Column("total_amount")
    private Float totalAmount;

    @MappedCollection(idColumn = "order_id")
    private Set<OrderProduct> orderProducts = new HashSet<OrderProduct>();

    // Custom relationship management methods
    public void addProduct(OrderProduct orderProduct) {
        orderProduct.setOrderId(this.id);
        this.orderProducts.add(orderProduct);
    }

    public void removeProduct(OrderProduct orderProduct) {
        this.orderProducts.remove(orderProduct);
    }
}
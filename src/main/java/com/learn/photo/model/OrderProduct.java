package com.learn.photo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("ORDERS_PRODUCTS")
public class OrderProduct {
    @Id
    private Long id;

    @Column("order_id")
    private Long orderId;

    @Column("product_id")
    private Long productId;

    private Integer quantity;

    @Column("price_at_purchase")
    private Float priceAtPurchase;

    @Transient
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;

    @Transient
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;
}
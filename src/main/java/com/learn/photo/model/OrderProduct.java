package com.learn.photo.model;

import javax.persistence.*;

@Entity
@Table(name = "orders_products")
public class OrderProduct {
    public OrderProduct(Order order, Product product, Integer quantity, Float price) {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")  // Using JPA @Column
    private Integer quantity;

    @Column(name = "price_at_purchase")
    private Float priceAtPurchase;

    // Constructors, getters, setters...


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(Float priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }


}

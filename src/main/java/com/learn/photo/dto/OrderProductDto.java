package com.learn.photo.dto;


import com.learn.photo.model.OrderProduct;
import lombok.Data;

@Data
public class OrderProductDto {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Float priceAtPurchase;

    public OrderProductDto(OrderProduct orderProduct) {
        this.productId = orderProduct.getProduct().getId();
        this.productName = orderProduct.getProduct().getName();
        this.quantity = orderProduct.getQuantity();
        this.priceAtPurchase = orderProduct.getPriceAtPurchase();
    }

    public OrderProductDto() {}

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}

package com.learn.photo.dto;


import com.learn.photo.model.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDto {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Float priceAtPurchase;

    public OrderProductDto(OrderProduct orderProduct) {
        this.productId = orderProduct.getProductId();
        this.quantity = orderProduct.getQuantity();
        this.priceAtPurchase = orderProduct.getPriceAtPurchase();
    }
}

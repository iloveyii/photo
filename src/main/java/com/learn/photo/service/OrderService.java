package com.learn.photo.service;

import com.learn.photo.dto.OrderDto;
import com.learn.photo.dto.OrderProductDto;
import com.learn.photo.model.Order;
import com.learn.photo.model.OrderProduct;
import com.learn.photo.model.Product;
import com.learn.photo.repository.OrderProductRepository;
import com.learn.photo.repository.OrderRepository;
import com.learn.photo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    public Order createOrder(OrderDto orderDto) {
        // Create new order
        Order order = new Order();
        //order.setCustomerName(orderDto.getCustomerName());
        //order.setCustomerEmail(orderDto.getCustomerEmail());
        order.setStatus("PENDING");

        // Calculate total amount and add products
        float totalAmount = 0;
        for (OrderProductDto item : orderDto.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            // Update product quantity
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);

            // Add to order
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setProductId(product.getId());
            orderProduct.setQuantity(product.getQuantity()); // must be from orderDto
            order.getOrderProducts().add(orderProduct);
            totalAmount += product.getPrice() * item.getQuantity();
        }

        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public OrderDto getOrderDto(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return convertToDto(order);
    }

    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        // ... set other simple fields ...

        dto.setItems(order.getOrderProducts().stream()
                .map(this::convertToOrderProductDto)
                .collect(Collectors.toList()));

        return dto;
    }

    private OrderProductDto convertToOrderProductDto(OrderProduct orderProduct) {
        Product product = productRepository.findById(orderProduct.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return OrderProductDto.builder()
                .productId(product.getId())
                .productName(product.getName())
                .quantity(orderProduct.getQuantity())
                .priceAtPurchase(orderProduct.getPriceAtPurchase())
                .build();
    }
}

//import com.learn.photo.dto.OrderDto;
//import com.learn.photo.dto.OrderProductDto;
//import com.learn.photo.model.*;
//import com.learn.photo.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class OrderService {
//    @Autowired
//    private OrderRepository orderRepository;
//    private ProductRepository productRepository;
//    private OrderProductRepository orderProductRepository;
//
//    public Order createOrder(OrderDto orderDto) {
//        Order order = Order.builder()
//                .customerName(orderDto.getCustomerName())
//                .customerEmail(orderDto.getCustomerEmail())
//                .status("PENDING")
//                .build();
//
//        float totalAmount = orderDto.getItems().stream()
//                .map(item -> processOrderItem(order, item))
//                .reduce(0f, Float::sum);
//
//        order.setTotalAmount(totalAmount);
//        return orderRepository.save(order);
//    }
//
//    private float processOrderItem(Order order, OrderProductDto item) {
//        Product product = productRepository.findById(item.getProductId())
//                .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));
//
//        validateProductQuantity(product, item.getQuantity());
//
//        product.setQuantity(product.getQuantity() - item.getQuantity());
//        productRepository.save(product);
//
//        order.addProduct(OrderProduct.builder()
//                .productId(product.getId())
//                .quantity(item.getQuantity())
//                .priceAtPurchase(product.getPrice())
//                .build());
//
//        return product.getPrice() * item.getQuantity();
//    }
//
//    private void validateProductQuantity(Product product, int requestedQuantity) {
//        if (product.getQuantity() < requestedQuantity) {
//            throw new RuntimeException(
//                    String.format("Insufficient stock for %s. Available: %d, Requested: %d",
//                            product.getName(),
//                            product.getQuantity(),
//                            requestedQuantity)
//            );
//        }
//    }
//
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    public Order getOrderById(Long id) {
//        return orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
//    }
//
//    public Order updateOrderStatus(Long id, String status) {
//        Order order = getOrderById(id);
//        order.setStatus(status);
//        return orderRepository.save(order);
//    }
//}
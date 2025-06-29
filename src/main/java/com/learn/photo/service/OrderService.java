package com.learn.photo.service;

import com.learn.photo.dto.OrderDto;
import com.learn.photo.dto.OrderProductDto;
import com.learn.photo.model.Order;
import com.learn.photo.model.Product;
import com.learn.photo.repository.OrderProductRepository;
import com.learn.photo.repository.OrderRepository;
import com.learn.photo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
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
            order.addProduct(product, item.getQuantity());
            totalAmount += product.getPrice() * item.getQuantity();
        }

        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrderStatus(Integer id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

//    public List<Order> getOrdersByCustomerEmail(String email) {
//        return orderRepository.findByCustomerEmail(email);
//    }
//
//    public List<Order> getOrdersByStatus(String status) {
//        return orderRepository.findByStatus(status);
//    }
}

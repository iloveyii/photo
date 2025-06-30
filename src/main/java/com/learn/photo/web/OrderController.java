package com.learn.photo.web;


import com.learn.photo.dto.OrderDto;
import com.learn.photo.model.Order;
import com.learn.photo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderDto(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
//        List<OrderDto> orders = orderService.getAllOrders().stream()
//                .map(OrderDto::new)
//                .toList()
//                .collect(Collectors.toList());

//        List<OrderDto> orders =  StreamSupport.stream( orderService.getAllOrders().spliterator(), false)
//                .map(OrderDto::new)
//                .collect(Collectors.toList());
        Iterable<Order> orders = orderService.getAllOrders();
        System.out.println(orders);
        List<OrderDto> orders2 = new ArrayList<>();
        return ResponseEntity.ok(orders2);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return orderService.getOrderDto(id);
        // return ResponseEntity.ok(new OrderDto(order));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Order order = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(new OrderDto(order));
    }

//    @GetMapping("/customer/{email}")
//    public ResponseEntity<List<OrderDto>> getOrdersByCustomerEmail(@PathVariable String email) {
//        List<OrderDto> orders = orderService.getOrdersByCustomerEmail(email).stream()
//                .map(OrderDto::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(orders);
//    }
//
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<OrderDto>> getOrdersByStatus(@PathVariable String status) {
//        List<OrderDto> orders = orderService.getOrdersByStatus(status).stream()
//                .map(OrderDto::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(orders);
//    }
}

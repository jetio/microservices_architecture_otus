package ru.otus.hw7.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw7.order.model.CustomerOrder;
import ru.otus.hw7.order.service.OrderService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/hw7/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<CustomerOrder> createOrder(@RequestParam String username, @RequestParam BigDecimal amount) {
        CustomerOrder order = orderService.createOrder(username, amount);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<CustomerOrder>> getOrdersByUsername(@RequestParam String username) {
        List<CustomerOrder> orders = orderService.getOrdersByUsername(username);
        return ResponseEntity.ok(orders);
    }
}
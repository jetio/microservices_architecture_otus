package ru.otus.hw8.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/create")
    public String createOrder() {
        // Logic to create an order
        System.out.println("Order created");
        return "Order Created";
    }

    @PostMapping("/cancel")
    public String cancelOrder() {
        // Logic to cancel an order
        System.out.println("Order canceled");
        return "Order Canceled";
    }
}
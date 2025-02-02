package ru.otus.project.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.orders.adapter.MqAdapter;
import ru.otus.project.orders.domain.Order;
import ru.otus.project.orders.service.OrderService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MqAdapter mqAdapter;

    @PostMapping("/buy")
    public Order createBuyOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrderToBuy(order);
        mqAdapter.sendOrderToBuyIntoTrade(savedOrder);
        return savedOrder;
    }

    @PostMapping("/sell")
    public Order createSellOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrderToSell(order);
        mqAdapter.sendOrderToSellIntoTrade(savedOrder);
        return savedOrder;
    }
}
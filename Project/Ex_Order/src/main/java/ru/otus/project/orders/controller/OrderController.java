package ru.otus.project.orders.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.orders.adapter.MqAdapter;
import ru.otus.project.orders.domain.Order;
import ru.otus.project.orders.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MqAdapter mqAdapter;

    @PostMapping("/buy")
    @Counted(value = "createBuyOrder_counted")
    @Timed(value = "createBuyOrder_timed", percentiles = {0.5, 0.95, 0.99})
    public Order createBuyOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrderToBuy(order);
        mqAdapter.sendOrderToBuyIntoTrade(savedOrder);
        return savedOrder;
    }

    @PostMapping("/sell")
    @Counted(value = "createSellOrder_counted")
    @Timed(value = "createSellOrder_timed", percentiles = {0.5, 0.95, 0.99})
    public Order createSellOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrderToSell(order);
        mqAdapter.sendOrderToSellIntoTrade(savedOrder);
        return savedOrder;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }
}
package ru.otus.hw9;

import org.springframework.web.bind.annotation.*;
import ru.otus.hw9.Order;
import ru.otus.hw9.OrderService;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestParam String username, @RequestParam Double amount) {
        return orderService.createOrder(username, amount);
    }

    @GetMapping
    public List<Order> getUserOrders(@RequestParam String username) {
        return orderService.getUserOrders(username);
    }
}

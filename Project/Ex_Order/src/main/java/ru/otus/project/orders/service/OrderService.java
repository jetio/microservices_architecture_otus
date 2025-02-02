package ru.otus.project.orders.service;

import org.springframework.stereotype.Service;
import ru.otus.project.orders.domain.Order;
import ru.otus.project.orders.repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrderToBuy(Order order) {
        order.setType("BUY");
        order.setDateTime(getCurrentDateTime());
        return orderRepository.save(order);
    }

    public Order saveOrderToSell(Order order) {
        order.setType("SELL");
        order.setDateTime(getCurrentDateTime());
        return orderRepository.save(order);
    }

    private static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
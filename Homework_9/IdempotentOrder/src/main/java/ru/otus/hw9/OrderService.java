package ru.otus.hw9;

import org.springframework.stereotype.Service;
import ru.otus.hw9.Order;
import ru.otus.hw9.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(String username, Double amount) {
        String idempotencyKey = generateIdempotencyKey(username, amount);

        if (orderRepository.existsByIdempotencyKey(idempotencyKey)) {
            return orderRepository.findByIdempotencyKey(idempotencyKey);
        }

        Order order = new Order();
        order.setUsername(username);
        order.setAmount(amount);
        order.setIdempotencyKey(idempotencyKey);
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String username) {
        return orderRepository.findByUsername(username);
    }

    private String generateIdempotencyKey(String username, Double amount) {
        return UUID.nameUUIDFromBytes((username + amount).getBytes()).toString();
    }
}

package ru.otus.hw9;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername(String username);
    boolean existsByIdempotencyKey(String idempotencyKey);
    Order findByIdempotencyKey(String idempotencyKey);
}
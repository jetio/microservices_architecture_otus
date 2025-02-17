package ru.otus.project.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.orders.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

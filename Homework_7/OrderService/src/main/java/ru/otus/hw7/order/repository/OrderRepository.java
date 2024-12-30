package ru.otus.hw7.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw7.order.model.CustomerOrder;

import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByUsername(String username);
}

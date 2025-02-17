package ru.otus.project.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.trade.domain.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {
}

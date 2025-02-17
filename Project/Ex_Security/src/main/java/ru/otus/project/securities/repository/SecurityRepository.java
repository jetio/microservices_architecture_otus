package ru.otus.project.securities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.securities.domain.Security;

public interface SecurityRepository extends JpaRepository<Security, Long> {
}

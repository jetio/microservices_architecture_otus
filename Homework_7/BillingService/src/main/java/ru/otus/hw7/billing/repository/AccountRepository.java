package ru.otus.hw7.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw7.billing.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}

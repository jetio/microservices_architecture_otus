package ru.otus.project.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.users.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
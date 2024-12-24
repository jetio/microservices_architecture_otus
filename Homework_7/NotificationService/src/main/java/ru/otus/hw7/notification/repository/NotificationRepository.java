package ru.otus.hw7.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw7.notification.model.NotificationMessage;

public interface NotificationRepository extends JpaRepository<NotificationMessage, Long> {
}
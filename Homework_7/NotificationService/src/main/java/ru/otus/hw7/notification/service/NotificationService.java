package ru.otus.hw7.notification.service;

import org.springframework.stereotype.Service;
import ru.otus.hw7.notification.model.NotificationMessage;
import ru.otus.hw7.notification.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationMessage saveMessage(String messageText) {
        NotificationMessage notificationMessage = new NotificationMessage(messageText);
        return notificationRepository.save(notificationMessage);
    }

    public List<NotificationMessage> getAllMessages() {
        return notificationRepository.findAll();
    }
}
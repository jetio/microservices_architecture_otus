package ru.otus.hw7.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw7.notification.model.NotificationMessage;
import ru.otus.hw7.notification.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/hw7/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "Save a new notification message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<NotificationMessage> saveMessage(@RequestParam String message) {
        return ResponseEntity.ok(notificationService.saveMessage(message));
    }

    @Operation(summary = "Get all notification messages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Messages retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No messages found")
    })
    @GetMapping
    public ResponseEntity<List<NotificationMessage>> getAllMessages() {
        List<NotificationMessage> messages = notificationService.getAllMessages();
        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(messages);
    }
}
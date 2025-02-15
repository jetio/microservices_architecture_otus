package ru.otus.project.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/auth")
    public ResponseEntity<Void> authenticate(@RequestHeader("Authorization") String authHeader) {
        // Simple check for a valid token (in real-world, you would validate against a database or OAuth provider)
        if ("Bearer 42".equals(authHeader)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
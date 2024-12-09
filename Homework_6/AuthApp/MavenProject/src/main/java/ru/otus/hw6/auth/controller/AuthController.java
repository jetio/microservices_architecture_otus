package ru.otus.hw6.auth.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw6.auth.dao.Dao;
import ru.otus.hw6.auth.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private Map<String, String> authMap = new HashMap<>();

    @Autowired
    @Qualifier("NoDb")
    Dao userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            User user_ = userRepository.getUserByCredentials(username, password);
            String authCode = String.valueOf(user_.getUsername().hashCode());
            authMap.put(authCode, user_.getUsername());
            return new ResponseEntity<>(authCode, HttpStatus.OK);
        }
         catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/auth")
    public ResponseEntity<String> login(@RequestHeader("X-AuthCode") String authCode) {
        try {
            if (authMap.containsKey(authCode)) {
                return new ResponseEntity<>("Authorised successful!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not authorised", HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package ru.otus.project.users.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.users.domain.User;
import ru.otus.project.users.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public User createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        rabbitTemplate.convertAndSend("/queue/trade/inbound/user", savedUser);
        return savedUser;
    }
}

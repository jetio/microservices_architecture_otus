package ru.otus.project.users.service;

import org.springframework.stereotype.Service;
import ru.otus.project.users.domain.User;
import ru.otus.project.users.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
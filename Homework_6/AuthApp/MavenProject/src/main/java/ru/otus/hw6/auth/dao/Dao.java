package ru.otus.hw6.auth.dao;

import ru.otus.hw6.auth.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by smirnov_aa on 02.06.2022.
 */

public interface Dao {

    List<User> findAll();

    void save(User user);

    User findById(long id);

    void update(User user);

    User getUserByCredentials(String username, String password);
}
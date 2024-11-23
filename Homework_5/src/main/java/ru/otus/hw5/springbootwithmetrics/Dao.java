package com.tutorialworks.demos.springbootwithmetrics;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by smirnov_aa on 02.06.2022.
 */

public interface Dao {
    void testConnection() throws SQLException;

    List<User> findAll();

    void save(User user);

    User findById(long id);

    void update(User user);
}
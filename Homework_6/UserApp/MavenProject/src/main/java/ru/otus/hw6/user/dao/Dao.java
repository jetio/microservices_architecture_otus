package ru.otus.hw6.user.dao;

import ru.otus.hw6.user.domain.UserProfile;

/**
 * Created by smirnov_aa on 02.06.2022.
 */

public interface Dao {

    UserProfile findById(long id);

    void update(UserProfile userProfile);
}
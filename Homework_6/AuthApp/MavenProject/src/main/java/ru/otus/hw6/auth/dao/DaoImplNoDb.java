package ru.otus.hw6.auth.dao;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.hw6.auth.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smirnov_aa on 03.06.2022.
 */
@Repository
@Qualifier("NoDb")
public class DaoImplNoDb implements Dao {

	private final List<User> userTable = new ArrayList<>();

	@Override
	public List<User> findAll() {
		return userTable;
	}

	@Override
	public void save(User user) {
		userTable.add(user);
	}

	@Override
	public User findById(long id) {
		for (User user : userTable) {
			if (user.getId() == id){
				return user;
			}
		}
		throw new RuntimeException("User not found");
	}

	@Override
	public void update(User user) {
		throw new RuntimeException("Not supported yet");
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		for (User user : userTable) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)){
				return user;
			}
		}
		throw new RuntimeException("User not found");
	}
}

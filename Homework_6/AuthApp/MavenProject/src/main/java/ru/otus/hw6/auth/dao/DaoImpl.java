package ru.otus.hw6.auth.dao;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.hw6.auth.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by smirnov_aa on 03.06.2022.
 */
@Repository
@Qualifier("Default")
public class DaoImpl implements Dao {

	private static final String SQL_CREATE_USER = "INSERT INTO user (id, username, firstName, lastName, email, phone) VALUES(?,?,?,?,?,?)";
	private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	private static final String SQL_UPDATE_USER = "UPDATE user SET type_id = ?, is_approved = ?, approval_date = ? WHERE payment_id = ?";
	private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
	private static final String SQL_SELECT_USER_BY_CREDENTIALS = "SELECT * FROM user WHERE username = ? and phone = ?";

	private JdbcTemplate jdbcTemplate;

	public DaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL_USERS, BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public void save(User user) {
		jdbcTemplate.update(SQL_CREATE_USER, user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
	}

	@Override
	public User findById(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, BeanPropertyRowMapper.newInstance(User.class), id);
	}

	@Override
	public void update(User user) {
		throw new RuntimeException("Not supported yet");
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_CREDENTIALS, BeanPropertyRowMapper.newInstance(User.class), username, password);
	}
}

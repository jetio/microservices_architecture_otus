package ru.otus.hw5.springbootwithmetrics;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by smirnov_aa on 03.06.2022.
 */
@Repository
@AllArgsConstructor
@Slf4j
public class DaoImpl implements Dao {

	private static final String SQL_CREATE_USER = "INSERT INTO user (id, username, firstName, lastName, email, phone) VALUES(?,?,?,?,?,?)";
	private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	private static final String SQL_UPDATE_USER = "UPDATE user SET type_id = ?, is_approved = ?, approval_date = ? WHERE payment_id = ?";
	private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";

	private JdbcTemplate jdbcTemplate;

	public void testConnection() throws SQLException {
		LoggerFactory.getLogger(Dao.class).info(jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName());
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query(SQL_SELECT_ALL_USERS, BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public void save(User user) {
		System.out.println(user);
		jdbcTemplate.update(SQL_CREATE_USER, user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
	}

	@Override
	public User findById(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, BeanPropertyRowMapper.newInstance(User.class), id);
	}

	@Override
	public void update(User user) {
		throw new RuntimeException("Not supported yet");
	}
}

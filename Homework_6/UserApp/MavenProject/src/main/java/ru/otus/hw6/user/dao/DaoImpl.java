package ru.otus.hw6.user.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.hw6.user.domain.UserProfile;

/**
 * Created by smirnov_aa on 03.06.2022.
 */
@Repository
@AllArgsConstructor
@Slf4j
@Primary
public class DaoImpl implements Dao {

	private static final String SQL_CREATE_USER = "INSERT INTO user (id, username, firstName, lastName, email, phone) VALUES(?,?,?,?,?,?)";
	private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	private static final String SQL_UPDATE_USER = "UPDATE user SET type_id = ?, is_approved = ?, approval_date = ? WHERE payment_id = ?";
	private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";

	private JdbcTemplate jdbcTemplate;

	@Override
	public UserProfile findById(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, BeanPropertyRowMapper.newInstance(UserProfile.class), id);
	}

	@Override
	public void update(UserProfile userProfile) {
		throw new RuntimeException("Not supported yet");
	}
}

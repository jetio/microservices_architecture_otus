package ru.otus.hw6.user.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.otus.hw6.user.domain.UserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smirnov_aa on 03.06.2022.
 */
@Repository
@AllArgsConstructor
@Slf4j
@Qualifier("NoDb")
public class DaoImplNoDb implements Dao {

	private final List<UserProfile> userProfileTable = new ArrayList<>();

	{
		userProfileTable.add(new UserProfile(1, "vip"));
		userProfileTable.add(new UserProfile(2, "common"));
	}

	@Override
	public UserProfile findById(long id) {
		for (UserProfile userProfile : userProfileTable) {
			if (userProfile.getUserId() == id){
				return userProfile;
			}
		}
		throw new RuntimeException("UserProfile not found");
	}

	@Override
	public void update(UserProfile userProfile) {

		for (int i = 0; i < userProfileTable.size(); i++) {
			if (userProfile.getUserId() == userProfileTable.get(i).getUserId()) {
				UserProfile userProfile1 = userProfileTable.get(i);
				userProfile1.setRole(userProfile.getRole());
            }
		}
		throw new RuntimeException("Not supported yet");
	}
}

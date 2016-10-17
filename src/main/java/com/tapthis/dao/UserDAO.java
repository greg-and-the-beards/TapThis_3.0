package com.tapthis.dao;

import java.util.List;
import com.tapthis.entity.UserInfo;

public interface UserDAO {

	UserInfo getUserById(int userId);
	UserInfo getUserByUsername(String userName);
	boolean addUser(UserInfo userId);
	void updateUser(UserInfo userId);
	void deleteUser(int userId);
	List<UserInfo> verifyPassword(String userName, String password);
}

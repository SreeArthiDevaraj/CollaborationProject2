package com.niit.dao;

import com.niit.model.User;

public interface UserDao {
	boolean isUsernamevalid(String username);
	boolean registerUser(User user);
	boolean isEmailvalid(String email);
	User login(User user);
	void update(User user);
	boolean isUpdatedEmailValid(String email,String username);
	User getUserByUsername(String username);
}

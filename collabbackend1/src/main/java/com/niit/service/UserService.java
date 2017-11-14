package com.niit.service;

import com.niit.model.User;

public interface UserService {
	boolean isUsernamevalid(String username);
	boolean registerUser(User user);
	boolean isEmailvalid(String email);
	User login(User user);
	void update(User user);
	User getUserByUsername(String username);
	boolean isUpdatedEmailValid(String email,String username);

}

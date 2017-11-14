package com.niit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.UserDao;
import com.niit.model.User;
@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserDao userDao;
	public boolean registerUser(User user) {
		return userDao.registerUser(user);
	}
	public boolean isUsernamevalid(String username) {
		// TODO Auto-generated method stub
		return userDao.isUsernamevalid(username);
	}
	
	public boolean isEmailvalid(String email) {
		return userDao.isEmailvalid(email);
	}
	
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
	public void update(User user) {
		// TODO Auto-generated method stub
		 userDao.update(user);
	}
	
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByUsername(username);
	}

	public boolean isUpdatedEmailValid(String email, String username) {
		// TODO Auto-generated method stub
		return userDao.isUpdatedEmailValid(email, username);
	}

}

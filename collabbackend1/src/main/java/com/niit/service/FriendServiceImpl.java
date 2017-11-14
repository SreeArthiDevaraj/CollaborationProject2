package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.FriendDao;
import com.niit.model.Friend;
import com.niit.model.User;
@Service
public class FriendServiceImpl implements FriendService {
@Autowired
private FriendDao friendDao;
	public List<User> listOfSuggestedUsers(String username) {
		// TODO Auto-generated method stub
		return friendDao.listOfSuggestedUsers(username);
	}
	public void friendRequest(Friend friend) {
		// TODO Auto-generated method stub
		friendDao.friendRequest(friend);
	}
	public List<Friend> pendingRequests(String toId) {
		// TODO Auto-generated method stub
		return friendDao.pendingRequests(toId);
	}
	public void updateFriendRequest(Friend friend) {
		// TODO Auto-generated method stub
		friendDao.updatePendingRequest(friend);
		
	}
	public List<String> listOfFriends(String username) {
		// TODO Auto-generated method stub
		return friendDao.listOfFriends(username);
	}

}

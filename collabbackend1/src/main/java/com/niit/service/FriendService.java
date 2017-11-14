package com.niit.service;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendService {
	List <User> listOfSuggestedUsers(String username);

	void friendRequest(Friend friend);
	List<Friend> pendingRequests(String toId);

	void updateFriendRequest(Friend friend);
	List<String>listOfFriends(String username);


}

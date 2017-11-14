package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.model.Error;
import com.niit.model.Friend;
import com.niit.model.User;
import com.niit.service.FriendService;

@Controller
public class FriendController {
@Autowired
private FriendService friendService;

@RequestMapping(value="/getsuggestedusers",method=RequestMethod.GET)
public ResponseEntity<?> getSuggestedUsers(HttpSession session){
		String username=(String)session.getAttribute("username");
		 if(username==null){
			Error error=new Error(6,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		 List <User> suggestedUsers=friendService.listOfSuggestedUsers(username);
		 return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	}

@RequestMapping(value="/friendrequest/{toId}",method=RequestMethod.GET)
public ResponseEntity<?> friendRequest(@PathVariable String toId,HttpSession session){
		String username=(String)session.getAttribute("username");
		 if(username==null){
			Error error=new Error(6,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Friend friend= new Friend();
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('P');
		friendService.friendRequest(friend);
		 return new ResponseEntity<Friend>(friend,HttpStatus.OK);

	}
@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
public ResponseEntity<?> pendingRequests(HttpSession session){
	String username=(String)session.getAttribute("username");
	 if(username==null){
		Error error=new Error(6,"Unauthorized access...");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	List<Friend>pendingRequests =friendService.pendingRequests(username);
	return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
}
@RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
public ResponseEntity<?> updatePendingRequest(@RequestBody Friend friend,HttpSession session){
	String username=(String)session.getAttribute("username");
	 if(username==null){
		Error error=new Error(6,"Unauthorized access...");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	 System.out.println(friend.getFromId()+""+friend.getStatus());
	 friendService.updateFriendRequest(friend);
	 return new ResponseEntity<Friend>(friend,HttpStatus.OK);

}
@RequestMapping(value="/friendslist",method=RequestMethod.GET)
public ResponseEntity<?> listOfFriends(HttpSession session){
	String username=(String)session.getAttribute("username");
	if(username==null){
		Error error=new Error(5,"UnAuthorized Access..");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	List<String> friends=friendService.listOfFriends(username);
	return new ResponseEntity<List<String>>(friends,HttpStatus.OK);
}


}

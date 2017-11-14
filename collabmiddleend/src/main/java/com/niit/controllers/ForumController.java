package com.niit.controllers;

import java.util.Date;
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

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Error;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.ForumRequest;
import com.niit.model.User;
import com.niit.service.ForumPostService;

import com.niit.service.UserService;

@Controller
public class ForumController {
	@Autowired
	private ForumPostService forumpostService;
	
	 @Autowired
	 private UserService userService;
	 @RequestMapping(value="/SaveForum",method=RequestMethod.POST)
	 public ResponseEntity<?> saveForum(@RequestBody Forum forum,HttpSession session){
	 String username=(String)session.getAttribute("username");
	 	 if(username==null){
	 		Error error=new Error(6,"Unauthorized access...");
	 		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	 	}
	 	
	 	forum.setPostedOn(new Date());
	 	User postedBy=userService.getUserByUsername(username);
	 	forum.setPostedBy(postedBy);
	 	try{
	 		forumpostService.saveForum(forum);
			System.out.println(" Forum Post created"+ forum.getForumTitle());

	 		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	 	}catch(Exception e){
	 		Error error=new Error(9,"Unable to save forum post details...");
	 		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	 	}
	 	
	 }
	 
	 
	 @RequestMapping(value="/getforums/{approved}",method=RequestMethod.GET)
	 public ResponseEntity<?> getForums(@PathVariable int approved,HttpSession session){
		 String username=(String)session.getAttribute("username");
		 if(username==null){
			Error error=new Error(6,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		 List<Forum> forums=forumpostService.getForums(approved);
			return new ResponseEntity<List<Forum>>(forums,HttpStatus.OK);

	 }
	 @RequestMapping(value="/getforumbyid/{id}",method=RequestMethod.GET)
	 public ResponseEntity<?> getForumById(@PathVariable int id,HttpSession session){
		 String username=(String)session.getAttribute("username");
		 if(username==null){
			Error error=new Error(6,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		 Forum forum=forumpostService.getForumById(id);
		 return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	 }


	 @RequestMapping(value="/updateForum",method=RequestMethod.PUT)
	 public ResponseEntity<?> updateForum(@RequestBody Forum forum,HttpSession session){
		 String username=(String)session.getAttribute("username");
		 if(username==null){
			Error error=new Error(6,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		 if(!forum.isApproved()&& forum.getRejectionReason()==null)
			 forum.setRejectionReason("Not Mentioned");
		 forumpostService.updateForum(forum);
		 System.out.println("forum updated");
		 return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	 }
	
	 @RequestMapping(value="/joinforum/{id}",method=RequestMethod.POST)
	public ResponseEntity<?> joinReq(@PathVariable int id,HttpSession session) {
		 String username=(String)session.getAttribute("username");
		 if(username==null){
			Error error=new Error(6,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		ForumRequest forreq = new ForumRequest();
		Forum forum = null;
		forreq.setForumid(id);
		forreq.setJoinuser(username);
		forreq.setForumTitle(forumpostService.getForumById(id).getForumTitle());
		forumpostService.saveJoinRequest(forreq);
		return new ResponseEntity<ForumRequest>(forreq, HttpStatus.OK);
		
	}
	 @RequestMapping(value="/addCommentforforum",method=RequestMethod.POST)
	 public ResponseEntity<?> addForumComment(@RequestBody ForumComment forumComment,HttpSession session){
		 String username=(String)session.getAttribute("username");
		 if(username==null){
			Error error=new Error(6,"Unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		 User user=userService.getUserByUsername(username);
		 forumComment.setCommentedBy(user);
		 forumComment.setCommentedOn(new Date());
		try {
			
		 forumpostService.addForumComment(forumComment);
		 return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		}catch(Exception e){
			Error error=new Error(9,"Unable to post comments...");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 
	 }
	 @RequestMapping(value="/getcommentsforforum/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> getComments(@PathVariable int id,HttpSession session){
			String username=(String)session.getAttribute("username");
			 if(username==null){
				Error error=new Error(6,"Unauthorized access...");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			 List<ForumComment> forumComments=forumpostService.getForumComments(id);
			 return new ResponseEntity<List<ForumComment>>(forumComments,HttpStatus.OK);
	 }
	 
	 
	 
	 @RequestMapping(value="/getjoinreq/{status}",method=RequestMethod.GET)
		public ResponseEntity<?> getJoinRequests(@PathVariable("status") int joinstatus,HttpSession session){
			if(session.getAttribute("username")==null){
				Error error = new Error(5, "Unauthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			List<ForumRequest> forumreq = forumpostService.getJoinRequests(joinstatus);
			return new ResponseEntity<List<ForumRequest>>(forumreq,HttpStatus.OK);
			
		}
	 

		@RequestMapping(value="/acceptjoinreq/{reqid}",method=RequestMethod.POST)
		public ResponseEntity<?> acceptJoinReq(@PathVariable("reqid") int reqid,HttpSession session){
			if(session.getAttribute("username")==null){
				Error error = new Error(5, "Unauthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			ForumRequest forreq = forumpostService.getRequestById(reqid);
			forreq.setReqstatus(true);
			forumpostService.acceptJoinReq(forreq);
			return new ResponseEntity<ForumRequest>(forreq,HttpStatus.OK);
		}
		
		@RequestMapping(value="/isparticipant/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> isParticipant(@PathVariable int id,HttpSession session){
			if(session.getAttribute("username")==null){
				Error error = new Error(5, "Unauthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}	
			String userapp =  (String) session.getAttribute("username");
			ForumRequest forreq = forumpostService.isParticipantUser(id,userapp);
			return new ResponseEntity<ForumRequest>(forreq,HttpStatus.OK);
		}
}

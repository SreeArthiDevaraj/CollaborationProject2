package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.ForumPostDao;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.ForumRequest;
@Service
public class ForumPostServiceImpl implements ForumPostService {
@Autowired
private ForumPostDao forumpostDao;

public void saveForum(Forum forum) {
	// TODO Auto-generated method stub
forumpostDao.saveForum(forum);
}

public List<Forum> getForums(int approved) {
	// TODO Auto-generated method stub
	return forumpostDao.getForums(approved);
}

public Forum getForumById(int id) {
	// TODO Auto-generated method stub
	return forumpostDao.getForumById(id);
}

public void updateForum(Forum forum) {
	// TODO Auto-generated method stub
	forumpostDao.updateForum(forum);
}

public void saveJoinRequest(ForumRequest forreq) {
	// TODO Auto-generated method stub
	forumpostDao.saveJoinRequest(forreq);
}

public void addForumComment(ForumComment forumComment) {
	// TODO Auto-generated method stub
	forumpostDao.addForumComment(forumComment);
}

public List<ForumComment> getForumComments(int forumId) {
	// TODO Auto-generated method stub
	return forumpostDao.getForumComments(forumId);
}

public List<ForumRequest> getJoinRequests(int joinstatus) {
	// TODO Auto-generated method stub
	return forumpostDao.getJoinRequests(joinstatus);
}

public void acceptJoinReq(ForumRequest forreq) {
	// TODO Auto-generated method stub
	forumpostDao.acceptJoinReq(forreq);
}

public ForumRequest getRequestById(int reqid) {
	// TODO Auto-generated method stub
	return forumpostDao.getRequestById(reqid);
}

public ForumRequest isParticipantUser(int id, String userapp) {
	// TODO Auto-generated method stub
	return forumpostDao.isParticipantUser(id,userapp);
}


}
	



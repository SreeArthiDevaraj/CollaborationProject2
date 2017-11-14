package com.niit.dao;

import java.util.List;

import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.ForumRequest;

public interface ForumPostDao {
	void saveForum(Forum forum);
	List<Forum> getForums(int approved);
	Forum getForumById(int id);
	void updateForum(Forum forum);
	void saveJoinRequest(ForumRequest forreq);
	void addForumComment(ForumComment forumComment);
	List<ForumComment> getForumComments(int forumId);
	List<ForumRequest> getJoinRequests(int joinstatus);

	void acceptJoinReq(ForumRequest forreq);
	ForumRequest getRequestById(int reqid);
	ForumRequest isParticipantUser(int id, String userapp);
}

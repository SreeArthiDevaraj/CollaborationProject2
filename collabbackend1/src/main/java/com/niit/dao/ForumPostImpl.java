package com.niit.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.ForumRequest;
@Repository
@Transactional
public class ForumPostImpl implements ForumPostDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	

	public void saveForum(Forum forum) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(forum);
		System.out.println(" Forum Post created"+ forum.getForumTitle());
	}



	public List<Forum> getForums(int approved) {
		Session session = sessionFactory.getCurrentSession();
		String queryStr="";
		if(approved==1)
			queryStr="from Forum where approved="+approved;
		else
			queryStr="from Forum where rejectionReason is null and approved="+approved;
		Query query=session.createQuery(queryStr);
		return query.list();
	}



	public Forum getForumById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Forum forum=(Forum)session.get(Forum.class, id);
		return forum;
	}



	public void updateForum(Forum forum) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(forum);
	}



	public void saveJoinRequest(ForumRequest forreq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(forreq);
	}



	public void addForumComment(ForumComment forumComment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(forumComment);
	}



	public List<ForumComment> getForumComments(int forumId) {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ForumComment where forum.id="+forumId);
		return query.list();
	}



	public List<ForumRequest> getJoinRequests(int joinstatus) {
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from ForumRequest where reqstatus = ?");
		query.setInteger(0, joinstatus);
		List<ForumRequest> forreq = query.list();
		return forreq;
	}



	public void acceptJoinReq(ForumRequest forreq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(forreq);
	}



	public ForumRequest getRequestById(int reqid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from ForumRequest where reqid = ?");
		query.setInteger(0,reqid);
		ForumRequest forreq = (ForumRequest) query.uniqueResult();
		return forreq;
	}



	public ForumRequest isParticipantUser(int id, String userapp) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from ForumRequest where reqid = ? and joinuser=?");
		query.setInteger(0, id);
		query.setString(1, userapp);
		ForumRequest forreq = (ForumRequest) query.uniqueResult();

		return forreq;
	}



	
}

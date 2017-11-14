package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.User;
@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<User> listOfSuggestedUsers(String username) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery(" select *from People_Name where username in (select username from People_Name where username!=? minus (select fromId from friend where toId=? union select toId from friend where fromId=?))");
		query.setString(0,username);
		query.setString(1,username);
		query.setString(2,username);
		query.addEntity(User.class);
		List<User> suggestedUsers=query.list();
		return suggestedUsers;
	}
	public void friendRequest(Friend friend) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);

	}
	public List<Friend> pendingRequests(String toId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where toId=? and status='P'");
		query.setString(0,toId);
		return query.list();
	}
	public void updatePendingRequest(Friend friend) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A')
			session.update(friend);
		else
			session.delete(friend);
	}
	
	public List<String> listOfFriends(String username) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		 SQLQuery sqlQuery1=session.createSQLQuery("select fromId from friend where toId=? and status='A'")
				 .addScalar("fromId", StandardBasicTypes.STRING);
		 sqlQuery1.setString(0, username);
		List<String>list1=sqlQuery1.list();
		System.out.println("RESULT OF 1st QUERY"+ list1);

		SQLQuery sqlQuery2=session.createSQLQuery("select toId from friend where fromId=? and status='A'")
				 .addScalar("toId", StandardBasicTypes.STRING);
		sqlQuery2.setString(0, username);
		List<String>list2=sqlQuery2.list();
		System.out.println("RESULT OF 2nd QUERY" +list2);
		list1.addAll(list2);
		System.out.println("RESULT OF list1 + list2" +list1);

		return list1;

	}
	
	

}

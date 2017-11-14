package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean registerUser(User user) {
		
			Session session=sessionFactory.getCurrentSession();
			try
			{
				session.save(user);
				return true;	
			} 
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return false;

		}
	}
	public boolean isUsernamevalid(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, username);
		if(user==null)
			return true;
		else
		return false;
	}
	
	public boolean isEmailvalid(String email) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email=?");
		query.setString(0, email);
		User user=(User)query.uniqueResult();
		if(user==null)
			return true;
		else
		return false;
	}
	
	public User login(User user) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where username=? and password=?");
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
		user=(User)query.uniqueResult();
		return user;
	}
	
	public void update(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		// TODO Auto-generated method stub
		
	}
	
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class,username);
		return user;
	}
	public boolean isUpdatedEmailValid(String email,String username){
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email=? and username!=? ");
		query.setString(0,email);
		query.setString(1,username);
		User user=(User)query.uniqueResult();
		if(user==null)
			return true;
		else
			return false;
		
	}
}

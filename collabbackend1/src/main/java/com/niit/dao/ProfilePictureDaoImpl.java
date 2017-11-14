package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ProfilePicture;
@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {
	@Autowired
	SessionFactory sessionFactory;
	public void uploadProfilePic(ProfilePicture profilePicture) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);
	}
	public ProfilePicture getProfilePic(String username) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilePic=(ProfilePicture)session.get(ProfilePicture.class, username);
		return profilePic;
	}

}

package com.niit.service;

import com.niit.model.ProfilePicture;

public interface ProfilePictureService {
	void uploadProfilePic(ProfilePicture profilePicture);
	ProfilePicture getProfilePic(String username);


}

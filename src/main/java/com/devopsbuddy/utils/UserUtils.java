package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.User;

public class UserUtils {
	
	private UserUtils(){
		
	}
	
	
	public static User createBasicUser() {
		
		User basicUser = new User();
		basicUser.setUserName("user");
		basicUser.setFirstName("basic");
		basicUser.setLastName("user");
		basicUser.setDescription("Basic user desc");
		basicUser.setEmail("basic@user.com");
		basicUser.setPassword("password");
		basicUser.setPhoneNo("7897897894");
		basicUser.setEnabled(true);
				
		return basicUser;
		
	}

}

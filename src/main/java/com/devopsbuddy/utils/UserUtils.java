package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.User;

public class UserUtils {
	
	private UserUtils(){
		
	}
	
	
	public static User createBasicUser(String username, String email) {
		
		User basicUser = new User();
		basicUser.setUserName(username);
		basicUser.setFirstName("basic");
		basicUser.setLastName("user");
		basicUser.setDescription("Basic user desc");
		basicUser.setEmail(email);
		basicUser.setPassword("password");
		basicUser.setPhoneNo("7897897894");
		basicUser.setEnabled(true);
				
		return basicUser;
		
	}

}

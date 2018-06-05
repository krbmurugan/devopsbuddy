package com.devopsbuddy.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.persistence.repositoires.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{
	
	
	private static final Logger log = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepo.findByUserName(username);
		if(null == user	) {
			throw new UsernameNotFoundException("User name not foune"+ username);
		}
		
		return user;
				
		
	}

}

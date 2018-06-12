package com.devopsbuddy.backend.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.devopsbuddy.backend.persistence.domain.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.persistence.repositoires.PasswordResetTokenRepository;
import com.devopsbuddy.backend.persistence.repositoires.UserRepository;

@Service
public class PasswordResetTokenService {
	
	
	private static final Logger log = LoggerFactory.getLogger(PasswordResetTokenService.class);
	
	@Value("${token.expiration.length.minutes}")
	private static int TOKEN_EXP_TIME_IN_MINS;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	
	
	public PasswordResetToken createPRTforEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		PasswordResetToken passwordResetToken = null;
		
		if(null != user) {
			log.info("User found for email id:"+ email);
			String token = UUID.randomUUID().toString();
			LocalDateTime expTime = LocalDateTime.now();
			passwordResetToken = new PasswordResetToken(token, user, expTime, TOKEN_EXP_TIME_IN_MINS);
			passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
		}
		else {
			log.info("User NOT found for email id:"+ email);

			
		}
		
		
		return passwordResetToken;
		
	}


}

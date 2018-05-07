package com.devopsbuddy.backend.service;

import org.springframework.mail.SimpleMailMessage;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;

public interface EmailService {
	
	public void sendFeedbackEmail (FeedbackPojo feedbackPojo);
	
	public void sendGenericEmail(SimpleMailMessage simpleMailMessage);
	

}

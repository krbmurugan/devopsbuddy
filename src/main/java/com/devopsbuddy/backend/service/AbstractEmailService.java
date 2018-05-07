package com.devopsbuddy.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.to.address}")
	String DEFAULT_TO_ADDRESS;


	protected SimpleMailMessage prepareSimpleEmailMessage (FeedbackPojo feedbackPojo) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setTo(DEFAULT_TO_ADDRESS);
		mailMessage.setFrom(feedbackPojo.getEmail());
		mailMessage.setSubject("Feedback from "+feedbackPojo.getFirstName());
		mailMessage.setText(feedbackPojo.getFeedback());
		
		return mailMessage;		
	}
	

	@Override
	public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {

		sendGenericEmail(prepareSimpleEmailMessage(feedbackPojo));
		
	}

	 

}

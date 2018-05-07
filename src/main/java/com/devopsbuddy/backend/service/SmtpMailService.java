package com.devopsbuddy.backend.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpMailService extends AbstractEmailService{
	
	
	private static final Logger log = LogManager.getLogger(SmtpMailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Override
	public void sendGenericEmail(SimpleMailMessage simpleMailMessage) {
		// TODO Auto-generated method stub
		log.info("Entering SMTP email message...");
		mailSender.send(simpleMailMessage);
		

		
	}

}

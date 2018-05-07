package com.devopsbuddy.backend.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{
	
	

private static final Logger log = LogManager.getLogger(MockEmailService.class);	


	@Override
	public void sendGenericEmail(SimpleMailMessage simpleMailMessage) {
		// TODO Auto-generated method stub
		log.info("Printing Mock email...");

		

		
		


		
	}

}

package com.devopsbuddy.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.MockEmailService;
import com.devopsbuddy.backend.service.SmtpMailService;

@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/config/application-dev.properties")
public class DevConfig {
	
	private static final Logger log = LogManager.getLogger(DevConfig.class);
	
	@Bean
	public EmailService emailServiceNew() {
		log.info("inside dev config...");

		return new MockEmailService();
	}
	
	 

}

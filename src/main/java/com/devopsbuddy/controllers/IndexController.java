package com.devopsbuddy.controllers;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	 private static final Logger logger = LogManager.getLogger(IndexController.class);
	@RequestMapping("/contact.go")
	public String launchContacts() {
		logger.info("inside launchContacts");
		logger.debug("inside launchContacts--debug");
		System.out.println("adfasdf");
		
		return "contact";
	}
	
	
}

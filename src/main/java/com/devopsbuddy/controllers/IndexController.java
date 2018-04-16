package com.devopsbuddy.controllers;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devopsbuddy.web.i18n.I18nService;

@Controller
public class IndexController {
	
	@Autowired
	I18nService i18nService;
	
	 private static final Logger logger = LogManager.getLogger(IndexController.class);
	@RequestMapping("/contact.go")
	public String launchContacts() {
		logger.info("inside launchContacts");
		logger.debug("inside launchContacts--debug");
		System.out.println("adfasdf");
		logger.info("Message from properties.."+i18nService.getMessage("id2"));
		return "contact";
	}
	
	
}

package com.devopsbuddy.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devopsbuddy.backend.persistence.domain.FeedBack;
import com.devopsbuddy.backend.persistence.repositoires.FeedBackRepository;
import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.web.domain.frontend.FeedbackPojo;

@Controller
public class FeedbackController {
	
	 private static final Logger logger = LogManager.getLogger(FeedbackController.class);

	
	private static final String FEEDBACK_MODEL_KEY = "feedback";
	private static final String CONTACT_US_VIEW_NAME = "contact/contact";
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private FeedBackRepository feedbackRepo;

	@RequestMapping(value = "/contact", method=RequestMethod.GET)
	public String contactGet(ModelMap model) {
		logger.info("inside Contact ");
		
		FeedbackPojo feedbackPojo = new FeedbackPojo();
		
		model.addAttribute(FeedbackController.FEEDBACK_MODEL_KEY, feedbackPojo);
		return FeedbackController.CONTACT_US_VIEW_NAME;
	}
	
	@RequestMapping(value="/contact", method=RequestMethod.POST)
	public String contactPost(@ModelAttribute(FEEDBACK_MODEL_KEY) FeedbackPojo fp)
	{
		logger.info("inside contact method - post" + fp);
		
		feedbackRepo.save(new FeedBack(fp));
		
		emailService.sendFeedbackEmail(fp);
		
		
		
		return FeedbackController.CONTACT_US_VIEW_NAME;
	}
	

}

package com.devopsbuddy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayloadController {
	
	private String PAYLOAD_VIEW_NAME="payload/payload";
	
	@RequestMapping("/payload")
	public String viewPayload() {
		
		return PAYLOAD_VIEW_NAME;
	}

}

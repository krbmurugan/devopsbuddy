package com.devopsbuddy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldControllers {
	
	@RequestMapping("/index.go")
	public String sayHello() {
		
		return "index";
	}
	
	@RequestMapping("/first.go")
	public String sayHello1() {
		
		return "first";
	}

}

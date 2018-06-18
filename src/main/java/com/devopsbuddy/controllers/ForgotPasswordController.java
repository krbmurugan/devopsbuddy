package com.devopsbuddy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devopsbuddy.backend.persistence.domain.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.service.PasswordResetTokenService;
import com.devopsbuddy.backend.service.UserService;

@Controller
public class ForgotPasswordController {
	
	
	private static final Logger log = LoggerFactory.getLogger(ForgotPasswordController.class);
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PasswordResetTokenService prtServ;
	
	
	
	public static final String confirmEmailURL = "/forgotpassword";
	public static final String CHANGE_PASSWORD_PATH = "/changepassword";
	public static final String confirmEmailView = "forgotpassword/emailForm";
	public static final String CHANGE_PASSWORD_VIEW="forgotpassword/changePassword";
	
	public static final String updPwdGet="updatePwdUrl";
	public static final String passwordUpdStatus = "passwordUpdStatus";

	
	
	@RequestMapping(value=confirmEmailURL, method = RequestMethod.GET)
	public String forgotPaswordGet() {
		log.info("Inside forgot password..");
		return confirmEmailView;
	}
	
	@RequestMapping(value=confirmEmailURL, method=RequestMethod.POST)
	public String forgotPasswordPost(@RequestParam("email") String email, ModelMap modelmap, HttpServletRequest request) {
		log.info("Printing email:"+email);
		
		String e = email;
		if(userServ.isUserEmailExists(e)) {
			log.info("User exists");
			modelmap.addAttribute("isMailSent", true);
			PasswordResetToken prt = prtServ.createPRTforEmail(email);
			String pwdResetLink = getPwdResetURL(request,prt);
			log.info("pwdResetLink:"+pwdResetLink);
		}
		else {
			log.info("User Does not Exist");
			modelmap.addAttribute("userNotExist", true);
		}

		return confirmEmailView;
		
	}
	
	@RequestMapping(value=CHANGE_PASSWORD_PATH, method=RequestMethod.GET)
	public String updatePasswordGET(@RequestParam("id")String userID, @RequestParam("token")String token, ModelMap model) {
		log.info("Inside update password Get:"+userID+":"+token);
		PasswordResetToken prt  = null;
		try {
			prt  = prtServ.getPRTforUserAndToken(Integer.parseInt(userID), token);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			log.info("Inside illegal argument exception");

//			e.printStackTrace();
			model.addAttribute(updPwdGet, "false");
			return CHANGE_PASSWORD_VIEW;
		}
		User user = prt.getUser();
		
		 model.addAttribute("principalId", userID);

	        // OK to proceed. We auto-authenticate the user so that in the POST request we can check if the user
	        // is authenticated
	        Authentication auth = new UsernamePasswordAuthenticationToken(
	                user, null, user.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(auth);

		return CHANGE_PASSWORD_VIEW;
	}
	
	@RequestMapping(value=CHANGE_PASSWORD_PATH, method=RequestMethod.POST)
	public String updatePasswordPOST(@RequestParam("principal_id")String userId, @RequestParam("password1")String password1, @RequestParam("password2")String password2, ModelMap model) {
		log.info("Inside update password POST:"+userId+":"+password1 +"::"+password2);
		if(password1.equals(password2)) {
			log.info("Inside valid password combo");

			userServ.updateUserPassword(Integer.parseInt(userId), password2);
			}
		model.addAttribute(passwordUpdStatus, "false");
		return CHANGE_PASSWORD_VIEW;
	}
	
	
	private String getPwdResetURL(HttpServletRequest request, PasswordResetToken prt) {
		String passwordResetUrl =
                request.getScheme() +
                        "://" +
                        request.getServerName() +
                        ":" +
                        request.getServerPort() +
                        request.getContextPath() +
                        ForgotPasswordController.CHANGE_PASSWORD_PATH +
                        "?id=" +
                        prt.getUser().getId() +
                        "&token=" +
                        prt.getToken();
		return passwordResetUrl;
	}

}

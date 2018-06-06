package com.devopsbuddy;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devopsbuddy.backend.persistence.domain.Role;
import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.persistence.domain.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(DevopsbuddyApplication.class);
	
	@Value("${admin.username}")
	private String adminUsername;
	
	@Value("${admin.password}")
	private String adminPassword;
	
	@Value("${admin.email}")
	private String adminEmail;
	
	@Autowired
	UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Set<UserRole> userRoles = new HashSet<UserRole>();
		User basicUser = UserUtils.createBasicUser(adminUsername, adminEmail);
		basicUser.setPassword(adminPassword);
		
		userRoles.add(new UserRole(new Role(RoleEnum.BASIC), basicUser));
		User createdUser = userService.createUser(basicUser, PlanEnum.BASIC, userRoles);	
		log.info("User Created: UserName: "+createdUser.getFirstName() +" | userID: "+createdUser.getId());

		
	}
}

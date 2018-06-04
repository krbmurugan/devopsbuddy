package com.devopsbuddy.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devopsbuddy.backend.persistence.domain.Role;
import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.persistence.domain.UserRole;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceIntgTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceIntgTest.class);

	
	@Autowired
	UserService userService;
	
	@Test
	public void testCreateUser() throws Exception{
		Set<UserRole> userRoles = new HashSet<UserRole>();
		User basicUser = UserUtils.createBasicUser();
		userRoles.add(new UserRole(new Role(RoleEnum.BASIC), basicUser));
		log.info("BEfore Calling create userservice..");


		User createdUser = userService.createUser(basicUser, PlanEnum.BASIC, userRoles);
		log.info("UserID"+createdUser.getId());
		Assert.assertNotNull(createdUser);
		
		
	}

}

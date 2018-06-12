package com.devopsbuddy.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
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
public class UserServiceIntgTest extends AbstractIntgTest{
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceIntgTest.class);

	
	@Autowired
	UserService userService;
	
	@Rule
	public TestName testName = new TestName();
	
	@Test
	public void testCreateUser() throws Exception{
		String username = testName.getMethodName();
		String email = testName.getMethodName()+"@gmail.com";
		
		Set<UserRole> userRoles = new HashSet<UserRole>();
		
		User basicUser = UserUtils.createBasicUser(username, email);
		userRoles.add(new UserRole(new Role(RoleEnum.BASIC), basicUser));
		log.info("BEfore Calling create userservice..");


		User createdUser = userService.createUser(basicUser, PlanEnum.BASIC, userRoles);
		log.info("UserID"+createdUser.getId());
		Assert.assertNotNull(createdUser);
			
	}
	
	@Test
	public void testUpdateUserPwd() throws Exception{
		User user = createNewUser(testName.getMethodName());
		log.info("user id and password before update:"+user.getId()+":"+user.getPassword());
		userService.updateUserPassword(user.getId(), "testpassword");
		
		User retrievedUser = userRepository.findByUserName(user.getUserName());
		log.info("user id and password after update:"+retrievedUser.getId()+":"+retrievedUser.getPassword());

	}

}

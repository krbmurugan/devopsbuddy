package com.devopsbuddy.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.devopsbuddy.backend.persistence.domain.Plan;
import com.devopsbuddy.backend.persistence.domain.Role;
import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.persistence.domain.UserRole;
import com.devopsbuddy.backend.persistence.repositoires.FeedBackRepository;
import com.devopsbuddy.backend.persistence.repositoires.PlanRepository;
import com.devopsbuddy.backend.persistence.repositoires.RoleRepository;
import com.devopsbuddy.backend.persistence.repositoires.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositoriesIntgTest {
	
	private static final Logger log = LoggerFactory.getLogger(RepositoriesIntgTest.class);

	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FeedBackRepository feedbackRepo;
	
	
	@Before
	public void init() {
		
		Assert.notNull(planRepository);
		Assert.notNull(roleRepository);
		Assert.notNull(userRepository);
		
		
	}
	
	@Rule
	public TestName testName = new TestName();
	
	@Test
	public void testCreateNewPlan() throws Exception{
		Plan basicPlan = createPlan(PlanEnum.BASIC);
		Plan proPlan = createPlan(PlanEnum.PRO);
		Plan prePlan = createPlan(PlanEnum.PRE);
		planRepository.save(basicPlan);
		planRepository.save(proPlan);
		planRepository.save(prePlan);
		boolean isExists = planRepository.existsById(PlanEnum.BASIC.getId());
		Assert.isTrue(isExists);
		
		long count = planRepository.count();
		org.junit.Assert.assertEquals(3, count);
		
	}
	
	@Test 
	public void validateTotalPlan() throws Exception{
		Plan basicPlan = createPlan(PlanEnum.BASIC);
		Plan proPlan = createPlan(PlanEnum.PRO);
		Plan prePlan = createPlan(PlanEnum.PRE);
		planRepository.save(basicPlan);
		planRepository.save(proPlan);
		planRepository.save(prePlan);
		 
		
		long count = planRepository.count();
		org.junit.Assert.assertEquals(3, count);
		
	}
	
	@Test
	public void validateTotalFeedback() throws Exception{
		System.out.println("Total users: "+feedbackRepo.count());
		org.junit.Assert.assertEquals(0, feedbackRepo.count());		
		
	}
	
	@Test
	public void testCreateRole() throws Exception{
		
		Role userRole = createRole(RoleEnum.BASIC);
		roleRepository.save(userRole);
		
		Role retrievedRole = roleRepository.findById(RoleEnum.BASIC.getRoleId()).get();
		Assert.notNull(retrievedRole);
		
	}
	
	@Test
	public void testCreateNewUser() throws Exception{
		String username = testName.getMethodName();
		String email = testName.getMethodName()+"@gmail.com";
		
		User newlyCreatedUser = createNewUser(username, email);
		log.info("UserID After creation: " + newlyCreatedUser.getId());
		Assert.notNull(newlyCreatedUser);
		
		
		
	}
	
	@Test
	public void testDeleteUser() throws Exception{
		String username = testName.getMethodName();
		String email = testName.getMethodName()+"@gmail.com";
		User newlyCreatedUser = createNewUser(username, email);
		log.info("User ID in testDeleteUser::"+newlyCreatedUser.getId());
		

		userRepository.delete(newlyCreatedUser);
		boolean isUserExists = userRepository.existsById(newlyCreatedUser.getId());
		Assert.isTrue(!isUserExists);
		
	}
	
	 private Plan createPlan(PlanEnum plansEnum) {
	        return new Plan(plansEnum);
	    }
	 
	 private Role createRole(RoleEnum roleEnum) {
		 return new Role(roleEnum);
	 }
	 
	 public User createNewUser(String username, String email) {

			
			User user = UserUtils.createBasicUser(username,  email);
			
			Plan plan = createPlan(PlanEnum.BASIC);
			plan=planRepository.save(plan);
			
			user.setPlan(plan);
			
			Role role = createRole(RoleEnum.BASIC);
			roleRepository.save(role);
			
			Set<UserRole> userRoles = new HashSet<UserRole>();
			UserRole userRole = new UserRole( role,user);
			userRoles.add(userRole);
			
			user.getUserRoles().addAll(userRoles);
			
			for(UserRole ur : userRoles ) {
				roleRepository.save(ur.getRole());
			}
			log.info("UserID Before Creation11: " +user.getId());
			
			userRepository.save(user);
			return user;
	 }

	     
	
	
	
	

}

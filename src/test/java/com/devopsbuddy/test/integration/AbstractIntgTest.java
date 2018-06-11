package com.devopsbuddy.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.devopsbuddy.backend.persistence.domain.Plan;
import com.devopsbuddy.backend.persistence.domain.Role;
import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.persistence.domain.UserRole;
import com.devopsbuddy.backend.persistence.repositoires.FeedBackRepository;
import com.devopsbuddy.backend.persistence.repositoires.PasswordResetTokenRepository;
import com.devopsbuddy.backend.persistence.repositoires.PlanRepository;
import com.devopsbuddy.backend.persistence.repositoires.RoleRepository;
import com.devopsbuddy.backend.persistence.repositoires.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;

public abstract class AbstractIntgTest {
	
	
	private static final Logger log = LoggerFactory.getLogger(AbstractIntgTest.class);

	@Autowired
	protected PlanRepository planRepository;
	
	@Autowired
	protected RoleRepository roleRepository;
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected FeedBackRepository feedbackRepo;
	
	@Autowired
	protected PasswordResetTokenRepository passwordResetTokenRepository;
	
	
	protected Plan createPlan(PlanEnum plansEnum) {
        return new Plan(plansEnum);
    }
 
 protected Role createRole(RoleEnum roleEnum) {
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
 
 public User createNewUser(String testName) {
	 return createNewUser(testName, testName+"@gmail.com");
 }

}

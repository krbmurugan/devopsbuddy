package com.devopsbuddy.backend.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devopsbuddy.backend.persistence.domain.Plan;
import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.persistence.domain.UserRole;
import com.devopsbuddy.backend.persistence.repositoires.PlanRepository;
import com.devopsbuddy.backend.persistence.repositoires.RoleRepository;
import com.devopsbuddy.backend.persistence.repositoires.UserRepository;
import com.devopsbuddy.enums.PlanEnum;

@Service
@Transactional(readOnly=true)
public class UserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Transactional
	public User createUser(User user, PlanEnum planEnum, Set<UserRole> userRoles) {
		log.info("inside the create user service");
		Plan plan = new Plan(planEnum);
		Plan savedPlan=null;
		if(null!=planRepository.findById(plan.getId())) {
				
			log.info("Plan does not exist..Name: " + plan.getName() +" | ID: "+plan.getId());

			savedPlan= planRepository.save(plan);
			
			log.info("Plan id: "+planRepository.findById(1).get().getId());

		}
		user.setPlan(savedPlan);
		
		 for (UserRole ur : userRoles) {
	            roleRepository.save(ur.getRole());
	        }
		
		user.getUserRoles().addAll(userRoles);
		userRepository.save(user);
		log.info("User created success- ID:  "+user.getId());
		return user;				
	}
	
	

}

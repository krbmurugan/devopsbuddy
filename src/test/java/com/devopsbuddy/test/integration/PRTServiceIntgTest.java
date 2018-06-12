package com.devopsbuddy.test.integration;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devopsbuddy.backend.persistence.domain.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.User;
import com.devopsbuddy.backend.service.PasswordResetTokenService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PRTServiceIntgTest extends AbstractIntgTest{
	

	private static final Logger log = LoggerFactory.getLogger(PRTServiceIntgTest.class);
	
	 
	
	@Autowired
	private PasswordResetTokenService prtServ;
	
	@Rule
	public TestName testName = new TestName();
	
	@Test
	public void testCreateTokenService() throws Exception{
		User user = createNewUser(testName.getMethodName());
		String userEmail = user.getEmail();
		log.info("userEmail:"+userEmail);
		
		PasswordResetToken prt = prtServ.createPRTforEmail(userEmail);
		log.info("PRT Details:"+prt);

		Assert.assertNotNull(prt);
	}
	
}

package com.devopsbuddy.test.integration;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devopsbuddy.backend.persistence.domain.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PRTRepoIntgTest extends AbstractIntgTest{
	
	
	private static final Logger log = LoggerFactory.getLogger(PRTRepoIntgTest.class);

	
	@Value("${token.expiration.length.minutes}")
	private int expirationTimeinMins;

	@Rule
	public TestName testName = new TestName();
	
	
	
	@Test
	public void testTokenExpTime() throws Exception{
		User user = createNewUser(testName.getMethodName());
		log.info("expirationTimeinMins::"+expirationTimeinMins);
		Assert.assertNotNull(user);
		
		LocalDateTime dateTime = LocalDateTime.now(Clock.systemUTC());
		String token = UUID.randomUUID().toString();
		
		LocalDateTime expectedExpiry = dateTime.plusMinutes(expirationTimeinMins);
		
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, dateTime, expirationTimeinMins);
		PasswordResetToken savedPRT = passwordResetTokenRepository.save(passwordResetToken);
		
		Assert.assertNotNull(savedPRT);
		LocalDateTime actualExpiry = savedPRT.getExpiryDate();
		
		log.info("printing expected and actual::"+expectedExpiry.toString() +":::"+actualExpiry.toString());
		Assert.assertEquals(expectedExpiry, actualExpiry);

	}
	
	@Test
	public void testFindPRTbyUserId() throws Exception{
		User user = createNewUser(testName.getMethodName());
		int userId  = user.getId();
		log.info("UserID created::"+userId);
		
		LocalDateTime dateTime = LocalDateTime.now(Clock.systemUTC());
		String token = UUID.randomUUID().toString();
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, dateTime, expirationTimeinMins);
		PasswordResetToken savedPRT = passwordResetTokenRepository.save(passwordResetToken);
		passwordResetToken = new PasswordResetToken(UUID.randomUUID().toString(), user, dateTime, expirationTimeinMins);
		passwordResetTokenRepository.save(passwordResetToken);
		User savedUser = savedPRT.getUser();
		log.info(savedPRT.toString());
		Set<PasswordResetToken> retievedPRT = passwordResetTokenRepository.findAllByUserId(savedUser.getId());
		log.info("list of PRT for the user: "+retievedPRT.size());
		
		Assert.assertEquals(2, retievedPRT.size());
		
	}
	
	@Test
	public void testFindPRTbyToken() throws Exception{
		User user = createNewUser(testName.getMethodName());
	 
		
		LocalDateTime dateTime = LocalDateTime.now(Clock.systemUTC());
		String token1 = UUID.randomUUID().toString();
		String token2 = UUID.randomUUID().toString();
		log.info("token1:\t"+token1);
		log.info("token2:\t"+token2);

		
		PasswordResetToken passwordResetToken = new PasswordResetToken(token1, user, dateTime, expirationTimeinMins);
		PasswordResetToken savedPRT1 = passwordResetTokenRepository.save(passwordResetToken);
		passwordResetToken = new PasswordResetToken(token2, user, dateTime, expirationTimeinMins);
		PasswordResetToken savedPRT2 = passwordResetTokenRepository.save(passwordResetToken);
		
		PasswordResetToken retrievedPRT = passwordResetTokenRepository.findByToken(token1);
		
		Assert.assertEquals(savedPRT1, retrievedPRT);
		
		
	}
	
	@Test
	public void testDeleteUserCascade() throws Exception{
		User user = createNewUser(testName.getMethodName());
		log.info("*** USER INFO ***"
				+ "\nuser id:\t"+user.getId()
				+ "\nuserName:\t"+user.getUserName());

		LocalDateTime dateTime = LocalDateTime.now(Clock.systemUTC());
		String token1 = UUID.randomUUID().toString();
		log.info("token1:\t"+token1);

		
		PasswordResetToken passwordResetToken = new PasswordResetToken(token1, user, dateTime, expirationTimeinMins);
		PasswordResetToken savedPRT1 = passwordResetTokenRepository.save(passwordResetToken);
		Set prts = new HashSet<PasswordResetToken>();
		prts.add(savedPRT1);
		user.setPwResetToken(prts);
		log.info("**PRT**"+savedPRT1);

		userRepository.delete(user);
		
		PasswordResetToken retrievedPRT = passwordResetTokenRepository.findByToken(savedPRT1.getToken());
		Assert.assertNull(retrievedPRT);
		
	}
	
	
	
	
	
	public void createPRT(User user) {
		
		
	}


}

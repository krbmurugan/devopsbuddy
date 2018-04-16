package com.devopsbuddy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devopsbuddy.web.i18n.I18nService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevopsbuddyApplicationTests {

	@Autowired
	private I18nService I18nService;
	 
	
	@Test
	public void testMessageByLocaleService() throws Exception{
		String expectedResult ="Home page template22";
		String messageId = "index.main.callout";
		
		String actual = I18nService.getMessage(messageId);
		Assert.assertEquals("Actual and expected dont match",expectedResult, actual);
		
	}

}
 
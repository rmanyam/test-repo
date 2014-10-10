package com.newsuk.common.utilities;

import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnvironmentProviderTest {

	EnvironmentProvider environmentProvider;
	String originalSystemProperty;
	
	@Before
	public void setup(){
		originalSystemProperty = System.getProperty("ENV");
	}
	
	public EnvironmentProviderTest(){
		Properties properties = new Properties();
		properties.setProperty("ENV", "unit-test");
			
		System.setProperties(properties);
		environmentProvider = new EnvironmentProvider();
	}

	//@Test
	public void testGetBaseUrl(){
		String baseUrl = environmentProvider.getBaseUrl();
		Assert.assertEquals(baseUrl, "http://www.unit-test.co.uk");
	}
	
	@After
	public void tearDown(){
		System.setProperty("ENV", originalSystemProperty);
	}
	
}

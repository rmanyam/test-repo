package com.newsuk.infrastructure.tests;

import java.util.HashMap;
import org.junit.Test;

public class OoyalaTest {

	private OoyalaAccess ooyalatest = new OoyalaAccess();
	
	@Test
	public void testputMetaData() {

		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("brand", "TNL");
		ooyalatest.putMetaData("1rMWF3ZDqkWoMQKIJhdpCZ7er5fbLMXD", parameter);
	}

	@Test
	public void testgetMetaData() {
		ooyalatest.getMetaData("1rMWF3ZDqkWoMQKIJhdpCZ7er5fbLMXD");
	}
}

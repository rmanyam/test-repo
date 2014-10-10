package com.newsuk.common.utilities;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class XmlReaderHelperTest {
	
	//@Test
	public void testXmlReaderHelperPass(){
		String xmlString = "<name>Dan</name>";
		XmlReaderHelper xpathHelper = new XmlReaderHelper(xmlString);
		
		String actualResult = xpathHelper.getValueAtPath("name");
		
		assertEquals("Dan", actualResult);
	}
	
	//@Test
	public void testXmlReaderHelperFail(){
		String xmlString = "<name>Dan</name>";
		XmlReaderHelper xpathHelper = new XmlReaderHelper(xmlString);
		
		String actualResult = xpathHelper.getValueAtPath("age");
		assertThat(actualResult, isEmptyOrNullString());
	}

}

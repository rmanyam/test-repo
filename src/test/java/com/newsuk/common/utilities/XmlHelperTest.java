package com.newsuk.common.utilities;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class XmlHelperTest {

	//@Test
	public void testEditXmlNode(){
		XMLHelper xmlHelper = new XMLHelper();
		
		String result = null;
		try {
			result = xmlHelper.editXMLNode("SoccerFeed/SoccerDocument/Competition/Country", "\\data\\OptaEvents\\teams.xml", "test-replace-text");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result.contains("<Country>test-replace-text</Country>"));
		
		
	}
	
	//@Test
	public void testEditXmlNodeMultiple(){
		XMLHelper xmlHelper = new XMLHelper();
		
		String result = null;
		
		List<String> xpathList = new ArrayList<String>();
		List<String>  textReplaceList = new ArrayList<String>();
		
		xpathList.add("/SoccerFeed/SoccerDocument/Competition/Country");
		xpathList.add("/SoccerFeed/SoccerDocument/Competition/Name");

		textReplaceList.add("test-replace-text-1");
		textReplaceList.add("test-replace-text-2");
		
		Map<String, String> editMap = new HashMap<String, String>();
		editMap.put("/SoccerFeed/SoccerDocument/Competition/Country", "test-replace-text-1");
		editMap.put("/SoccerFeed/SoccerDocument/Competition/Name", "test-replace-text-2");
		
		try {
			result = xmlHelper.editXMLNode("\\data\\OptaEvents\\teams.xml", editMap);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result.contains("<Country>test-replace-text-1</Country>"));
		assertTrue(result.contains("<Name>test-replace-text-2</Name>"));
	}
	
}

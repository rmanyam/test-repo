package com.newsuk.common.utilities;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.google.api.client.http.GenericUrl;
import com.jayway.jsonpath.JsonPath;


public class JsonParser {
     
	@SuppressWarnings("unchecked")
	@Test
     public void jsonParser() throws IOException, ParseException {
       

		GenericUrl  url = new GenericUrl("http://epl-dev3.uat-thetimes.co.uk/files/v1_fixtures_latest.json");
		HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
		requestBuilder.initializeRequestBuilder();
		requestBuilder.getHttpGetRequest(url);
	    String json = requestBuilder.getHttpResponse().parseAsString();
       
	    
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
                 
//      JSONObject j = (JSONObject)jsonObject.get("additions"); 
        JSONObject j = (JSONObject)jsonObject.get("items");         

        List<JSONObject> feedItemList = (List<JSONObject>)j.get("fixtures");
     
        processJson(feedItemList);
     

	}
	
	
	private void processJson(List<JSONObject> feedItemList) 
	{
		 for(int i=0;i<feedItemList.size();i++)
			 System.out.println(feedItemList.get(i));
 		
	}

}

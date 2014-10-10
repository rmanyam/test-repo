package com.newsuk.common.utilities;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.net.URL;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;


public class JsonValidator {

	public JsonValidator(){

	}

	public void assertJsonIsValid(String pathToValidator, String pathToJsonFile){
		
		String jsonText = null;
		URL url = Resources.getResource(pathToJsonFile);
		try {
			jsonText = Resources.toString(url, Charsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertThat(jsonText, matchesJsonSchemaInClasspath(pathToValidator));

	}




}

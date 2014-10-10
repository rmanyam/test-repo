package com.newsuk.common.utilities;

import org.junit.Test;

public class JsonValidatorTest {
	
	//@Test
	public void testJsonValidatorPass(){
		JsonValidator jv = new JsonValidator();
        jv.assertJsonIsValid("jsonValidatorTest/my-validator.json", "jsonValidatorTest/data.json");
	}
	
	//@Test(expected = AssertionError.class)  
	public void testJsonValidatorFail(){
		JsonValidator jv = new JsonValidator();
		jv.assertJsonIsValid("jsonValidatorTest/my-validator.json", "jsonValidatorTest/data-fail.json");
	}
	
	//@Test
	public void testJsonValidatorPassTest(){
		JsonValidator jv = new JsonValidator();
        jv.assertJsonIsValid("jsonValidatorTest/groups_json_schema.json", "jsonValidatorTest/v1_table_latest.json");
	}
	
}

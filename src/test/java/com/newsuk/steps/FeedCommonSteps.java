package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.joda.time.DateTime;
import com.newsuk.common.utilities.XmlReaderHelper;
import cucumber.api.java.en.Then;

public class FeedCommonSteps extends FeedBaseSteps {
	
	private static String FEED_UPDATED = "/feed/updated";
	
	@Then("I get a (\\d+) response")
	public void checkResponseHasCode(int responseCode){
		assertThat(statusCode, is(responseCode));
	}
	
	@Then("the \"([^\"]*)\" is not present")
	public void checkElementNotPresent(String xpathToElement){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
	
		String value = xmlReader.getValueAtPath(xpathToElement);	
		assertThat(value, isEmptyString());
	}
	
	@Then("the update field has been updated to the current time")
	public void checkUpdateFieldIsCurrentTime(){
		assertThat(isUpdateFieldCurrentTime(), is(true));
	}
	
	@Then("the update field has not been updated to the current time")
	public void checkUpdateFieldIsNotCurrentTime(){
		assertThat(isUpdateFieldCurrentTime(), is(false));
	}

	private boolean isUpdateFieldCurrentTime() {
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(FEED_UPDATED);
		
		DateTime actualUpdated = new DateTime(value);
		
		DateTime now = new DateTime();
		
		DateTime upperBound = now.plusSeconds(10);
		DateTime lowerBound = now.minusSeconds(10);
		System.out.println("upperbound date: " + upperBound);
		System.out.println("lowerBound date: " + lowerBound);
		System.out.println("actual updated: " + actualUpdated);

		
		if(upperBound.isAfter(actualUpdated.getMillis()) && lowerBound.isBefore(actualUpdated.getMillis())){
			return true;
		}
		else{
			return false;
		}
	}
	
}

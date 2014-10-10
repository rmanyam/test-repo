package com.newsuk.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeedManifestSteps extends FeedBaseSteps{
	
	@Given("Given Times Sports Navigation is unpublished")
	public void unpublishTimesSportsNav()
	{
		//TODO
	}
	
	@When("I make a request for the Sports Navigation Manifest feed")
	public void requestSportsNavigationManifestFeed(){
		feedRequester.makeSportsNavigationManifestFeedRequest();
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
		System.out.println(responseBody);
		System.out.println(statusCode);
	}
	
	@Then("I get the Sports Navigation Manifest response with the correct mandatory fields")
	public void checkSportsNavManifestResponseHasCorrectMandatoryFields(){
		checkMandatoryFields(baseFeedDomain,"Feed title : manifest", baseFeedDomain, "manifest", "The Times");
	}
	

}

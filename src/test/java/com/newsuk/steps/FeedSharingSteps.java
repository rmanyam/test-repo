package com.newsuk.steps;

import static org.junit.Assert.*;

import com.google.api.client.http.GenericUrl;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.model.feeds.ArticleModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
 

public class FeedSharingSteps extends FeedBaseSteps {
	
	@Given("^I create a new article for sharing$")
	public void i_create_a_new_article_for_sharing(){
		ArticleModel article = new ArticleModel();
		article.setDefaultValues(baseFeedDomain);
		article.setParentName("daily_informer");
		//article.setId("678987");
		FeedArticleSteps.testArticle = article;
		
		cmsHelper.createArticle(article);
		System.out.println("Article created. Sharing URL: " + article.getSharingUrl());
		
	    // Small pause needed for this article to load up
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("^I access the sharing url$")
	public void i_access_the_sharing_url(){
		//intentionally left blank
	}
	
	@Then("^accessing the sharing url returns successfully$")
	public void accessing_the_sharing_url_returns_successfully(){
		HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
	    com.newsuk.common.utilities.HttpResponse response;
		
	    GenericUrl url = new GenericUrl(FeedArticleSteps.testArticle.getSharingUrl());
	    System.out.println("URL to check: " + url);
	    
	    response = requestBuilder.doHttpGetRequest(url);
	    
	    assertTrue(requestBuilder.getHttpResponse().isSuccessStatusCode());
	}

}

package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class FeedWebViewSteps extends FeedBaseSteps{

	private static String FEED_ARTICLE_HTML = "/feed/articlehtml";
	private static String FEED_ARTICLE_LABEL = "/feed/articlelabel";
	
	@Given("a WebView Article exists under the Section")
	public void createWebVewArticle(){
		createDefaultWebVewArticle();
		testArticle.setId("3236625");
	}
	
	@Given("an unpublished WebView Article exists under the Section")
	public void createUnpublishedWebViewArticle(){
		createDefaultWebVewArticle();
		testArticle.setState("unpublished");
		testArticle.setId("3236626");
	}
	
	@Given("the WebView Article has a relatation to another Article")
	public void relateWebViewArticleToAnotherArticle(){
		relatedArticle = new ArticleModel();
	
		testArticle.addRelatedArticle(relatedArticle);
		testArticle.setId("3236625"); //TODO remove when we have ability to create test data dynamically
	}
	
	@Then("the WebView Article contains correct fields")
	public void checkWebViewHasCorrectFields(){
	XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(FEED_ARTICLE_HTML);
		assertThat(value, equalTo(testArticle.getArticleHtml()));
		
		value = xmlReader.getValueAtPath(FEED_ARTICLE_LABEL);
		assertThat(value, equalTo(testArticle.getArticleLabel()));
	}
	
	@Then("I get the WebView Article response with the correct mandatory fields")
	public void checkWebViewHasCorrectMandatoryFields(){
		checkMandatoryFields(testArticle, testArticle.getWebViewTitle());
	}
	
	private void createDefaultWebVewArticle(){
		testArticle = new ArticleModel();
		testArticle.setWebViewTitle("webview article title");
		testArticle.setCategory("webview");
		testArticle.setArticleTitle("webview title");
		testArticle.setTitle("webview title");
		testArticle.setHeadline("webview test");
		testArticle.setArticleHtml("http://www.webview-html-url.com");
		testArticle.setArticleLabel("webview article label");
		testArticle.setFeedLink(baseFeedDomain + "article/3236625");
		testArticle.setAuthorFirstName("tto");
		testArticle.setAuthorLastName("Administrator");
	}
}

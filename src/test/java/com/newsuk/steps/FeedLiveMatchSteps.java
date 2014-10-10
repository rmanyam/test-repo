package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;



public final class FeedLiveMatchSteps extends FeedBaseSteps {
	
	public static final String FEED_TITLE = "feed/title";
	public static final String FEED_TITLE_PREFIX = "feed/articletitleprefix";
	public static final String FEED_MATCH_DESCRIPTION = "feed/articlematchdescription";
	public static final String FEED_MATCH_ID = "feed/articlematchid";
	public static final String FEED_MATCH_COPY = "feed/articlematchcopy";
	public static final String FEED_ENTRY = "feed/entry/id";
	
	
	@Given("a Live Match Article exists under the Section")
	public void createLiveMatchArticle(){
		createDefaultLiveMatchArticle();
		testArticle.setId("3236925");
	}
	
	@Given("an unpublished Live Match Article exists under the Section")
	public void createUnpublishedLiveMatchArticle(){
		createLiveMatchArticle();
		testArticle.setState("unpublished");
		testArticle.setId("3236926");
		testArticle.setFeedLink(baseFeedDomain + "article/3236926");
	}
	
	@Then("the Live Match Article contains correct fields")
	public void checkLiveMatchHasCorrectFields(){
	
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(FEED_TITLE_PREFIX);
		assertThat(value, equalTo(testArticle.getTitleprefix()));
		
		value = xmlReader.getValueAtPath(FEED_TITLE);
		assertThat(value, equalTo(testArticle.getTitle()));
		
	    value = xmlReader.getValueAtPath(FEED_MATCH_DESCRIPTION);
		assertThat(value, equalTo(testArticle.getArticleMatchDescription()));
		
		value = xmlReader.getValueAtPath(FEED_MATCH_ID);
		assertThat(value, equalTo(testArticle.getArticleMatchId()));
		
		value = xmlReader.getValueAtPath(FEED_MATCH_COPY);
		assertThat(value, equalTo("<p>\n" + testArticle.getArticleMatchCopy() + "\n</p>\n"));
	}
	
	@Then("I get the Live Match Article response with the correct mandatory fields")
	public void checkLiveMatchHasCorrectMandatoryFields(){
		checkMandatoryFields(testArticle, testArticle.getTitle());
	}
	
	@Then("I get the Article response with Cover Image")
	public void checkCoverImageIsPresentInImageEntry() {
		
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(FEED_ENTRY);
				
		assertThat(value.contains(testArticle.getCoverImage().getId()), is(true));	
	}
	
	
	/**
	 * Configures a default live match article
	 */
	private void createDefaultLiveMatchArticle() {
		testArticle = new ArticleModel();
		testArticle.setTitle("LiveMatch Match Description");
		testArticle.setTitleprefix("LiveMatch Title Prefix");
		testArticle.setArticleMatchDescription("LiveMatch Match Description");
		testArticle.setArticleMatchId("LiveMatch Opta Match id");
		testArticle.setArticleMatchCopy("LiveMatch Match Copy");
		testArticle.setSectionId("LiveMatch Section Identifier Name");
		
		testArticle.setAuthorFirstName("tto");
		testArticle.setAuthorLastName("Administrator");
		testArticle.setAuthorId("2");
		testArticle.setAuthorUri(baseFeedDomain + "author/" + testArticle.getAuthorId());
		
		testArticle.setCategory("livematch");
		testArticle.setFeedLink(baseFeedDomain + "article/3236925");
	}
	
}

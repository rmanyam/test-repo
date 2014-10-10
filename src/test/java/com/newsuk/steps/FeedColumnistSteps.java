package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeedColumnistSteps extends FeedBaseSteps{
	
	private static String FEED_HEADLINE = "/feed/articleheadline ";
	private static String FEED_STANDFIRST = "/feed/articlestandfirst ";
	private static String FEED_ARTICLE_BODY = "/feed/articlebody";
	private static String FEED_ARTICLE_HEADLINE_OVERRIDE="/feed/articleheadlineoverride ";
	private static String FEED_ARTICLE_STANDFIRST_OVERRIDE="/feed/articlestandfirstoverride ";
	
	@Given("a Columnist Article exists under the Section")
	public void createColumnistArticle(){
		testArticle = new ArticleModel();
		
		testArticle.setId("3236550");
		testArticle.setHeadline("columnist headline");
		testArticle.setStandFirst("columnist standfirst");
		testArticle.setBody("columnist body");
		testArticle.setAuthorFirstName("tto");
		testArticle.setAuthorLastName("Administrator");
		testArticle.setFeedLink(baseFeedDomain + "article/3236550");
		testArticle.setAuthorUri(baseFeedDomain + "author/2");
		testArticle.setCategory("columnist");
		testArticle.setArticleHeadlineOverride("columnist headline - overridden");
		testArticle.setArticleStandFirstOverride("columnist standfirst - overridden");
		testArticle.setState("published");
	}
	
	@Given("an unpublished Columnist Article exists under the Section")
	public void createUnpublishedColumnistArticle(){
		createColumnistArticle();
		testArticle.setState("unpublished");
	
		testArticle.setId("3236622"); //TODO 
	}
	
	@Given("the Columnist Article has a relatation to another Article")
	public void relateColumnistArticleToAnotherArticle(){
		relatedArticle = new ArticleModel();
	
		testArticle.addRelatedArticle(relatedArticle);
		testArticle.setId("3236550"); //TODO remove when we have ability to create test data dynamically
	}
		
	@Then("the Columnist Article contains correct fields")
	public void checkColumnistArticleHasCorrectFields(){
	XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(FEED_HEADLINE);
		assertThat(value, equalTo(testArticle.getHeadline()));
		
		value = xmlReader.getValueAtPath(FEED_STANDFIRST);
		assertThat(value, equalTo(testArticle.getStandFirst()));
		
		value = xmlReader.getValueAtPath(FEED_ARTICLE_BODY);
		assertThat(value, equalTo("<p>\n" + testArticle.getBody() + "\n</p>\n"));

		value = xmlReader.getValueAtPath(FEED_ARTICLE_HEADLINE_OVERRIDE);
		assertThat(value, equalTo(testArticle.getArticleHeadlineOverride()));
		
		value = xmlReader.getValueAtPath(FEED_ARTICLE_STANDFIRST_OVERRIDE);
		assertThat(value, equalTo(testArticle.getArticleStandFirstOverride()));
	}
	
	@Then("the Section does not have an entry for the Columnist Article")
	public void checkNoEntryForColumnistArticle(){
		checkEntryNotPresent(testArticle);
	}
	
}

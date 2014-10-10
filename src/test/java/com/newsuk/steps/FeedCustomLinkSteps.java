package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeedCustomLinkSteps extends FeedBaseSteps{
	
	private static final String FEED_ARTICLE_ID = "/feed/articleid";
	private static final String FEED_ARTICLE_SECTION_ID = "/feed/articlesectionid";
	private static final String FEED_HEADLINE = "/feed/articleheadline ";
	private static final String FEED_TEASER_TEXT = "/feed/articleteasertext";

	@Given("a CustomLink Article exists under the Section")
	public void createCustomLinkArticle(){
		testArticle = new ArticleModel();
		testArticle.setId("3237177");
		testArticle.setDefaultValues(baseFeedDomain);
		testArticle.setName("custom link - name");
		testArticle.setURL("http://www.a-url.com");
		testArticle.setTeaserText("teaser text");
		testArticle.setSectionIdentifierName("section identifier name");
		testArticle.setHeadline("headline");
		testArticle.setCategory("customLink");
	}
	
	@Given("an unpublished CustomLink Article exists under the Section")
	public void createUnpublishedCustomLinkArticle(){
		testArticle = new ArticleModel();
		testArticle.setId("3238301");
	}
	
	@Given("the CustomLink Article has a relatation to another Article")
	public void createArticleWithArticleRelation(){
		testArticle = new ArticleModel();
		testArticle.setId("3237177");
	}
	
	@Then("I get the CustomLink Article response with the correct mandatory fields")
	public void checkMandatoryFields(){
		checkMandatoryFields(testArticle, testArticle.getName());
	}
	
	@Then("the CustomLink Article contains correct fields")
	public void checkArticleHasCorrectFields(){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(FEED_ARTICLE_ID);
		assertThat(value, equalTo(testArticle.getId()));
		
		value = xmlReader.getValueAtPath(FEED_ARTICLE_SECTION_ID);
		assertThat(value, equalTo(testArticle.getSectionIdentifierName()));
		
	    value = xmlReader.getValueAtPath(FEED_HEADLINE);
		assertThat(value, equalTo(testArticle.getHeadline()));
		
		value = xmlReader.getValueAtPath(FEED_TEASER_TEXT);
		assertThat(value, equalTo(testArticle.getTeaserText()));
	}
}

package com.newsuk.worldcuphub.feeds.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.containsString;

import com.google.api.client.http.GenericUrl;
import com.jayway.jsonpath.JsonPath;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.JsonReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.ImageModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeedWorldCupArticleSteps extends FeedWorldCupBaseSteps {
	
	private static String HEADLINE = "headline.value";
	private static String BODY = "body.value";
	private static String AUTHOR_TITLE = "authorTitle.value";
	private static String CLASSIFICATION = "classification.value";
	
	@Given("an Article exists under the WCH Section")
	public void createWorldCupFeedArticle(){
		testArticle = new ArticleModel();
		testArticle.setDefaultValues(baseFeedUrl);
		testArticle.setId("3218842");
		testArticle.setHeadline("Headline");
		testArticle.setFeedLink(baseFeedUrl + "article/" + testArticle.getId());
		testArticle.setBody("test body");
		testArticle.setTitle("title");
		testArticle.setSectionPageHeadline("Headline section page");
		testArticle.setByline("byline override");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("an unpublished WCH Article exists under the Section")
	public void createUnpublishedArticle(){
		testArticle = new ArticleModel();
		testArticle.setId("3238085");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("2 WCH Articles exist under the Section")
	public void createTwoArticles(){
		testArticle = new ArticleModel();
		testArticle.setDefaultValues(baseFeedUrl);
		testArticle.setId("3218842");
		testArticle.setHeadline("Headline");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);

		testArticle2 = new ArticleModel();
		testArticle2.setDefaultValues(baseFeedUrl);
		testArticle2.setId("3238165");
		testArticle2.setHeadline("headline 2");
		cmsHelper.moveArticleToBrick(testArticle2.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", false);
	}
	
	@Given("the first WCH Article is related to a second WCH Article")
	public void relateSecondArticleToFirst(){
		testArticle = new ArticleModel();
		testArticle.setId("3238168");
		
		testArticle2 = new ArticleModel();
		testArticle2.setId("3238169");
		
	}
	
	@Given("an unpublished WCH Article with multiple images exists under the Section")
	public void createUnpublishedSlideshowArticle(){
		testArticle = new ArticleModel();
		testArticle.setId("3239167");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("Podcast exists under the WCH Article")
	public void createArticleWithPodcast(){
		testArticle = new ArticleModel();
		testArticle.setId("3239246");
		testArticle.setDefaultValues(baseWorldCupFeedDomain);
		testArticle.setClassification("Podcast");
		testArticle.setHeadline("article with podcast");
		testArticle.setByline("byline override");
		testArticle.setBylineOverride("byline override");
		testArticle.setInfographic("<iframe>test iframe</iframe>");
		testArticle.setBody("body");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("Interactive exists under the WCH Article")
	public void createArticleWithInteractive(){
		testArticle = new ArticleModel();
		testArticle.setId("3239250");
		testArticle.setDefaultValues(baseWorldCupFeedDomain);
		testArticle.setClassification("Interactive");
		testArticle.setHeadline("article with interactive");
		testArticle.setByline("byline override");
		testArticle.setInfographic("interactive information");
		testArticle.setBody("body");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("an unpublished WCH Article with Podcast exists under the Section")
	public void createUnpublishedArticleWithPodcast(){
		testArticle = new ArticleModel();
		testArticle.setId("3239257");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("an unpublished WCH Article with Interactive exists under the Section")
	public void createUnpublishedArticleWithInteractive(){
		testArticle = new ArticleModel();
		testArticle.setId("3239335");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("many different asset types exist under the WCH Article, including \"([^\"]*)\"")
	public void createArticleWithManyAssetsThatLeadsToType(String targetArticleType){
		testArticle = new ArticleModel();
		if(targetArticleType.equals("Video")){
			testArticle.setId("3239410");
		}
		else if (targetArticleType.equals("Slideshow")){
			testArticle.setId("3239411");
		}
		else if (targetArticleType.equals("Interactive")){
			testArticle.setId("3239412");
		}
		else if (targetArticleType.equals("Podcast")){
			testArticle.setId("3239413");
		}
		else{ //Image
			testArticle.setId("3239414");
		}
	}
	
	@Given("the Article has Display Slideshow In New Tab set")
	public void createArticleWithSlideshowInNewTab(){
		testArticle = new ArticleModel();
		testArticle.setId("3240364");
		testArticle.setDefaultValues(baseWorldCupFeedDomain);
		testArticle.setClassification("Slideshow");
		testArticle.setHeadline("article with sldieshow in separate tab");
		testArticle.setBody("body");
		testArticle.setStandFirst("standfirst");
		testArticle.setByline("byline override");
		testArticle.setBylineOverride("byline override");
		
		testArticle.addImage(new ImageModel("261320"));
		testArticle.addImage(new ImageModel("261345"));
		testArticle.addImage(new ImageModel("262363"));
		testArticle.addImage(new ImageModel("261493"));
		testArticle.addImage(new ImageModel("261496"));
		testArticle.addImage(new ImageModel("261504"));
		testArticle.addImage(new ImageModel("261519"));
		testArticle.addImage(new ImageModel("261957"));
		testArticle.addImage(new ImageModel("261521"));
		testArticle.addImage(new ImageModel("262004"));
		testArticle.addImage(new ImageModel("261527"));
		
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("the unpublished Article has Display Slideshow In New Tab set")
	public void createUnpublishedSlideshowArticleInSeparateTab(){
		testArticle = new ArticleModel();
		testArticle.setId("3240389");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("I update the WCH article headline to \"([^\"]*)\"")
	public void updateWCHArticleHeadline(String updatedHeadline){
		testArticle.setId("3240390");
		updateArticleHeadline(updatedHeadline);
	}
	
	@When("I make a request for the WCH Article")
	public void requestArticle(){
		feedRequester.makeWorldCupHubFeedArticleRequest(testArticle.getId());
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
	}
	
	@When("I follow the article url link")
	public void followArticleUrlLink(){
		String urlString =  JsonPath.read(responseBody, "articleUrl.value" );
		GenericUrl url = new GenericUrl(urlString + "/readPane=3");
		HttpRequestBuilder httpRequester = new HttpRequestBuilder();
		
		responseBody = httpRequester.doHttpGetRequest(url).getBody();
	}
	
	@Then("I get the WCH Article response with the correct mandatory fields")
	public void checkArticleHasCorrectMandatoryFields(){
		checkMandatoryFields(testArticle, testArticle.getHeadline());
	}
	
	@Then("the WCH Article contains correct fields")
	public void checkArticleHasCorrectFields(){
		String value = null;
		
		//body
		value = JsonPath.read(responseBody, BODY);
		assertThat(value, equalTo("<p>\n" + testArticle.getBody() + "\n</p>\n"));
		
		//author title
		value = JsonPath.read(responseBody, AUTHOR_TITLE);
		assertThat(value, equalTo(testArticle.getTitle()));
		
		//headline
		value = JsonPath.read(responseBody, HEADLINE);
		assertThat(value, equalTo(testArticle.getSectionPageHeadline()));
		
		//classification - another set of specific tests will cover classification and precedence logic
		value = JsonPath.read(responseBody, CLASSIFICATION);
		assertThat(value, not(isEmptyOrNullString()));
	}
	
	@Then("there is no Article entry for the second WCH Article")
	public void checkEntriesDoesNotHaveSecondArticle(){
		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody);
		int entryIndex = jsonReader.getIndexOfNodeWithValue("entries[*].id.value", baseFeedUrl + "article/" + testArticle2.getId());
		assertThat(entryIndex, is(-1));
	}

	@Then("the WCH article contains the correct article url field")
	public void checkArticleUrlFieldIsCorrect(){
		String value = JsonPath.read(responseBody, "articleUrl.value");
		assertThat(value, equalTo("http://ttosi.se.newsint.co.uk/tto/feeds/worldcuphub/worldcuphubhome/article" + testArticle.getId() + ".ece"));
	}
	
	@Then("I get a WCH readingPane response with the correct fields")
	public void checkReadPaneResponseIsCorrect(){
		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody);
		responseBody = jsonReader.stripInvalidJson();
		
		String value = JsonPath.read(responseBody, "articleType");
		assertThat(value, equalTo("standardArticle"));
		
		value = JsonPath.read(responseBody, "contentType");
		assertThat(value, equalTo("slideshow"));
		
		value = JsonPath.read(responseBody, "headline");
		assertThat(value, equalTo(testArticle.getHeadline()));
		
		value = JsonPath.read(responseBody, "standfirst");
		assertThat(value, equalTo(testArticle.getStandFirst()));
		
		value = JsonPath.read(responseBody, "byline");
		assertThat(value, equalTo(testArticle.getBylineOverride()));
		
		value = JsonPath.read(responseBody, "body");
		assertThat(value, equalTo("<p>\n" + testArticle.getBody() + "\n</p>\n"));
	}
	
	@Then("the WCH article with Podcast contains the correct fields")
	public void checkArticleWithPodFields(){
		String value = JsonPath.read(responseBody, "classification.value");
		assertThat(value, equalTo(testArticle.getClassification()));
		
		value = JsonPath.read(responseBody, "infographic.value");
		assertThat(value, equalTo(testArticle.getInfographic()));
		
		value = JsonPath.read(responseBody, "headline.value");
		assertThat(value, equalTo(testArticle.getHeadline()));
		
		value = JsonPath.read(responseBody, "articleUrl.value");
		assertThat(value, containsString(testArticle.getId()));
		
		value = JsonPath.read(responseBody, "body.value");
		assertThat(value, containsString(testArticle.getBody()));
	}
	
	@Then("the classification value is \"([^\"]*)\"")
	public void checkClassificationValue(String classificationType){
		String value = JsonPath.read(responseBody, "classification.value");
		assertThat(value, equalTo(classificationType));
	}
}

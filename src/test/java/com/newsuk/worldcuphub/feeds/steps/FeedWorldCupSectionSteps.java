package com.newsuk.worldcuphub.feeds.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

import java.util.Date;

import com.google.api.client.http.GenericUrl;
import com.google.common.base.CharMatcher;
import com.jayway.jsonpath.JsonPath;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.JsonReaderHelper;
import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.SectionModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public final class FeedWorldCupSectionSteps extends FeedWorldCupBaseSteps {


	@Given("World Cup Hub Section exists")
	public void createSportsNavSection(){
		//TODO
	}
	
	@Given("a Section named \"([^\"]*)\" exists underneath the WCH Section")
	public void createSection(String sectionName){
		testSection = new SectionModel();
		testSection.setSectionName(sectionName);
		testSection.setUniqueName(CharMatcher.WHITESPACE.removeFrom(sectionName.toLowerCase()));
		testSection.setSectionPiority("1");
		testSection.setSectionTemplate("autoSub01");
		testSection.setSectionParentUiqueName("timessportnavigation");
		testSection.setSectionColor("#1A0000");
		testSection.setSectionHighlightColor("#E60000");
		
		String uniqueName = CharMatcher.WHITESPACE.removeFrom(sectionName.toLowerCase());
		
		testSection.setId("sectionName");
		testSection.setCategory("section");
		testSection.setAuthorFirstName("The");
		testSection.setAuthorLastName("Times");
		testSection.setAuthorId("2");
		testSection.setHeadline("Feed title : section");
		testSection.setFeedLink(baseFeedUrl + "section/" + uniqueName);
		testSection.setAuthorUri("http://www.thetimes.co.uk/tto/news/");
		testSection.setId("13850");
		//testSection.setId("13853");

		//TODO pass to utility that takes Section bean and creates Section in CMS
	}
	
	@When("I make a request for the World Cup Hub feed")
	public void requestWorldCupHubHome(){
		feedRequester.makeWorldCupHubFeedRequest();
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
	}
	
	@When("I use the Article URL returned in the WCH to make a request for the Article")
	public void followUrlToArticle(){
		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody);
		int entryIndex = jsonReader.getIndexOfNodeWithValue("entries[*].id.value", baseWorldCupFeedDomain + "worldcuphub/article/" + testArticle.getId());
		System.out.println("entryIndex is: " + entryIndex);
		assertThat(entryIndex, not(-1));
		
		String urlString =  JsonPath.read(responseBody, "entries[" + entryIndex + "].link.href" );
		GenericUrl url = new GenericUrl(urlString);
		HttpRequestBuilder httpRequester = new HttpRequestBuilder();
		
		responseBody = httpRequester.doHttpGetRequest(url).getBody();
	}
	
	@Then("I get the main WCH response with the correct mandatory fields")
	public void checkMainMandatoryFields(){
		checkMandatoryFields(testSection, "Feed title : section");
	}

	@Then("the main WCH response contains the Article entry with the correct fields")
	public void checkSectionContainsArticle(){
		checkSectionContainsEntry(testArticle);
	}
	
	@Then("there are 2 WCH Article entries in the Section response")
	public void checkTwoEntriesAppearInSection(){
		checkSectionContainsEntry(testArticle);
		checkSectionContainsEntry(testArticle2);
	}
	
	@Then("the main WCH response does not have an entry for the Article")
	public void checkSectionDoesNotContainEntry(){
		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody);
		int entryIndex = jsonReader.getIndexOfNodeWithValue("entries[*].id.value", baseFeedUrl + "article/" + testArticle.getId());
		assertThat(entryIndex, is(-1));
	}
	
	@Then("the layout value is valid")
	public void checkLayoutValueIsValid(){
		String value =  JsonPath.read(responseBody, "layout.value" );
		assertThat(value, startsWith("worldcupHub"));
	}
	
	private void checkSectionContainsEntry(ArticleModel article) {
		
		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody);
		int entryIndex = jsonReader.getIndexOfNodeWithValue("entries[*].id.value", baseWorldCupFeedDomain + "worldcuphub/article/" + article.getId());
		System.out.println("entryIndex is: " + entryIndex);
		assertThat(entryIndex, not(-1));
		
		// assert the category term
		String value =  JsonPath.read(responseBody, "entries[" + entryIndex + "].category.term" );
		assertThat(value, equalTo(article.getCategory()));
		
		// assert the title
		value =  JsonPath.read(responseBody, "entries[" + entryIndex + "].title.value" );
		assertThat(value, equalTo(article.getHeadline()));
		
		// assert the content
		value =  JsonPath.read(responseBody, "entries[" + entryIndex + "].content.value" );
		assertThat(value, equalTo(article.getId()));
		
		// assert the link
		value =  JsonPath.read(responseBody, "entries[" + entryIndex + "].link.href" );
		assertThat(value, equalTo(baseWorldCupFeedDomain + "worldcuphub/article/" + article.getId()));
		
		// assert the author
		value =  JsonPath.read(responseBody, "entries[" + entryIndex + "].author.name" );
		assertThat(value, equalTo(article.getAuthorFirstName() + " " + article.getAuthorLastName()));	
		value =  JsonPath.read(responseBody, "entries[" + entryIndex + "].author.uri" );
		assertThat(value, equalTo(baseWorldCupFeedDomain + "worldcuphub/author/2"));
	}
}

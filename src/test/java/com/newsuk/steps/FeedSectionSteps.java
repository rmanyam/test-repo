package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

import java.util.Date;

import com.google.api.client.http.GenericUrl;
import com.google.common.base.CharMatcher;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.SectionModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public final class FeedSectionSteps extends FeedBaseSteps {

	//Section fields
	private static final String XPATH_TO_FEED_SECTION_NAME = "/feed/sectionname";
	private static final String XPATH_TO_FEED_SECTION_ID = "/feed/sectionid";
	private static final String XPATH_TO_FEED_SECTION_PRIORITY = "/feed/sectionpriority";
	private static final String XPATH_TO_FEED_SECTION_TEMPLATE = "/feed/sectiontemplate";
	private static final String XPATH_TO_FEED_SECTION_PARENTID = "/feed/sectionparentid";
	private static final String XPATH_TO_FEED_SECTION_COLOUR = "/feed/sectioncolor";
	private static final String XPATH_TO_FEED_SECTION_HIGHLIGHT_COLOUR = "/feed/sectionhighlightcolor";
	
	public FeedSectionSteps(){
		testSection = new SectionModel();
	}
	
	@Given("Times Sports Navigation Section exists")
	public void createSportsNavSection(){
		//TODO
	}
	
	@Given("a Section named \"([^\"]*)\" exists underneath it")
	public void createSection(String sectionName){
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
		testSection.setFeedLink(baseFeedDomain + "section/" + uniqueName);
		testSection.setAuthorUri("http://www.thetimes.co.uk/tto/news/");
		
		//TODO pass to utility that takes Section bean and creates Section in CMS
	}
	
	@Given("a Sub-Section named \"([^\"]*)\" exists underneath it")
	public void createSubSection(String subSectionName){
		testSubSection.setSectionName(subSectionName);
		testSubSection.setUniqueName(CharMatcher.WHITESPACE.removeFrom(subSectionName.toLowerCase()));
		testSubSection.setSectionPiority("11");
		testSubSection.setSectionTemplate("navigationSectionFeedHero");
		testSubSection.setSectionParentUiqueName(testSection.getUniqueName());
		testSubSection.setSectionColor("#1A0000");
		testSubSection.setSectionHighlightColor("#E60000");
		//TODO pass to utility that takes Section bean and creates Section in CMS
	}
	
	@Given("a Section named \"([^\"]*)\" with Priority of (\\d+) exists underneath it")
	public void createSectionWithPriority(String sectionName, int priority){
		testSection.setSectionName(sectionName);
		testSection.setUniqueName(CharMatcher.WHITESPACE.removeFrom(sectionName.toLowerCase()));
		testSection.setSectionPiority(String.valueOf(priority));
		testSection.setSectionTemplate("navigationSectionFeedHero");
		testSection.setSectionParentUiqueName("timessportnavigation");
		testSection.setSectionColor("#1A0000");
		testSection.setSectionHighlightColor("#E60000");
		//TODO pass to utility that takes Section bean and creates Section in CMS
	}
	
	@Given("a Sub-Section named \"([^\"]*)\" with Priority (\\d+) exists underneath the Section")
	public void createSubSectionWithPriority(String subSectionName, int priority){
		testSubSection.setSectionName(subSectionName);
		testSubSection.setUniqueName(CharMatcher.WHITESPACE.removeFrom(subSectionName.toLowerCase()));
		testSubSection.setSectionPiority(String.valueOf(priority));
		testSubSection.setSectionTemplate("navigationSectionFeedHero");
		testSubSection.setSectionParentUiqueName(testSection.getUniqueName());
		testSubSection.setSectionColor("#1A0000");
		testSubSection.setSectionHighlightColor("#E60000");
		//TODO pass to utility that takes Section bean and creates Section in CMS
	}
	
	@Given("a Sub-Section named \"([^\"]*)\" exists underneath which is unpublished")
	public void createUnpublishedSection(String subSectionName)
	{
		testSubSection.setSectionName(subSectionName);
		testSubSection.setUniqueName(CharMatcher.WHITESPACE.removeFrom(subSectionName.toLowerCase()));
		testSubSection.setSectionPiority("40");
		testSubSection.setSectionTemplate("navigationSectionFeedHero");
		testSubSection.setSectionParentUiqueName(testSection.getUniqueName());
		testSubSection.setSectionColor("#1A0000");
		testSubSection.setSectionHighlightColor("#E60000");
		testSubSection.setPublished(false);
		//TODO pass to utility that takes Section bean and creates Section in CMS
	}
	
	@When("I use the Section URL returned to make a request for the Section")
	public void followSectionUrlFromManifestResponse(){
		followLinkInSection(testSection);
	}
	
	@When("I use the Sub-Section URL returned to make a request for the Sub-Section")
	public void followSubSectionUrlFromManifestResponse(){
		followLinkInSection(testSubSection);
	}
	
	@When("I request for a Section that does not exist")
	public void requestSectionThatDoesNotExist(){
		String randomId = String.valueOf(new Date().getTime());
		System.out.println("random id is: " + randomId);
		feedRequester.makeSportsNavFeedSectionRequest(randomId);
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
		System.out.println(responseBody);
		System.out.println(statusCode);
	}
	
	@When("I make a request for the Section")
	public void requestSection(){
		feedRequester.makeSportsNavFeedSectionRequest(testSection.getUniqueName());
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
		System.out.println(responseBody);
		System.out.println(statusCode);
	}
	
	@When("I make a request for the Sub-Section")
	public void requestSubSection(){
		feedRequester.makeSportsNavFeedSectionRequest(testSubSection.getUniqueName());
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
		System.out.println(responseBody);
		System.out.println(statusCode);
	}
	
	@Then("the Section contains the Article entry with the correct fields")
	public void checkSectionContainsArticle(){
		checkSectionContainsEntry(testArticle);
	}
	
	@Then("the Section list contains the Section with the correct values")
	public void checkSectionListContainsSection(){
		checkManifestContainsSection(testSection);
	}
	
	@Then("the Section list contains the Sub-Section with the correct values")
	public void checkSectionListContainsSubSection(){
		checkManifestContainsSection(testSubSection);
	}
	
	@Then("I get a Section response with the correct mandatory fields")
	public void checkSectionResponseHasCorrectMandatoryFields(){
		checkMandatoryFields(testSection, testSection.getHeadline());
	}
	
	@Then("I get a Section response for the Sub-Section with the correct mandatory fields")
	public void checkSubSectionResponseHasCorrectMandatoryFields(){
		checkMandatoryFields(baseFeedDomain + "section/"+testSubSection.getUniqueName(), "Feed title : section",
				baseFeedDomain + "section/"+testSubSection.getUniqueName(), "section", "The Times");
	}
	
	@Then("the Section has the correct values")
	public void checkSectionHasCorrectValues(){
		checkSectionDetails(testSection);
	}
	
	@Then("the Sub-Section has the correct values")
	public void checkSubSectionHasCorrectValues(){
		checkSectionDetails(testSubSection);
	}
	
	@Then("the Section \"([^\"]*)\" is above the Section \"([^\"]*)\" in the response")
	public void checkSectionIsAboveAnother(String sectionName1, String sectionName2){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String section1 = CharMatcher.WHITESPACE.removeFrom(sectionName1.toLowerCase());
		String section2 = CharMatcher.WHITESPACE.removeFrom(sectionName2.toLowerCase());
		
		int section1Index = xmlReader.getIndexOfNodeWithValue("//entry/id", baseFeedDomain + "section/" + section1);
		int section2Index = xmlReader.getIndexOfNodeWithValue("//entry/id", baseFeedDomain + "section/" + section2);
		
		assertThat(section1Index, lessThan(section2Index));
	}
	
	@Then("there are (\\d+) Article entries in the Section response")
	public void checkNumberArticlesInSection(int numArticles){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int actualArticleNum = xmlReader.getNumberOfElements("/feed/entry/category[@term!='image']");
		assertThat(actualArticleNum, is(numArticles));
	}
	
	@Then("the Section does not have an entry for the Article")
	public void checkArticleIsNotInSection(){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("//entry/id", baseFeedDomain + "article/" + testArticle.getId());
	
		assertThat(-1, is(sectionIndex));
	}
	
	@Then("the Section contains the \"([^\"]*)\" Article entry with the correct fields")
	public void checkSectionForColumnistArticleEntry(String articleType){
		checkSectionContainsEntry(testArticle);
	}
	
	private void checkManifestContainsSection(SectionModel section){
		
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("//entry/id", baseFeedDomain + "section/" + section.getUniqueName());
		sectionIndex++;
		
		System.out.println("Section index is: " + sectionIndex);
		
		String value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/title");
		//*[node()='title']
		assertThat(value, equalTo(section.getSectionName()));
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/updated");
		//assertThat(value, isDate())); TODO - implement when we can control section creation
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/author/name");
		assertThat(value, equalTo("The Times"));
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/author/uri");
		assertThat(value, equalTo("http://www.thetimes.co.uk/tto/news/"));
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/content");
		assertThat(value, equalTo(section.getSectionName()));
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/link/@href");
		assertThat(value, equalTo(baseFeedDomain + "section/" + section.getUniqueName()));
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/category/@term");
		assertThat(value, equalTo("section"));	
	}
	
	private void checkSectionDetails(SectionModel expectedSection){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(XPATH_TO_FEED_SECTION_NAME);
		assertThat(value, equalTo(expectedSection.getSectionName()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_SECTION_ID);
		assertThat(value, equalTo(expectedSection.getUniqueName()));

		value = xmlReader.getValueAtPath(XPATH_TO_FEED_SECTION_PRIORITY);
		assertThat(value, equalTo(expectedSection.getSectionPiority()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_SECTION_TEMPLATE);
		assertThat(value, equalTo(expectedSection.getSectionTemplate()));
			
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_SECTION_PARENTID);
		assertThat(value, equalTo(expectedSection.getSectionParentUiqueName()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_SECTION_COLOUR);
		assertThat(value, equalTo(expectedSection.getSectionColor()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_SECTION_HIGHLIGHT_COLOUR);
		assertThat(value, equalTo(expectedSection.getSectionHighlightColor()));
	}
	
	private void followLinkInSection(SectionModel section) {
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("//entry/id", baseFeedDomain + "section/" + section.getUniqueName());
		sectionIndex++;
		String urlString = xmlReader.getValueAtPath("/feed/entry["+sectionIndex+"]/link/@href");

		GenericUrl url = new GenericUrl(urlString);
		HttpRequestBuilder httpRequester = new HttpRequestBuilder();

		responseBody = httpRequester.doHttpGetRequest(url).getBody();
		System.out.println(responseBody);

	}
	
	private void checkSectionContainsEntry(ArticleModel article) {
		
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("//entry/id", baseFeedDomain + "article/" + article.getId()) +1;
		
		System.out.println("sectionIndex is: " + sectionIndex);
		assertThat(sectionIndex, not(0));
		
		// assert the category term
		String categoryTerm = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/category/@term");
		assertThat(categoryTerm, equalTo(article.getCategory()));
		
		// assert the title
		String value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/title");
		if (categoryTerm.equals("livematch")) {
			assertThat(value, equalTo(article.getTitle()));
		}
		else if (categoryTerm.equals("customLink")){
			assertThat(value, equalTo(article.getName()));
		}
		else {
			assertThat(value, equalTo(article.getHeadline()));
		}

		// assert the author
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/author/name");
		assertThat(value, equalTo(article.getAuthorFirstName() + " " + article.getAuthorLastName()));	
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/author/uri");
		assertThat(value, equalTo(baseFeedDomain + "author/2"));
		
		// assert the content
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/content");
		assertThat(value, equalTo(article.getId()));
		
		// assert the link
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/link/@href");
		assertThat(value, equalTo(baseFeedDomain + "article/" + article.getId()));
	}
}

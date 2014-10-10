package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.joda.time.DateTime;

import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.ImageModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeedArticleSteps extends FeedBaseSteps {

	//Article entry fields
	private static final String XPATH_TO_FEED_ARTICLE_ID = "/feed/articleid";
	private static final String XPATH_TO_FEED_ARTICLE_TITLE = "/feed/articletitle";
	private static final String XPATH_TO_FEED_ARTICLE_HEADLINE = "/feed/articleheadline";
	private static final String XPATH_TO_FEED_ARTICLE_STANDFIRST = "/feed/articlestandfirst ";
	private static final String XPATH_TO_FEED_ARTICLE_BODY = "/feed/articlebody";
	private static final String XPATH_TO_FEED_ARTICLE_SECTION_NAME_OVR = "/feed/sectionnameoverride";
	private static final String XPATH_TO_FEED_ARTICLE_TITLE_PREFIX = "/feed/articletitleprefix";
	private static final String XPATH_TO_FEED_ARTICLE_HEADLINE_OVR = "/feed/articleheadlineoverride";
	private static final String XPATH_TO_FEED_ARTICLE_STANDFIRST_OVR = "/feed/articlestandfirstoverride";
	private static final String XPATH_TO_FEED_ARTICLE_BODY_OVR = "/feed/articlebodyoverride";
	
	public FeedArticleSteps(){
		super();
	}
	
	@Given("an Article exists under the Section")
	public void createArticle(){
		//set all article attributes
		testArticle = new ArticleModel();
		testArticle.setId("3236549");
		testArticle.setDefaultValues(baseFeedDomain);
		testArticle.setTitle("automation 3 article title");
		testArticle.setState("published");
		testArticle.setParentName("automation3published");
		testArticle.setAuthorFirstName("tto");
		testArticle.setAuthorLastName("Administrator");
			
		testArticle.setStandFirst("automation 3 article standfirst");
		testArticle.setSectionNameOverride("automation 3 article section name override");
		testArticle.setTitleprefix("automation 3 article times sport title prefix");
		testArticle.setHeadlineTitleOverride("automation 3 article headline changed");
		testArticle.setArticleStandFirstOverride("automation 3 article  standfirst changed");
		testArticle.setArticleBodyOverride("automation 3 article body - changed ");
		
		testArticle.setFeedLink(baseFeedDomain + "article/" + 3236549);
		testArticle.setArticleBodyOverride("<p>\nautomation 3 article body - changed \n</p>\n");
		testArticle.setBody("<p>\nautomation 3 article body\n</p>\n");
		testArticle.setCategory("article");
		testArticle.setAuthorFirstName("tto");
		testArticle.setAuthorLastName("Administrator");
		testArticle.setAuthorId("2");
		testArticle.setFeedLink(baseFeedDomain + "article/3236549");
		testArticle.setHeadline("automation 3 article headline");
		testArticle.setAuthorUri(baseFeedDomain + "author/" + testArticle.getAuthorId());
		
		//TODO re-implement this once cms asset creation/teardown is available
//		String articleId = cmsHelper.createArticle(testArticle);
//		testArticle.setArticleId(articleId);
				
	}
	
	@Given("(\\d+) Articles exist under the Section")
	public void createSectionWithMultipleArticles(int numArticles){
		//TODO - when we dynamically create test data - loop and create multiple articles under the section
//		for(int i=0; i<numArticles; i++){
			createArticle();	
//		}
		
		
	}
	
	@Given("an unpublished Article exists under the Section")
	public void createUnpublishedArticle(){
		createArticle();
		testArticle.setState("unpublished");
		testArticle.setId("3235670"); //TODO hardcode article id of an unpublished article for now
	}
	
	@Given("a Cover Image exists under the Article")
	public void createImage(){
		coverImage = new ImageModel();
		coverImage.setTitle("Wayne Rooney bicycle kick. Best quality.mp4");
		coverImage.setAuthorFirstName("The");
		coverImage.setAuthorLastName("Times");
		coverImage.setContentType("257719");
		coverImage.setFeedLink(baseFeedDomain + "image/257719");
		coverImage.setCategory("image");
		
		testArticle.setCoverImage(coverImage);
		
		testArticle.setId("3236044");//TODO once asset creation is dynamic, do not set article id here
		testArticle.setByline("byline");
		testArticle.setFeedLink(baseFeedDomain + "article/3236044");
		testArticle.setHeadline("automation article with picture");
		coverImage.setId("257719");  //TODO once asset creation is dynamic, need to set image ids after creation
	}
	
	@Given("the first Article is related to a second Article")
	public void  createRealtedArticle(){
		relatedArticle = new ArticleModel();
		
		testSection.setId("13836"); //TODO once asset creation is dynamic, do not set section id here
		testSection.setUniqueName("automation1subsection1");
		testArticle.setId("3236045"); //TODO once asset creation is dynamic, do not set article id here
		relatedArticle.setId("3236046");
	}
	
	@Given("a second Article exists without a Section")
	public void createRelatedArticleWithoutSection(){
		createRealtedArticle();
		relatedArticle.setParentId("");
		relatedArticle.setParentName("");
		
		testArticle.setId("3236045"); //TODO once asset creation is dynamic, do not set article id here
	}
	
	@Given("I update the article headline to \"([^\"]*)\"")
	public void updateArticleHeadline(String changedHeadline){
		testArticle.setHeadline(changedHeadline + " " + DateTime.now());
		testArticle.setId("3236851"); //TODO remove this when we're able to create data dynamically
		cmsHelper.updateArticle(testArticle);
		
		//give chance for Feed to update before validation steps
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("I update the unpublished article headline to \"([^\"]*)\"")
	public void updateUnpublishedArticleHeadline(String changedHeadline){
	/*
	 *TODO
	 * Place holder / sugar method so that I can set article id to that of an unpublished article intended solely for Update Field tests.
	 *  Need to remove this method when we can create data dynamically.
	 * */
		testArticle.setHeadline(changedHeadline + DateTime.now());
		testArticle.setId("3236853");
		cmsHelper.updateArticle(testArticle);
	}
	
	@When("I use the Article URL returned to make a request for the Article")
	public void followArticleUrlInArticleEntry(){
		followLinkInEntry(testArticle);
	}
	
	@When("I make a request for the Article")
	public void makeRequestForArticle(){
		System.out.println("makeRequestForArticle - " + testArticle.getClass().hashCode());
		feedRequester.makeSportsNavFeedArticleRequest(testArticle.getId());
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
		System.out.println(responseBody);
		System.out.println(statusCode);	
	}
	
	@When("I use the \"([^\"]*)\" Article URL returned to make a request for the Article")
	public void followColumnistEntryLink(String articleType){
		followLinkInEntry(testArticle);
	}

	@Then("I get the Article response with the correct mandatory fields")
	public void checkSectionArticle(){
		checkMandatoryFields(testArticle, testArticle.getHeadline());
	}
			
	@Then("there is no Article entry for that Article")
	public void checkNoArticleEntryInResponse(){
		//Check that there is no article entry for the "related" Article
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("/feed/entry/category[@term=\"article\"]", "");

		System.out.println("sectionIndex is: " + sectionIndex);
		assertThat(sectionIndex, is(-1));
	}

	@Then("there is no Article entry for the second Article")
	public void checkNoArticleEntryForSecondArticle(){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("//feed/entry/link[@href=\"" + baseFeedDomain + "article/" + relatedArticle.getId() + "\"]", "");

		System.out.println("sectionIndex is: " + sectionIndex);
		assertThat(sectionIndex, is(-1));
	}
	
	@Then("the Article contains correct fields")
	public void checkArticleResponseContainsCorrectFields(){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_ID);
		assertThat(value, equalTo(testArticle.getId()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_TITLE);
		assertThat(value, equalTo(testArticle.getTitle()));

		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_HEADLINE);
		assertThat(value, equalTo(testArticle.getHeadline()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_STANDFIRST);
		assertThat(value, equalTo(testArticle.getStandFirst()));
			
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_BODY);
		assertThat(value, equalTo(testArticle.getBody()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_SECTION_NAME_OVR);
		assertThat(value, equalTo(testArticle.getSectionNameOverride()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_TITLE_PREFIX);
		assertThat(value, equalTo(testArticle.getTitleprefix()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_HEADLINE_OVR);
		assertThat(value, equalTo(testArticle.getHeadlineTitleOverride()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_STANDFIRST_OVR);
		assertThat(value, equalTo(testArticle.getArticleStandFirstOverride()));

		value = xmlReader.getValueAtPath(XPATH_TO_FEED_ARTICLE_BODY_OVR);
		assertThat(value, equalTo(testArticle.getArticleBodyOverride()));

	}
	
	@Then("the Article has the Video entry with the correct fields")
	public void checkVideoEntryIsCorrect(){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("//entry/id", baseFeedDomain + "video/" + testVideo.getId());
		
		System.out.println("sectionIndex is: " + sectionIndex);
		assertThat(sectionIndex, not(-1));
		
		sectionIndex++;
		
		String value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/title");
		assertThat(value, equalTo(testVideo.getVideoTitle()));

		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/author/name");
		assertThat(value, equalTo(testVideo.getAuthorFirstName() + " " + testVideo.getAuthorLastName()));	
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/author/uri");
		assertThat(value, equalTo(testVideo.getAuthorUri()));
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/content");
		assertThat(value, equalTo(testVideo.getId()));
		
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/link/@href");
		assertThat(value, equalTo(testVideo.getFeedLink()));
	
		value = xmlReader.getValueAtPath("/feed/entry[" + sectionIndex + "]/category/@term");
		assertThat(value, equalTo("video"));
	}
	
	@Then("there are no Image Entries in the Article response")
	public void checkArticleHasNoImageEntries(){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("/feed/entry/category[@term=\"image\"]", "");

		System.out.println("sectionIndex is: " + sectionIndex);
	}
}

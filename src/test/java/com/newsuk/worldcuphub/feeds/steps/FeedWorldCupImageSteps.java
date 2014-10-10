package com.newsuk.worldcuphub.feeds.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.newsuk.common.utilities.JsonReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.ImageModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeedWorldCupImageSteps extends FeedWorldCupBaseSteps{
	
	private static String IMAGE_ID = "imageId.value";
	private static String IMAGE_TITLE = "imageTitle.value";
	private static String IMAGE_NAME = "imageName.value";
	private static String IMAGE_CAPTION = "imageCaption.value";
	private static String IMAGE_CREDIT = "imageCredit.value";

	@Given("a Cover Image exists under the WCH Article")
	public void createCoverImageForArticle(){
		testArticle = new ArticleModel();
		testArticle.setId("3238167");
		testArticle.setDefaultValues(baseFeedUrl);
		testArticle.setHeadline("article with cover image");
		testArticle.setBody("test body");
		testArticle.setTitle("title");
		testArticle.setByline("byline");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("an Image exists under the WCH Article")
	public void createArticleWithImage(){
		testArticle = new ArticleModel();
		testArticle.setId("3238592");
		testArticle.setDefaultValues(baseFeedUrl);
		testArticle.setByline("byline");
		testArticle.setHeadline("article with image");
		
		ImageModel image = new ImageModel();
		image.setId("258283");
		image.setAuthorFirstName("The");
		image.setAuthorLastName("Times");
		image.setCaption("local caption");
		image.setTitle("Pinger locator used to find the Malaysia Airlines black box - BBC News");	
		image.setHeadline("Pinger locator used to find the Malaysia Airlines black box - BBC News");
		image.setName("Pinger locator used to find the Malaysia Airlines black box - BBC News");
		image.setFileName("Pinger_locator_used_258283a.jpg");
		image.setCredit("credit");
		testArticle.addImage(image);
		
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("(\\d+) images exists under the WCH Article")
	public void createMultipleImages(int numImages){
		//using static data - hard coding 11 images for tests
		testArticle = new ArticleModel();
		testArticle.setId("3238948");
		testArticle.setDefaultValues(baseFeedUrl);
		testArticle.setHeadline("article with 11 images");
		testArticle.setFeedLink(baseFeedUrl + "article/" + testArticle.getId());
		testArticle.setBody("body");
		testArticle.setTitle("title");
		testArticle.setSectionPageHeadline("Headline section page");
		testArticle.setByline("byline override");
		testArticle.setBylineOverride("byline override");
		testArticle.setStandFirst("standfirst");
		
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
	
	@When("I follow the link to the WCH image")
	public void followLinkInImageEntry(){
		followLinkInEntry(testArticle.getImageList().get(0));
	}
	
	@Then("there are Entries for first (\\d+) Images of the WCH article")
	public void checkImageEntriesFromArticleArePresent(int numImages){
		List<ImageModel> imageList = testArticle.getImageList();

		for(int i=0; i<numImages; i++){
			checkEntryPresent(imageList.get(i));	
		}
	}
	
	@Then("the WCH article contains the Image entry")
	public void checkImageEntryIsCorrect(){
		checkEntryIsCorrect(testArticle.getImageList().get(0));
	}
	
	@Then("I get a WCH image response with the correct fields")
	public void checkImageFields(){
		ImageModel image = testArticle.getImageList().get(0);
		
		String value = JsonPath.read(responseBody, IMAGE_ID);
		assertThat(value, equalTo(image.getId()));
		
		value = JsonPath.read(responseBody, IMAGE_TITLE);
		assertThat(value, equalTo(image.getTitle()));
		
		value = JsonPath.read(responseBody, IMAGE_NAME);
		assertThat(value, equalTo(image.getName()));
		
		value = JsonPath.read(responseBody, IMAGE_CAPTION);
		assertThat(value, equalTo(image.getCaption()));
		
		value = JsonPath.read(responseBody, IMAGE_CREDIT);
		assertThat(value, equalTo(image.getCredit()));
	}
	
	@Then("there is version \"([^\"]*)\" in the image list")
	public void checkImageVersionIsInImageList(String imageVersion){	
		ImageModel image = testArticle.getImageList().get(0);

		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody); 
		int sectionIndex = jsonReader.getIndexOfNodeWithValue("imageurls.imageList[*].imageversion.value", imageVersion);

		assertThat(sectionIndex, greaterThan(-1));
		
		String value = JsonPath.read(responseBody, "imageurls.imageList["+sectionIndex+"].imageUrl.value");
		assertThat(value, equalTo(baseWorldCupFeedDomain + "tto/multimedia/archive/00258/" + image.getFileName()));		
	}
	
	@Then("there is no Video entry for the unpublished WCH Video")
	public void checkNoVideoEntry(){
		checkEntryNotPresent(testArticle.getVideoList().get(0));
	}
	
	@Then("the first (\\d+) images are present in the readPane response")
	public void checkImagesPresentInReadPaneResponse(int numImages){
		List<ImageModel> list = testArticle.getImageList();
		String id = null;
		String imageUrl=null;
		for(int i=0; i<numImages; i++){
			id = list.get(i).getId();
			imageUrl = JsonPath.read(responseBody, "images[" + i + "].imageurl");
			assertThat(imageUrl, containsString(id));
		}
	}

}

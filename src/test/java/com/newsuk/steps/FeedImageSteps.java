package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import com.newsuk.common.utilities.FeedEngineRequester;
import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ImageModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * A class providing the cucumber steps for image-based operation in feeds
 */
public final class FeedImageSteps extends FeedBaseSteps {

	private static final String FEED_IMAGE_VERSION = "/feed/imageurls/image/imageversion";

	private List<String> imageIds;


	/**
	 * Initializes a new instance of the FeedImageSteps type
	 */
	public FeedImageSteps(){
		// populate with default image ids
		imageIds = new ArrayList<String>(Arrays.asList("258283", "258265", "258268"));
	}


	@Given("there are (\\d+) Images attached to the Article")
	public void addImagesToArticle(int numImages){
		if (numImages > imageIds.size())
			throw new IndexOutOfBoundsException("Cannot add " + numImages + "images to the article - as only "+ imageIds.size() +" are specified in the collection");

		configureArticleImages(this.imageIds.subList(0, numImages-1));
	}

	@Given("an Image exists under the Article")
	public void addImageToArticle(){
		configureArticleImages(this.imageIds.subList(0, 1));
	}

	@When("I make a request for Image number (\\d+) from the Article")
	public void requestForImage(int imageNum){
		String imageId = testArticle.getImageList().get(imageNum-1).getId();

		FeedEngineRequester feedRequester = new FeedEngineRequester();
		feedRequester.makeSportsNavFeedImageRequest(imageId);
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();

		System.out.println(responseBody);
		System.out.println(statusCode);	
	}

	@When("I update the Image description to \"([^\"]*)\"")
	public void updateImage(String changedDescription){
		testArticle.setId("3236851"); //TODO remove when able to create data dynamically
		testArticle.getImageList().get(0).setId("259134"); //TODO remove when able to create data dynamically
		
		testArticle.getImageList().get(0).setDescription(changedDescription);
		cmsHelper.updateImage(testArticle.getImageList().get(0));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@When("I make a request for the Image")
	public void requestImageFeed(){
		feedRequester.makeSportsNavFeedImageRequest(testArticle.getImageList().get(0).getId());
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
	}
	
	@Then("there are Image Entries for first (\\d+) Images")
	public void checkImageEntriesFromArticleArePresent(int numImages){
		List<ImageModel> imageList = testArticle.getImageList();

		for(int i=0; i<numImages; i++){
			checkEntryPresent(imageList.get(i));	
		}
	}

	@Then("there are (\\d+) Image Entries in the Article response")
	public void checkNumImagesIn(int numImages){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int value = xmlReader.getNumberOfElements("/feed/entry");
		assertThat(value, is(numImages));
	}
	
	@Then("I get an Image Response and the Image version is \"([^\"]*)\"")
	public void checkImageVersionIs(String imageVersion){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);

		String value = xmlReader.getValueAtPath(FEED_IMAGE_VERSION);
		assertThat(value, equalTo(imageVersion));
	}
	
	/**
	 * Sets the image Ids for the current article
	 * @param imageIds   The array of image ids to set
	 */
	public void setImageIds(String...imageIds) {
		this.imageIds = new ArrayList<String>();

		for (String id : imageIds) {
			this.imageIds.add(id);
		}
	}


	/**
	 * Add the images identified by id value to an article
	 * @param article  The article to add the images to
	 * @param imageIds  The list of image id values to add
	 */
	public void configureArticleImages(List<String> imageIds) {
		ImageModel imageArticle = null;

		for (int i=0; i<imageIds.size(); i++) {
			imageArticle = new ImageModel();
			imageArticle.setId(imageIds.get(i));
			testArticle.addImage(imageArticle);
		}
	}

}


 
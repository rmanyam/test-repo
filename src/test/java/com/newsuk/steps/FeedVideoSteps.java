package com.newsuk.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.google.api.client.http.GenericUrl;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.BaseCmsModel;
import com.newsuk.model.feeds.VideoModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FeedVideoSteps extends FeedBaseSteps {

	//Video fields
	private static final String XPATH_TO_FEED_VIDEO_ID = "/feed/videoid";
	private static final String XPATH_TO_FEED_VIDEO_TITLE = "/feed/videotitle";
	private static final String XPATH_TO_FEED_VIDEO_CAPTION = "/feed/videocaption";
	private static final String XPATH_TO_FEED_VIDEO_EMBEDED_CODE = "/feed/videoembedcode";
	private static final String XPATH_TO_FEED_VIDEO_PLAYER_ID = "/feed/videoplayerid";
	
	@Given("a Video exists under the Article")
	public void createVideo(){		
		testVideo = new VideoModel();
		testVideo.setId("3236269");
		testVideo.setVideoTitle("Wayne Rooney bicycle kick. Best quality.mp4");
		testVideo.setVideoEmbededCode("Nwb3AybTqOQDm9ZvOPIFAQRhWYN89pyb");
		testVideo.setAuthorFirstName("The");
		testVideo.setAuthorLastName("Times");
		testVideo.setAuthorUri("http://www.thetimes.co.uk/tto/news/");
		testVideo.setFeedLink(baseFeedDomain + "video/3236269");
		testVideo.setVideoCaption("test caption");
		testVideo.setVideoPlayerId("OTZiZjhjOWQ5ZDc2ZTE1ZjI5OTEzZThi"); //TODO move to ENV property file
		
		testArticle.addVideo(testVideo);
		System.out.println("createVideo - " + testArticle.getClass().hashCode());
	}
	
	@Given("an unpublished Video exists under the Article")
	public void createUnpublishedVideo(){
		testVideo = new VideoModel();
		testVideo.setId("3235947");
		testVideo.setState("unpublished");
	}
	
	@When("I make a request for the Video")
	public void makeRequestForVideo(){
		feedRequester.makeSportsNavFeedVideoRequest(testVideo.getId());
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
		System.out.println(responseBody);
		System.out.println(statusCode);	
	}
	
	@Then("I use the Video URL returned to make a request for the Video")
	public void followVideoUrlInVideoEntry(){
		followLinkInEntry(testVideo);
	}
	
	@Then("I get a Video response contains correct fields")
	public void checkVideoResponseHasCorrectFields(){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(XPATH_TO_FEED_VIDEO_ID);
		assertThat(value, equalTo(testVideo.getId()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_VIDEO_TITLE);
		assertThat(value, equalTo(testVideo.getVideoTitle()));

		value = xmlReader.getValueAtPath(XPATH_TO_FEED_VIDEO_CAPTION);
		assertThat(value, equalTo(testVideo.getVideoCaption()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_VIDEO_EMBEDED_CODE);
		assertThat(value, equalTo(testVideo.getVideoEmbededCode()));
			
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_VIDEO_PLAYER_ID);
		assertThat(value, equalTo(testVideo.getVideoPlayerId()));
	}
	
	@Then("there is no Video entry for the unpublished Video")
	public void checkNoVideoEntry(){
		checkEntryNotPresent(testVideo);
	}
	
	protected void followLinkInEntry(BaseCmsModel cmsAsset){
		String assetType;
		
		if(cmsAsset instanceof ArticleModel){
			assetType = "article";
		}
		else if(cmsAsset instanceof VideoModel){
			assetType = "video";
		}
		else{
			assetType = "section";
		}
		
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("//entry/id", baseFeedDomain + assetType + "/" + cmsAsset.getId());
		sectionIndex++;
		String urlString = xmlReader.getValueAtPath("/feed/entry["+sectionIndex+"]/link/@href");

		GenericUrl url = new GenericUrl(urlString);
		HttpRequestBuilder httpRequester = new HttpRequestBuilder();
		
		responseBody = httpRequester.doHttpGetRequest(url).getBody();
		System.out.println(responseBody);
	}
	
}

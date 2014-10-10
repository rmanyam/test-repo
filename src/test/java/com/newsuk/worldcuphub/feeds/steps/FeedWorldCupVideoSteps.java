package com.newsuk.worldcuphub.feeds.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.jsonpath.JsonPath;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.ImageModel;
import com.newsuk.model.feeds.VideoModel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class FeedWorldCupVideoSteps extends FeedWorldCupBaseSteps{
	
	private static String VIDEO_ID = "videoId.value";
	private static String VIDEO_TITLE = "videoTitle.value";
	private static String VIDEO_CAPTION = "videoCaption.value";
	private static String VIDEO_EMBEDED_CODE = "videoEmbedCode.value";
	private static String VIDEO_PLAYER_ID = "videoPlayerId.value";
	
	@Given("a Video exists under the WCH Article")
	public void createVideoUnderArticle(){
		testArticle = new ArticleModel();
		testArticle.setId("3238786");
		testArticle.setDefaultValues(baseFeedUrl);
		testArticle.setHeadline("article with video");
		testArticle.setByline("byline override");
		testArticle.setTitle("title");
		
		VideoModel video = new VideoModel();
		video.setId("3236929");
		video.setAuthorFirstName("The");
		video.setAuthorLastName("Times");
		video.setHeadline("Japan's New Komeito Party's leader arrives in Beijing to mend Sino-Japanese ties");
		video.setTitle("Japan's New Komeito Party's leader arrives in Beijing to mend Sino-Japanese ties");
		video.setVideoTitle("Japan's New Komeito Party's leader arrives in Beijing to mend Sino-Japanese ties");
		video.setVideoPlayerId("OTZiZjhjOWQ5ZDc2ZTE1ZjI5OTEzZThi");
		video.setVideoCaption("STORY: China-Japan Relations -- Japan's New Komeito Party's leader arrives in Beijing to mend Sino-Japanese ties ");
		video.setVideoEmbededCode("dnaTdkbTqQEZthJkynrF2Qfdydbzn9ow");
		
		testArticle.addVideo(video);
		
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("an unpublished Video exists under the WCH Article")
	public void createUnpublishedVideo(){
		testArticle = new ArticleModel();
		testArticle.setId("3238870");
		testArticle.setDefaultValues(baseWorldCupFeedDomain);
		testArticle.setHeadline("Article with Unpublished Video");
		testArticle.setByline("Article with Unpublished Video");
		
		VideoModel video = new VideoModel();
		video.setId("3170740");
		testArticle.addVideo(video);
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@Given("an unpublished WCH Article with video exists under the Section")
	public void createUnpublishedArticleWithVideo(){
		testArticle = new ArticleModel();
		testArticle.setId("3238875");
		cmsHelper.moveArticleToBrick(testArticle.getId(), testSection.getId(), "worldcupHubFiftyFiftyContent", true);
	}
	
	@When("I use the Video URL returned to make a request for the WCH Video")
	public void followVideoUrl(){
		followLinkInEntry(testArticle.getVideoList().get(0));
	}
	
	@When("I make a request for the WCH Video")
	public void makeVideoRequest(){
		feedRequester.makeWorldCupHubFeedVideoRequest(testArticle.getVideoList().get(0).getId());
		responseBody = feedRequester.getResponseBody();
		statusCode = feedRequester.getResponseCode();
	}
	
	@Then("the WCH article contains the Video entry")
	public void checkArticleContainsVideoEntry(){
		checkEntryIsCorrect(testArticle.getVideoList().get(0));
	}
	
	@Then("I get a WCH Video response contains correct fields")
	public void checkVideoHasCorrectFields(){
		VideoModel video = testArticle.getVideoList().get(0);
		
		String value = JsonPath.read(responseBody, VIDEO_ID);
		assertThat(value, equalTo(video.getId()));
		
		value = JsonPath.read(responseBody, VIDEO_TITLE);
		assertThat(value, equalTo(video.getTitle()));
		
		value = JsonPath.read(responseBody, VIDEO_CAPTION);
		assertThat(value, equalTo(video.getVideoCaption()));
		
		value = JsonPath.read(responseBody, VIDEO_EMBEDED_CODE);
		assertThat(value, equalTo(video.getVideoEmbededCode()));
		
		value = JsonPath.read(responseBody, VIDEO_PLAYER_ID);
		assertThat(value, equalTo(video.getVideoPlayerId()));
	}
}

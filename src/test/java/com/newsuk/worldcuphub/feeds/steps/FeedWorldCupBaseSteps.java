package com.newsuk.worldcuphub.feeds.steps;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.google.api.client.http.GenericUrl;
import com.jayway.jsonpath.JsonPath;
import com.newsuk.common.utilities.CmsHelper;
import com.newsuk.common.utilities.EnvironmentProvider;
import com.newsuk.common.utilities.FeedEngineRequester;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.JsonReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.BaseCmsModel;
import com.newsuk.model.feeds.ImageModel;
import com.newsuk.model.feeds.SectionModel;
import com.newsuk.model.feeds.VideoModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FeedWorldCupBaseSteps {
	
	//Mandatory fields
	private static final String XPATH_TO_FEED_ID = "$id.value";
	private static final String XPATH_TO_FEED_TITLE = "$title.value";
	private static final String XPATH_TO_FEED_UPDATED = "$updated.value";
	private static final String XPATH_TO_FEED_AUTHOR_NAME = "$author.name";
	private static final String XPATH_TO_FEED_LINK = "$link.href";
	private static final String XPATH_TO_FEED_CATEGORY= "$category.term";
	private static final String XPATH_TO_FEED_RIGHTS = "$rights.value";
	
	protected FeedEngineRequester feedRequester;
	protected CmsHelper cmsHelper;
	protected EnvironmentProvider environmentProvider;
	
	protected static SectionModel testSection;
	protected SectionModel testSubSection;
	protected static ArticleModel testArticle;
	protected static ArticleModel testArticle2;
	protected ArticleModel relatedArticle;
	protected ImageModel coverImage;
	protected static VideoModel testVideo;
	
	protected static String baseFeedUrl;
	protected static String responseBody;
	protected static String baseWorldCupFeedDomain;
	protected static int statusCode;
	
	public FeedWorldCupBaseSteps(){
		feedRequester = new FeedEngineRequester();
		cmsHelper = new CmsHelper();
		testSubSection = new SectionModel();
		environmentProvider = new EnvironmentProvider();
		baseFeedUrl = environmentProvider.getBaseFeedsWorldCupUrl();
		baseWorldCupFeedDomain = environmentProvider.getBaseWorldCupFeedDomain();
	}

	protected void followLinkInEntry(BaseCmsModel cmsAsset){
		String assetType = getAssettype(cmsAsset);

		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody); 
		int sectionIndex = jsonReader.getIndexOfNodeWithValue("entries[*].id.value", baseWorldCupFeedDomain + "worldcuphub/" + assetType + "/" + cmsAsset.getId());
		
		assertThat(sectionIndex, greaterThan(-1));
		
		String urlString = JsonPath.read(responseBody, "entries["+sectionIndex+"].link.href");

		GenericUrl url = new GenericUrl(urlString);
		HttpRequestBuilder httpRequester = new HttpRequestBuilder();
		
		responseBody = httpRequester.doHttpGetRequest(url).getBody();
		System.out.println(responseBody);
	}
	
	protected void checkMandatoryFields(BaseCmsModel cmsTypeAsset, String expectedTitle){	
		String value = JsonPath.read(responseBody, XPATH_TO_FEED_ID);
		//assertThat(value, equalTo(cmsTypeAsset.getFeedLink()));
		
		value = JsonPath.read(responseBody, XPATH_TO_FEED_TITLE);
		assertThat(value, equalTo(expectedTitle));
		
		value = JsonPath.read(responseBody, XPATH_TO_FEED_UPDATED);
		System.out.println("Date: " + value);

		if(cmsTypeAsset instanceof ArticleModel && StringUtils.isNotBlank(((ArticleModel) cmsTypeAsset).getByline())){
			value = JsonPath.read(responseBody, XPATH_TO_FEED_AUTHOR_NAME);
			assertThat(value, equalTo(cmsTypeAsset.getByline()));
		}
		else{
			value = JsonPath.read(responseBody, XPATH_TO_FEED_AUTHOR_NAME);
			assertThat(value, equalTo(cmsTypeAsset.getAuthorFirstName() + " " + cmsTypeAsset.getAuthorLastName()));	
		}
		
		value = JsonPath.read(responseBody, XPATH_TO_FEED_LINK);
		//assertThat(value, equalTo(cmsTypeAsset.getFeedLink()));

		value = JsonPath.read(responseBody, XPATH_TO_FEED_CATEGORY);
		assertThat(value, equalTo(cmsTypeAsset.getCategory()));
		
		value = JsonPath.read(responseBody, XPATH_TO_FEED_RIGHTS);
		assertThat(value, equalTo("Copyright Times Newspapers Ltd"));
	}
	
	protected void checkEntryIsCorrect(BaseCmsModel cmsTypeAsset){
		String assetType = getAssettype(cmsTypeAsset);

		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody); 
		int sectionIndex = jsonReader.getIndexOfNodeWithValue("entries[*].id.value", baseWorldCupFeedDomain + "worldcuphub/" + assetType + "/" + cmsTypeAsset.getId());
		
		assertThat(sectionIndex, greaterThan(-1));
			
		String value = JsonPath.read(responseBody, "entries[" + sectionIndex + "].title.value");
		assertThat(value, equalTo(cmsTypeAsset.getHeadline()));
		
		value = JsonPath.read(responseBody, "entries[" + sectionIndex + "].author.name");
		assertThat(value, equalTo(cmsTypeAsset.getAuthorFirstName() + " " + cmsTypeAsset.getAuthorLastName()));
		
		value = JsonPath.read(responseBody, "entries[" + sectionIndex + "].content.value");
		assertThat(value, equalTo(cmsTypeAsset.getId()));
		
		value = JsonPath.read(responseBody, "entries[" + sectionIndex + "].link.href");
		assertThat(value, equalTo(baseWorldCupFeedDomain + "worldcuphub/" + assetType + "/" + cmsTypeAsset.getId()));
		
		value = JsonPath.read(responseBody, "entries[" + sectionIndex + "].category.term");
		assertThat(value, equalTo(cmsTypeAsset.getCategory()));
	}
	
	protected void checkEntryNotPresent(BaseCmsModel cmsAsset){
		assertThat(isPresent(cmsAsset),is(false));
	}
	
	protected void checkEntryPresent(BaseCmsModel cmsAsset){
		assertThat(isPresent(cmsAsset),is(true));
	}
	
	protected void updateArticleHeadline(String changedHeadline){
		testArticle.setHeadline(changedHeadline + " " + DateTime.now());
		cmsHelper.updateArticle(testArticle);
		
		//give chance for Feed to update before validation steps
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean isPresent(BaseCmsModel cmsAsset){
		String assetType = getAssettype(cmsAsset);

		JsonReaderHelper jsonReader = new JsonReaderHelper(responseBody); 
		int sectionIndex = jsonReader.getIndexOfNodeWithValue("entries[*].id.value", baseWorldCupFeedDomain + "worldcuphub/" + assetType + "/" + cmsAsset.getId());

		if(sectionIndex == -1){
			return false;
		}
		else{
			return true;
		}
	}
	
	private String getAssettype(BaseCmsModel cmsAsset){
		String assetType;
		
		if(cmsAsset instanceof ArticleModel){
			assetType = "article";
		}
		else if(cmsAsset instanceof VideoModel){
			assetType = "video";
		}
		else if(cmsAsset instanceof ImageModel){
			assetType = "image";
		}
		else{
			assetType = "section";
		}
		return assetType;
	}
	
}

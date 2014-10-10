package com.newsuk.steps;

import org.apache.commons.lang3.StringUtils;

import com.google.api.client.http.GenericUrl;
import com.newsuk.common.utilities.CmsHelper;
import com.newsuk.common.utilities.EnvironmentProvider;
import com.newsuk.common.utilities.FeedEngineRequester;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.BaseCmsModel;
import com.newsuk.model.feeds.ImageModel;
import com.newsuk.model.feeds.SectionModel;
import com.newsuk.model.feeds.VideoModel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FeedBaseSteps {
	
	//Mandatory fields
	private static final String XPATH_TO_FEED_ID = "/feed/id";
	private static final String XPATH_TO_FEED_TITLE = "/feed/title";
	private static final String XPATH_TO_FEED_UPDATED = "/feed/updated";
	private static final String XPATH_TO_FEED_AUTHOR_NAME = "/feed/author/name";
	private static final String XPATH_TO_FEED_AUTHOR_URI = "/feed/author/uri";
	private static final String XPATH_TO_FEED_LINK = "/feed/link/@href";
	private static final String XPATH_TO_FEED_CATEGORY= "/feed/category/@term";
	private static final String XPATH_TO_FEED_RIGHTS = "/feed/rights";

	protected FeedEngineRequester feedRequester;
	protected CmsHelper cmsHelper;
	protected EnvironmentProvider environmentProvider;
	
	protected static SectionModel testSection;
	protected SectionModel testSubSection;
	protected static ArticleModel testArticle;
	protected ArticleModel relatedArticle;
	protected ImageModel coverImage;
	protected static VideoModel testVideo;
	
	protected static String baseFeedDomain;
	protected static String responseBody;
	protected static int statusCode;
	
	public FeedBaseSteps(){
		feedRequester = new FeedEngineRequester();
		cmsHelper = new CmsHelper();
		//testSection = new SectionModel();
		testSubSection = new SectionModel();
		//testArticle = new ArticleModel();
		environmentProvider = new EnvironmentProvider();
		baseFeedDomain = environmentProvider.getBaseFeedsEngineUrl();
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
	
	protected void checkMandatoryFields(BaseCmsModel cmsTypeAsset, String expectedTitle){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(XPATH_TO_FEED_ID);
		assertThat(value, equalTo(cmsTypeAsset.getFeedLink()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_TITLE);
		assertThat(value, equalTo(expectedTitle));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_UPDATED);
		System.out.println("Date: " + value);

		if(cmsTypeAsset instanceof ArticleModel && StringUtils.isNotBlank(cmsTypeAsset.getByline()) && ((ArticleModel) cmsTypeAsset).getCategory()!="customLink"){
			value = xmlReader.getValueAtPath(XPATH_TO_FEED_AUTHOR_NAME);
			assertThat(value, equalTo(cmsTypeAsset.getByline() ));	
		}
		else{
			value = xmlReader.getValueAtPath(XPATH_TO_FEED_AUTHOR_NAME);
			assertThat(value, equalTo(cmsTypeAsset.getAuthorFirstName() + " " + cmsTypeAsset.getAuthorLastName()));
		}
		
//		value = xmlReader.getValueAtPath(XPATH_TO_FEED_AUTHOR_URI);
//		assertThat(value, equalTo(cmsTypeAsset.getAuthorUri()));
//		Removed because URI is functionality is not required
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_LINK);
		assertThat(value, equalTo(cmsTypeAsset.getFeedLink()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_CATEGORY);
		assertThat(value, equalTo(cmsTypeAsset.getCategory()));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_RIGHTS);
		assertThat(value, equalTo("Copyright Times Newspapers Ltd"));
	}
	
	protected void checkMandatoryFields(String expectedId, String expectedTitle, String feedLink, String category, String author){
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		
		String value = xmlReader.getValueAtPath(XPATH_TO_FEED_ID);
		assertThat(value, equalTo(expectedId));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_TITLE);
		assertThat(value, equalTo(expectedTitle));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_UPDATED);
		System.out.println("Date: " + value);

		value = xmlReader.getValueAtPath(XPATH_TO_FEED_AUTHOR_NAME);
		assertThat(value, equalTo(author));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_AUTHOR_URI);
		assertThat(value, equalTo("http://www.thetimes.co.uk/tto/news/"));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_LINK);
		assertThat(value, equalTo(feedLink));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_CATEGORY);
		assertThat(value, equalTo(category));
		
		value = xmlReader.getValueAtPath(XPATH_TO_FEED_RIGHTS);
		assertThat(value, equalTo("Copyright Times Newspapers Ltd"));
	}
	
	protected void checkEntryNotPresent(BaseCmsModel cmsAsset){
		assertThat(isPresent(cmsAsset),is(false));
	}
	
	protected void checkEntryPresent(BaseCmsModel cmsAsset){
		assertThat(isPresent(cmsAsset),is(true));
	}
	
	private boolean isPresent(BaseCmsModel cmsAsset){
		String assetType = getAssettype(cmsAsset);
		
		XmlReaderHelper xmlReader = new XmlReaderHelper(responseBody);
		int sectionIndex = xmlReader.getIndexOfNodeWithValue("//feed/entry/link[@href=\"" + baseFeedDomain + assetType + "/" + cmsAsset.getId() + "\"]", "");

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

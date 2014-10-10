package com.newsuk.common.utilities;

import com.google.api.client.http.GenericUrl;
import com.newsuk.common.utilities.HttpResponse;

public class FeedEngineRequester {
		
	private HttpRequestBuilder httpRequester;
	private EnvironmentProvider envProvider;
	private HttpResponse response;
	
	public FeedEngineRequester(){
		httpRequester = new HttpRequestBuilder();
		envProvider = new EnvironmentProvider();
		httpRequester.initializeRequestBuilder();
	}
	
	public HttpResponse makeSportsNavigationManifestFeedRequest(){
		String urlString = envProvider.getBaseFeedsEngineUrl();
		return makeRequest(urlString);
	}
	
	public HttpResponse makeSportsNavFeedSectionRequest(String sectionId){
		String urlString = envProvider.getBaseFeedsEngineUrl() + "/section/" + sectionId;
		return makeRequest(urlString);
	}
	
	public HttpResponse makeSportsNavFeedArticleRequest(String articleId){
		String urlString = envProvider.getBaseFeedsEngineUrl() + "article/" + articleId;
		return makeRequest(urlString);
	}

	public HttpResponse makeSportsNavFeedVideoRequest(String videoId){
		String urlString = envProvider.getBaseFeedsEngineUrl() + "/video/" + videoId;
		return makeRequest(urlString);
	}

	public HttpResponse makeSportsNavFeedImageRequest(String imageId){
		String urlString = envProvider.getBaseFeedsEngineUrl() + "/image/" + imageId;
		return makeRequest(urlString);
	}
	
	public HttpResponse makeWorldCupHubFeedRequest(){
		String urlString = envProvider.getBaseFeedsWorldCupUrl();
		return makeRequest(urlString);
	}
	
	public HttpResponse makeWorldCupHubFeedArticleRequest(String articleId){
		String urlString = envProvider.getBaseWorldCupFeedDomain() + "worldcuphub/article/" + articleId;
		return makeRequest(urlString);
	}
	
	public HttpResponse makeWorldCupHubFeedVideoRequest(String videoId){
		String urlString = envProvider.getBaseWorldCupFeedDomain() + "worldcuphub/video/" + videoId;
		return makeRequest(urlString);
	} 
	
	private HttpResponse makeRequest(String urlString) {
		GenericUrl url = new GenericUrl(urlString);
		response = httpRequester.doHttpGetRequest(url); 

		return response;
	}
	
	public int getResponseCode(){
		return response.getStatusCode();
	}
	
	public String getResponseBody(){
			return response.getBody();
	}

}

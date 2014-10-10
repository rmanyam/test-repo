package com.newsuk.infrastructure.tests;

import com.google.api.client.http.GenericUrl;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.HttpResponse;

public class FeedHitter implements Runnable {
	HttpRequestBuilder requester;
	public FeedHitter(){
		requester = new HttpRequestBuilder();
		requester.initializeRequestBuilder();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<200; i++){
			
			String url = "http://feeds.se.newsint.co.uk/timessport?" + i;

			HttpResponse response = requester.doHttpGetRequest(new GenericUrl(url));
		
			System.out.println(Thread.currentThread().getId() + " " + response.getStatusCode());
		}
	}

}

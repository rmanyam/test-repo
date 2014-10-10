package com.newsuk.common.utilities;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

 public class HttpRequestBuilder {
	public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	public static final JsonFactory JSON_FACTORY = new JacksonFactory();
	public static HttpHeaders header = new HttpHeaders();
	public static HttpRequestFactory requestFactory;
	public static HttpResponse response;
	public static String contentType;
	public static String parserType;


	public void setAuthentication(String userName, String password) {
		header.setBasicAuthentication(userName, password);
	}

	public void setContentType(String contentType) {
		header.setContentType(contentType);
 	}

	public void setRequestParser(String parserType)
	{

	}

	public void initializeRequestBuilder() {
		requestFactory = HTTP_TRANSPORT
				.createRequestFactory(new HttpRequestInitializer() {
					//@Override
					public void initialize(HttpRequest request) {
						request.setHeaders(header);
 						request.setParser(new JsonObjectParser(JSON_FACTORY));
					}
				});
	}

	public HttpResponse getHttpResponse()
	{
		return response;
	}

	public void getHttpPostRequest(String contentPath,
			GenericUrl url) throws FileNotFoundException {

		File file = new File(contentPath);
		InputStreamContent fileContent = new InputStreamContent(contentType,
				new BufferedInputStream(new FileInputStream(file)));
		fileContent.setLength(file.length());

		try {
			HttpRequest request = requestFactory.buildPostRequest(url,
					fileContent);
			response = request.execute();
 		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String doHttpPostRequest(String body,
			GenericUrl url) throws FileNotFoundException {


		InputStreamContent fileContent = new InputStreamContent(contentType,new ByteArrayInputStream(body.getBytes() ));
		//fileContent.setLength(file.length());
		fileContent.setLength(body.length());

		try {
			HttpRequest request = requestFactory.buildPostRequest(url,
					fileContent);
			response = request.execute();
			return response.parseAsString();
 		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	public void getHttpGetRequest(GenericUrl url)
	{
		try {
			System.out.println(url);
			HttpRequest request = requestFactory.buildGetRequest(url);
			response = request.execute();
 		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public com.newsuk.common.utilities.HttpResponse doHttpGetRequest(GenericUrl url)
	{
		 com.newsuk.common.utilities.HttpResponse response = new com.newsuk.common.utilities.HttpResponse();

		try {
			System.out.println(url);
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse tempResponse = request.execute();

			response.setStatusCode(tempResponse.getStatusCode());
			response.setReason(tempResponse.getStatusMessage());
			response.setBody(tempResponse.parseAsString());

			return response;
 		} 
		catch (HttpResponseException e) {
			response.setStatusCode(e.getStatusCode());
			response.setReason(e.getStatusMessage());
			e.printStackTrace();	
			return response;
		}
		catch(IOException e){
			e.printStackTrace();	
		}

		return null;
	}



	public void getHttpHeadRequest(GenericUrl url)
	{
		try {
			HttpRequest request = requestFactory.buildHeadRequest(url);
			response = request.execute();
 		} catch (IOException e) {

			e.printStackTrace();
		}
	}	



}
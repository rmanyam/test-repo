package com.newsuk.image.version.service.steps;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Time;

import com.google.api.client.http.GenericUrl;
import com.newsuk.common.utilities.CmsHelper;
import com.newsuk.common.utilities.EnvironmentProvider;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.steps.FeedBaseSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ImageVersionServiceSteps extends FeedBaseSteps {
	
	private static final String XPATH_TO_IMAGE_VERSIONS = "/image/multimedia";
	
	private String newImageId;
	private int numVersions;
	
	public ImageVersionServiceSteps(){
		super();
	}
	
	
	@Given("a new large image is created in Escenic with source as \"([^\"]*)\"")
	public void createImageWithSource(String source){
		URL urlPath = getClass().getClassLoader().getResource("data" + File.separator +  "cmsWebServiceTemplates" + File.separator + "large.jpg");
		String contentPath = null;
		try {
			contentPath = URLDecoder.decode(urlPath.getPath(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CmsHelper cms = new CmsHelper();
		if(null !=contentPath)
		newImageId = cms.createImageInCms("default name ar", "default description ar", source, "large.jpg", contentPath);
		
		try {
			Thread.sleep(45000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Given("a new large image is created in Escenic from Eidos Methode")
	public void createImage(){
		createImageWithSource("EidosMedia");
	}
	
	@When("I look at the image details")
	public void makeImageInformationRequest(){
		getImageInformationFromEscenic();
	}
	
	@Then("the image has (\\d+) image versions")
	public void checkNumberOfImageVersionsInResponse(int expectedNumVersions){
		// if our expected result does not match the actual result this is likely a timing issue
		// we will want to rerun the 'get escenic data' step up to a few times
		for(int loopCounter = 0; loopCounter <= 20; loopCounter++){
			if(expectedNumVersions == numVersions)
				break;
			System.out.println("["+getTimeStamp()+"] Number of images is incorrect (expect: " + expectedNumVersions + " actual: " + numVersions + "). Timing issue? Re-pulling data from escenic attempt: " + loopCounter);
			getImageInformationFromEscenic();
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		assertThat(numVersions, equalTo(expectedNumVersions));
	}
	
	private void getImageInformationFromEscenic(){
		EnvironmentProvider envProvider = new EnvironmentProvider();
		String webserviceUrl = envProvider.getCmsWebServiceImageUrl() + newImageId;
		HttpRequestBuilder httpRequester = new HttpRequestBuilder();
		httpRequester.initializeRequestBuilder();
		
		GenericUrl url = new GenericUrl(webserviceUrl);
		System.out.println("["+getTimeStamp()+"] Endpoint to check image details: " + url);
		String response = httpRequester.doHttpGetRequest(url).getBody(); 
		
		XmlReaderHelper xmlReader = new XmlReaderHelper(response);
		numVersions = xmlReader.getNumberOfElements(XPATH_TO_IMAGE_VERSIONS);
		System.out.println("["+getTimeStamp()+"] " + response);

	}
	
	private String getTimeStamp(){
		return (new java.text.SimpleDateFormat("HH:mm:ss")).format(java.util.Calendar.getInstance().getTime());		
	}
}

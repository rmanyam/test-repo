package com.newsuk.steps;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.PageActions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class StepDefinitions 
{
 
	PageActions pageAction = new PageActions();
	HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
    com.newsuk.common.utilities.HttpResponse response;
	public String URL;

 
@Given("^I have the HTTP Request$")
public void I_have_the_HTTP_Request()
{
  System.out.println("In Given");
  requestBuilder.initializeRequestBuilder();
}

@When("^I get the json feed from CommentaryFeed$")
public void I_get_the_json_feed_from_CommentaryFeed() throws IOException{
	requestBuilder.getHttpGetRequest(new GenericUrl("http://epl-dev3.uat-thetimes.co.uk/files/v1_videosDesktop_latest.json"));
}

 

@Given("^I set Http Authorization username (.+) with password (.+)$")
public void I_set_Http_Authentication_Headers(String userName,String password)
{
	requestBuilder.setAuthentication(userName, password);
}

@Given("^I set Http Request content type as (.+)$")
public void I_set_Http_ContentType_Header(String contentType)
{
	requestBuilder.setContentType(contentType);
}


@When("^I Post an xml from \"([^\"]*)\" To endpoint \"([^\"]*)\"$")
public void I_Post_An_Article_To_Escenic(String contentPath,String url)
{	  
	String userDirectory = System.getProperty("user.dir");
	System.out.println(url+" "+contentPath);
	contentPath=userDirectory+File.separator+"data"+File.separator+"OptaEvents"+File.separator+contentPath;
 try {
	requestBuilder.getHttpPostRequest(contentPath, new GenericUrl(url));
} catch (FileNotFoundException e) {
	 e.printStackTrace();
}	 
}

 @Given("^I have the article URL \"([^\"]*)\"$")
 public void I_have_the_URL(String url)
 {
	 System.out.println("Article URL:"+ url);
	 URL = url;
 }
 @Then("^I get a http success code$")
 public void I_get_the_status_code_as() throws IOException
 {
	 Assert.assertTrue(requestBuilder.getHttpResponse().isSuccessStatusCode());
	 System.out.println(requestBuilder.getHttpResponse().getStatusMessage());
	 System.out.println(requestBuilder.getHttpResponse().getHeaders().toString());
 }
 
 @When("^I make a get request to the URL \"([^\"]*)\"$")
 public void I_make_a_get_request_to_the_URL(String url) {
	 	 
   response = requestBuilder.doHttpGetRequest(getGenericUrl(url));
	  
}
 

private GenericUrl getGenericUrl(String url) {
	GenericUrl gURL = new GenericUrl(url);
    return gURL;
}

@Then("^I should get the response code as \"([^\"]*)\"$")
 public void I_should_get_the_response_code_as(int responseCode) {
	
	System.out.println(response.getStatusCode());
	Assert.assertEquals(responseCode,response.getStatusCode());    
 } 
 
 @Then("^I open the article on \"([^\"]*)\"$")
 public void I_open_the_article_on_browser(String browser) {
	 pageAction.setBrowser(browser);;
	 pageAction.checkArticlePage();
	 pageAction.openArticlePage(URL);
    
 }
 
 @Then("^The article should have \"([^\"]*)\" as headline$")
 public void The_article_should_have_as_headline(String headline)  {
	 pageAction.checkArticlePage();
	 pageAction.highlightElement(pageAction.article.getHeadingTitle());
	 Assert.assertEquals(pageAction.article.getHeadingTitle().getText(),headline);
 }
 
 @Then("^The article should have \"([^\"]*)\" as standfirst$")
 public void The_article_should_have_as_standfirst(String standfirst) {
	 pageAction.checkArticlePage();
	 pageAction.highlightElement(pageAction.article.getStandFirst());
	 Assert.assertEquals(pageAction.article.getStandFirst().getText(),standfirst);
      
 }
 
 @Then("^The article should have \"([^\"]*)\" as byline$")
 public void The_article_should_have_as_byline(String byline) {
	 pageAction.checkArticlePage();
	 pageAction.highlightElement(pageAction.article.getByLine());
	 Assert.assertEquals(pageAction.article.getByLine().getText(),byline);
      
 }
 @Then("^The article should have \"([^\"]*)\" as author$")
 public void The_article_should_have_as_author(String author) {
	 pageAction.checkArticlePage();
	 pageAction.highlightElement(pageAction.article.getAuthor());
	 Assert.assertEquals(pageAction.article.getAuthor().getText(),author);
      
 }
 @Then("^The article should have last Updated date$")
 public void The_article_should_have_as_lastUpdated() {
	 pageAction.checkArticlePage();
	 pageAction.highlightElement(pageAction.article.getLastUpdated());
	 Assert.assertTrue(!pageAction.article.getLastUpdated().getText().isEmpty());
      
 }
 
 
  

 @Then("^I close the article$")
 public void I_close_the_article()  
 {
     pageAction.closeDriver();
 }


}
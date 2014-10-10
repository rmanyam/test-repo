package com.newsuk.steps;

import com.google.api.client.http.GenericUrl;
import com.newsuk.common.utilities.HttpRequestBuilder;
import com.newsuk.common.utilities.HttpResponse;
import com.newsuk.common.utilities.XmlReaderHelper;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.ArticleType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;
import org.junit.Assert;

/**
 * Created by ranjithmanyam on 26/08/2014.
 */
public class PushNotificationTestSteps extends FeedBaseSteps {

    private String articleId;
    private HttpRequestBuilder builder;
    private HttpResponse response;
    private ArticleType articleType;


    public PushNotificationTestSteps(){
        builder = new HttpRequestBuilder();
        builder.initializeRequestBuilder();

    }


    @Given("^I have a ([^\"]*) article with articleId ([^\"]*)$")
    public void createArticleModel(ArticleType articleType, String articleId){

        this.articleId = articleId;
        this.articleType = articleType;

        testArticle = new ArticleModel();
        testArticle.setId(articleId);

        switch (articleType)
        {
            case STANDARD:
                testArticle.setHeadline("PNT-StandardArticle - " + DateTime.now());
                testArticle.setStandFirst("PushNofiticationText standfirst");
                testArticle.setBylineOverride("BylineOverride");
                testArticle.setBylineDetails("BylineDetails");
                testArticle.setBody("<p>\nPushNofiticationText article body\n</p>\n");
                break;
            case CUSTOM_HTML:
                testArticle.setName("PNT-CustomHTML - " + DateTime.now());
                testArticle.setArticleHtml("<p>\nPushNofiticationText article body\n</p>\n");
                break;
            case LIVE_MATCH:
                testArticle.setLiveMatchDescription("PNT-LiveMatch - " + DateTime.now());
                break;
            case CUSTOM_LINK:
                testArticle.setName("PNT-CustomLink - " + DateTime.now());
                break;
            case COLUMNIST:
                testArticle.setColumnistHeadLine("PNT-ColumnistHeadline - " + DateTime.now());
                break;
        }

    }

    @When("^I update the article Push notification text to \"([^\"]*)\" and Send push notification to Times Sports app \"([^\"]*)\"$")
    public void updatePushNotificationFields(String pushNotificationText, String sendPushNotification) throws Throwable {
        testArticle.setPushNotificationText(pushNotificationText);
        testArticle.setPushNotificationCheckBox(sendPushNotification);

        cmsHelper.updateArticle(articleType, testArticle);

        //give chance for Feed to update before validation steps
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("^I request for the Article$")
    public void requestForArticle() throws Throwable {
        String baseURL = environmentProvider.getBaseTimesSportArticleURL();
        GenericUrl url = new GenericUrl(baseURL + articleId);
        response = builder.doHttpGetRequest(url);
    }

    @Then("^the pushNotificationText fields populated with \"([^\"]*)\" and ifSendPushNotification populated with \"([^\"]*)\"$")
    public void verifyPushNotificationFields(String pushNotificationText, String sendPushNotification) throws Throwable {
        XmlReaderHelper xmlReaderHelper = new XmlReaderHelper(response.getBody());
        Assert.assertEquals(pushNotificationText, xmlReaderHelper.getValueAtPath("//feed/pushNotificationText"));
        Assert.assertEquals(sendPushNotification, xmlReaderHelper.getValueAtPath("//feed/ifSendPushNotification"));
    }

    @Then("^the pushNotificationText field should be empty and ifSendPushNotification populated with \"([^\"]*)\"$")
    public void verifyPushNotificationFields(String sendPushNotification) throws Throwable {
        XmlReaderHelper xmlReaderHelper = new XmlReaderHelper(response.getBody());
        Assert.assertEquals("", xmlReaderHelper.getValueAtPath("//feed/pushNotificationText"));
        Assert.assertEquals(sendPushNotification, xmlReaderHelper.getValueAtPath("//feed/ifSendPushNotification"));
    }
}

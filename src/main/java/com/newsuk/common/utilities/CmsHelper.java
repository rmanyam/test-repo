package com.newsuk.common.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.client.http.GenericUrl;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.newsuk.model.feeds.ArticleModel;
import com.newsuk.model.feeds.ArticleType;
import com.newsuk.model.feeds.ImageModel;

public class CmsHelper {
	
	private static final String SOURCE_ID_TAG = "/io/article/@sourceid";
	private static final String STATE_TAG = "/io/article/@state";
	private static final String HEADLINE_TAG = "/io/article/field[@name='HEADLINE']";
	private static final String STANDFIRST_TAG = "/io/article/field[@name='STANDFIRST']";
	private static final String BYLINEOVERRIDE_TAG = "/io/article/field[@name='BYLINEOVERRIDE']";
    private static final String BYLINEDETAILS_TAG = "/io/article/field[@name='BYLINEDETAILS']";
    private static final String METHODEPUUID_TAG = "/io/article/field[@name='METHODEPUUID']";
    private static final String BODY_TAG = "/io/article/field[@name='BODY']";
    private static final String SECTION_NAME = "/io/article/section/@name";
    private static final String DB_ID = "/io/article/@dbid";
    private static final String STANDARD_PUSH_NOTIFICATION_TEXT = "/io/article/field[@name='timessportpushnotificationtext']";
    private static final String STANDARD_SEND_PUSH_NOTIFICATION = "/io/article/field[@name='timessportpushnotificationcb']";
    private static final String NAME_TAG = "/io/article/field[@name='name']";
    private static final String HTML_TAG = "/io/article/field[@name='html']";
    private static final String CUSTOM_HTML_PUSH_NOTIFICATION_TEXT = "/io/article/field[@name='customhtmlPushNotificationText']";
    private static final String CUSTOM_HTML_SEND_PUSH_NOTIFICATION = "/io/article/field[@name='customhtmlPushNotificationCb']";
    private static final String LIVE_MATCH_DESCRIPTION = "/io/article/field[@name='liveMatchDescription']";
    private static final String LIVE_MATCH_PUSH_NOTIFICATION_TEXT = "/io/article/field[@name='liveMatchPushnotificationText']";
    private static final String LIVE_MATCH_SEND_PUSH_NOTIFICATION = "/io/article/field[@name='liveMatchPushnotificationCb']";
    private static final String CUSTOM_LINK_PUSH_NOTIFICATION_TEXT = "/io/article/field[@name='customlinkpushnotificationtext']";
    private static final String CUSTOM_LINK_SEND_PUSH_NOTIFICATION = "/io/article/field[@name='customlinkpushnotificationcb']";
    private static final String COLUMNIST_HEADLINE = "/io/article/field[@name='columnistHeadline']";
    private static final String COLUMNIST_PUSH_NOTIFICATION_TEXT = "/io/article/field[@name='columnistPushNotificationText']";
    private static final String COLUMNIST_SEND_PUSH_NOTIFICATION = "/io/article/field[@name='columnistPushNotificationCb']";

	private static final String IMG_DBID_TAG = "/io/multimediaGroup/@dbid";
	private static final String IMG_ID_DESCRIPTION = "/io/multimediaGroup/description";
	
	private static final String BRICK = "%%brick%%";
	private static final String CLEAR_BRICK = "%%clearBrick%%";
	private static final String SECTION_ID = "%%sectionId%%";
		
	private HttpRequestBuilder httpRequester;
	private EnvironmentProvider envProvider;
	private String cmsUsername;
	private String cmsPassword;
	private String cmsImportEndPoint;
	private String cmsMoveToEndPoint;
	private XMLHelper xmlHelper;
	
	public CmsHelper(){
		httpRequester = new HttpRequestBuilder();
		httpRequester.initializeRequestBuilder();
		envProvider = new EnvironmentProvider();
		xmlHelper = new XMLHelper();
		cmsUsername = envProvider.getCmsUsername();
		cmsPassword = envProvider.getCmsPassword();
		cmsImportEndPoint = envProvider.getCmsWebServiceUrl();
		cmsMoveToEndPoint = envProvider.getCmsWebServiceMoveToUrl();
	}
	
	public String createArticleAndMoveToBrick(ArticleModel article) {
		String articleId = createArticle(article);
		moveArticleToBrick(articleId, "13842", "sectionContentGridHero", false);
		return articleId;
	}

	public String createArticle(ArticleModel article) {
		if(article.getSourceId() == null)
			article.setSourceId(String.valueOf(new Date().getTime()));
		
		Map<String, String> editMap = new HashMap<String, String>();
		editMap.put(SOURCE_ID_TAG, article.getSourceId());
		editMap.put(STATE_TAG, article.getState());
		editMap.put(HEADLINE_TAG, article.getHeadline());
		editMap.put(STANDFIRST_TAG, article.getStandFirst());
		editMap.put(BYLINEOVERRIDE_TAG, article.getBylineOverride());
		editMap.put(BYLINEDETAILS_TAG, article.getBylineDetails());
		editMap.put(METHODEPUUID_TAG, article.getUuid());
		editMap.put(BODY_TAG, article.getBody());
		editMap.put(SECTION_NAME, article.getParentName());
		
		String postBody=null;
		try {
			postBody = xmlHelper.editXMLNode("data"+ File.separator +"cmsWebServiceTemplates"+ File.separator +"ArticleTemplate.xml", editMap);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Edited xml!!: " + postBody);
		
		String xmlStringResponse = postMessage(cmsImportEndPoint, postBody);
			
		//retrieve article id and set bean 
		XmlReaderHelper xmlReader = new XmlReaderHelper(xmlStringResponse);
		String articleUrl = xmlReader.getValueAtPath("escenicObjectTOes/escenicObjectTO/url");
		
		System.out.println("article url is: " + articleUrl);
		String[] articleArray = articleUrl.split("/");
		
		String articleString = articleArray[articleArray.length-1];
		System.out.println("end bit: " + articleString);
		
		Pattern p = Pattern.compile("article(\\d+).ece");
		Matcher m = p.matcher(articleString);
		
		m.find();
		String articleId = m.group(1);
		
		return articleId;
	}
	
	
	public String updateArticle(ArticleModel article) {
		String sourceId = String.valueOf(new Date().getTime());
		
		Map<String, String> editMap = new HashMap<String, String>();
		editMap.put(SOURCE_ID_TAG, sourceId);
		editMap.put(STATE_TAG, article.getState());
		editMap.put(HEADLINE_TAG, article.getHeadline());
		editMap.put(STANDFIRST_TAG, article.getStandFirst());
		editMap.put(BYLINEOVERRIDE_TAG, article.getBylineOverride());
		editMap.put(BYLINEDETAILS_TAG, article.getBylineDetails());
		editMap.put(METHODEPUUID_TAG, article.getUuid());
		editMap.put(BODY_TAG, article.getBody());
		editMap.put(SECTION_NAME, article.getParentName());
		editMap.put(DB_ID, article.getId());
		
		String postBody=null;
		try {
			postBody = xmlHelper.editXMLNode("data"+ File.separator +"cmsWebServiceTemplates" + File.separator + "ArticleUpdate.xml", editMap);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Edited xml!!: " + postBody);
		
		String xmlStringResponse = postMessage(cmsImportEndPoint, postBody);
				
		//retrieve article id and set bean 
		XmlReaderHelper xmlReader = new XmlReaderHelper(xmlStringResponse);
		String articleUrl = xmlReader.getValueAtPath("escenicObjectTOes/escenicObjectTO/url");
		
		System.out.println("article url is: " + articleUrl);
		String[] articleArray = articleUrl.split("/");
		
		String articleString = articleArray[articleArray.length-1];
		System.out.println("end bit: " + articleString);
		
		Pattern p = Pattern.compile("article(\\d+).ece");
		Matcher m = p.matcher(articleString);
		
		m.find();
		String articleId = m.group(1);
		
		return articleId;
	}

    /**
     * Method to update an Article
     * @param articleType
     * @param article
     * @return
     */
    public String updateArticle(ArticleType articleType, ArticleModel article)
    {

        String xmlStringResponse = postMessage(cmsImportEndPoint, getPostBody(articleType, article));

        //retrieve article id and set bean
        XmlReaderHelper xmlReader = new XmlReaderHelper(xmlStringResponse);
        String articleUrl = xmlReader.getValueAtPath("escenicObjectTOes/escenicObjectTO/url");

        System.out.println("article url is: " + articleUrl);
        String[] articleArray = articleUrl.split("/");

        String articleString = articleArray[articleArray.length-1];
        System.out.println("end bit: " + articleString);

        Pattern p = Pattern.compile("article(\\d+).ece");
        Matcher m = p.matcher(articleString);

        m.find();
        String articleId = m.group(1);

        return articleId;
    }

    /**
     * Method to get the poster body depending on Article type with given Article Model
     * @param articleType
     * @param article
     * @return
     */
    public String getPostBody(ArticleType articleType, ArticleModel article)
    {
        Map<String, String> editMap = new HashMap<String, String>();
        editMap.put(DB_ID, article.getId());
        String fileName = null;

        switch (articleType)
        {
            case STANDARD:
                fileName = "ArticleWithPushNotificationTextUpdate.xml";
                editMap.put(HEADLINE_TAG, article.getHeadline());
                editMap.put(STANDFIRST_TAG, article.getStandFirst());
                editMap.put(BYLINEOVERRIDE_TAG, article.getBylineOverride());
                editMap.put(BYLINEDETAILS_TAG, article.getBylineDetails());
                editMap.put(BODY_TAG, article.getBody());
                editMap.put(STANDARD_PUSH_NOTIFICATION_TEXT, article.getPushNotificationText());
                editMap.put(STANDARD_SEND_PUSH_NOTIFICATION, article.getPushNotificationCheckBox());
                break;
            case CUSTOM_HTML:
                fileName = "CustomHTMLUpdate.xml";
                editMap.put(NAME_TAG, article.getName());
                editMap.put(HTML_TAG, article.getArticleHtml());
                editMap.put(CUSTOM_HTML_PUSH_NOTIFICATION_TEXT, article.getPushNotificationText());
                editMap.put(CUSTOM_HTML_SEND_PUSH_NOTIFICATION, article.getPushNotificationCheckBox());
                break;
            case LIVE_MATCH:
                fileName = "LiveMatchUpdate.xml";
                editMap.put(LIVE_MATCH_DESCRIPTION, article.getLiveMatchDescription());
                editMap.put(LIVE_MATCH_PUSH_NOTIFICATION_TEXT, article.getPushNotificationText());
                editMap.put(LIVE_MATCH_SEND_PUSH_NOTIFICATION, article.getPushNotificationCheckBox());
                break;
            case CUSTOM_LINK:
                fileName = "CustomLinkUpdate.xml";
                editMap.put(NAME_TAG, article.getName());
                editMap.put(CUSTOM_LINK_PUSH_NOTIFICATION_TEXT, article.getPushNotificationText());
                editMap.put(CUSTOM_LINK_SEND_PUSH_NOTIFICATION, article.getPushNotificationCheckBox());
                break;
            case COLUMNIST:
                fileName = "ColumnistUpdate.xml";
                editMap.put(COLUMNIST_HEADLINE, article.getColumnistHeadLine());
                editMap.put(COLUMNIST_PUSH_NOTIFICATION_TEXT, article.getPushNotificationText());
                editMap.put(COLUMNIST_SEND_PUSH_NOTIFICATION, article.getPushNotificationCheckBox());
                break;
        }


        String postBody=null;
        try {
            postBody = xmlHelper.editXMLNode("data"+ File.separator +"cmsWebServiceTemplates" + File.separator + fileName, editMap);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Edited xml!!: " + postBody);

        return postBody;
    }

	public void updateImage(ImageModel image){	
		Map<String, String> editMap = new HashMap<String, String>();
		editMap.put(IMG_DBID_TAG, image.getId());
		editMap.put(IMG_ID_DESCRIPTION, image.getDescription());

		String postBody=null;
		try {
			postBody = xmlHelper.editXMLNode("data" + File.separator + "cmsWebServiceTemplates" + File.separator + "ImageUpdate.xml", editMap);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Edited xml!!: " + postBody);
		
		postMessage(cmsImportEndPoint, postBody);
		
	}
	
	public void moveArticleToBrick(String articleId, String sectionId, String brickName, boolean clearBrick){
		//post xml file to webservice to move article to the correct grid/element-group/brick
		cmsMoveToEndPoint = envProvider.getCmsWebServiceMoveToUrl();
		cmsMoveToEndPoint = cmsMoveToEndPoint.replaceAll(SECTION_ID, sectionId);
		cmsMoveToEndPoint = cmsMoveToEndPoint.replaceAll(BRICK, brickName);
		cmsMoveToEndPoint = cmsMoveToEndPoint.replaceAll(CLEAR_BRICK, String.valueOf(clearBrick));

		
		String xpathToArticleId = "/io/article/@dbid";

		String moveArticleXmlString = null;
		 try {
			 moveArticleXmlString = xmlHelper.editXMLNode( xpathToArticleId, "data"+ File.separator +"cmsWebServiceTemplates"+ File.separator +"ArticleMoveToTemplate.xml", articleId);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//post xml file to web service to create article
		 System.out.println("move to url: " + cmsMoveToEndPoint);
		 postMessage(cmsMoveToEndPoint, moveArticleXmlString);
		 
		 try {
			Thread.sleep(3000); //allow for Feed API to update
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String createImageInCms(String name, String description, String source, String fileName, String filePath){
		String result=null;
		String sourceId = Long.toString(System.currentTimeMillis());
		String xpathToSourceId = "/io/multimediaGroup/@sourceid";
		String xpathToName = "/io/multimediaGroup/@name";
		String xpathToDescription = "/io/multimediaGroup/description";
		String xpathToFileName = "/io/multimediaGroup/multimedia/@filename";
		String xpathToImageId = "/escenicObjectTOes/escenicObjectTO/@id";
		String xpathToSource = "/io/multimediaGroup/@source";
			
		//edit xml
		Map<String, String> editMap = new HashMap<String, String>();
		editMap.put(xpathToName, name);
		editMap.put(xpathToDescription, description);
		editMap.put(xpathToFileName, fileName);
		editMap.put(xpathToSourceId, sourceId);
		editMap.put(xpathToSource, source);

		String postBody=null;
		try {
			postBody = xmlHelper.editXMLNode("data" + File.separator + "cmsWebServiceTemplates" + File.separator + "image.xml", editMap);
			System.out.println(postBody);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 Files.write( postBody, new File( "image1.xml"), Charsets.UTF_8 );
			} catch( IOException e ) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		}
		
		//create zip file		
		String[] files = {"image1.xml", filePath};		
		ZipHelper.createZipFromFiles(files, "image1.zip"); //creates temporary zip file called image1.zip which will be uploaded
				
		//post image creation request to CMS API		
				HttpRequestBuilder httpBuilder = new HttpRequestBuilder();
				httpBuilder.initializeRequestBuilder();
				GenericUrl url = new GenericUrl(cmsImportEndPoint);
				try {
					httpBuilder.setAuthentication(cmsUsername, cmsPassword);
					httpBuilder.setContentType("application/zip");
					httpBuilder.getHttpPostRequest("image1.zip", url);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(httpBuilder.getHttpResponse().getStatusMessage());
				System.out.println(httpBuilder.getHttpResponse().getStatusCode());

				try {
					result = httpBuilder.getHttpResponse().parseAsString();
					System.out.println(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		XmlReaderHelper xmlReader = new XmlReaderHelper(result);
		return xmlReader.getValueAtPath(xpathToImageId);
	}
	
	
	private String postMessage(String endpoint, String postBody){
		//set authentication
		httpRequester.setAuthentication(cmsUsername, cmsPassword);
		httpRequester.setContentType("application/xml");
		String xmlStringResponse = null;
		
		//post xml file to web service to create article
		GenericUrl genericUrl = new GenericUrl(endpoint);
		System.out.println("About to use the URL: " + genericUrl);
		try {
			xmlStringResponse = httpRequester.doHttpPostRequest(postBody, genericUrl);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(xmlStringResponse);
		
		return xmlStringResponse;
	}
	
	public int getExistingAuthorId(){
		return -1;
	}
	

}
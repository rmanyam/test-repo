package com.newsuk.model.feeds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newsuk.common.utilities.EnvironmentProvider;

public class ArticleModel extends BaseCmsModel {

	private String parentName;
	private String parentId;
	
	private String headline;
	private String authorFirstName;
	private String authorLastName;
	private String standFirst;
	private String body;
	private String sectionNameOverride;
	private String articleTitle;
	private String articleCoverImageId;
	private String articleMatchDescription;
	private String articleMatchId;
	private String articleMatchCopy;
	private String articleHtml;
	private String articleLabel;
	private String bylineOverride;
	private String title;
	private String titleprefix;
	private String headlineTitleOverride;
	private String articleHeadlineOverride;
	private String articleStandFirstOverride;
	private String articleBodyOverride;
	private String state;
	private String sectionId;
	private String bylineDetails;
	private String authorId;	
	private String webViewTitle;
	private String name;
	private String URL;
	private String sourceId;
	private String uuid;
	private String sharingUrl;
	private String teaserText;
	private String sectionIdentifierName;
	private String sectionPageHeadline;
	private ImageModel coverImage;
	private String classification;
	private String infographic;
    private String liveMatchDescription;
    private String pushNotificationText;
    private String pushNotificationCheckBox;
    private String columnistHeadLine;


    private List<ImageModel> imageList;
	private List<VideoModel> videoList;
	private List<BaseCmsModel> articleList;
	
	public ArticleModel(){
		imageList = new ArrayList<ImageModel>();
		videoList = new ArrayList<VideoModel>();
		articleList = new ArrayList<BaseCmsModel>();	
	}
	
	public void setDefaultValues(String baseFeedDomain){
		setTitle("automation 3 article title");
		setState("published");
		setParentName("automation3published");
		setAuthorFirstName("tto");
		setAuthorLastName("Administrator");
			
		setStandFirst("automation 3 article standfirst");
		setSectionNameOverride("automation 3 article section name override");
		setTitleprefix("automation 3 article times sport title prefix");
		setHeadlineTitleOverride("automation 3 article headline changed");
		setArticleStandFirstOverride("automation 3 article  standfirst changed");
		setArticleBodyOverride("automation 3 article body - changed ");
		
		setSourceId(String.valueOf(new Date().getTime()));
		
		if(uuid == null)
			setUuid(String.valueOf(new Date().getTime()));
		setSharingUrl(new EnvironmentProvider().getBaseUrl() + "public/share/uuid/" + getUuid());
		
		setFeedLink(baseFeedDomain + "article/" + 3236549);
		setArticleBodyOverride("<p>\nautomation 3 article body - changed \n</p>\n");
		setBody("<p>\nautomation 3 article body\n</p>\n");
		//setId("3236549");
		setCategory("article");
		setAuthorFirstName("tto");
		setAuthorLastName("Administrator");
		setAuthorId("2");
		setFeedLink(baseFeedDomain + "article/" + getId());
		setHeadline("automation 3 article headline");
		setAuthorUri(baseFeedDomain + "author/" + getAuthorId());
		setByline("automation 3 byline");
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getBylineDetails() {
		return bylineDetails;
	}
	public void setBylineDetails(String bylineDetails) {
		this.bylineDetails = bylineDetails;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBylineOverride() {
		return bylineOverride;
	}
	public void setBylineOverride(String bylineOverride) {
		this.bylineOverride = bylineOverride;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	public String getStandFirst() {
		return standFirst;
	}
	public void setStandFirst(String standFirst) {
		this.standFirst = standFirst;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSectionNameOverride() {
		return sectionNameOverride;
	}
	public void setSectionNameOverride(String sectionNameOverride) {
		this.sectionNameOverride = sectionNameOverride;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleCoverImageId() {
		return articleCoverImageId;
	}
	public void setArticleCoverImageId(String articleCoverImageId) {
		this.articleCoverImageId = articleCoverImageId;
	}
	public String getArticleMatchDescription() {
		return articleMatchDescription;
	}
	public void setArticleMatchDescription(String articleMatchDescription) {
		this.articleMatchDescription = articleMatchDescription;
	}
	public String getArticleMatchId() {
		return articleMatchId;
	}
	public void setArticleMatchId(String articleMatchId) {
		this.articleMatchId = articleMatchId;
	}
	public String getArticleMatchCopy() {
		return articleMatchCopy;
	}
	public void setArticleMatchCopy(String articleMatchCopy) {
		this.articleMatchCopy = articleMatchCopy;
	}
	public String getArticleHtml() {
		return articleHtml;
	}
	public void setArticleHtml(String articleHtml) {
		this.articleHtml = articleHtml;
	}
	public String getArticleLabel() {
		return articleLabel;
	}
	public void setArticleLabel(String articleLabel) {
		this.articleLabel = articleLabel;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getTitleprefix() {
		return titleprefix;
	}
	public void setTitleprefix(String titleprefix) {
		this.titleprefix = titleprefix;
	}
	public String getHeadlineTitleOverride() {
		return headlineTitleOverride;
	}
	public void setHeadlineTitleOverride(String headlineTitleOverride) {
		this.headlineTitleOverride = headlineTitleOverride;
	}
	public String getArticleHeadlineOverride() {
		return articleHeadlineOverride;
	}
	public void setArticleHeadlineOverride(String articleHeadlineOverride) {
		this.articleHeadlineOverride = articleHeadlineOverride;
	}
	public String getArticleStandFirstOverride() {
		return articleStandFirstOverride;
	}
	public void setArticleStandFirstOverride(String articleStandFirstOverride) {
		this.articleStandFirstOverride = articleStandFirstOverride;
	}
	public String getArticleBodyOverride() {
		return articleBodyOverride;
	}
	public void setArticleBodyOverride(String articleBodyOverride) {
		this.articleBodyOverride = articleBodyOverride;
	}
	
	public String getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	public ImageModel getCoverImage() {
		return coverImage;
	}
	
	public void setCoverImage(ImageModel coverImage) {
		this.coverImage = coverImage;
	}
	
	public void addVideo(VideoModel video){
		videoList.add(video);
	}

	public List<ImageModel> getImageList() {
		return imageList;
	}

	public void setImageList(List<ImageModel> imageList) {
		this.imageList = imageList;
	}

	public void addRelatedArticle(BaseCmsModel article){
		articleList.add(article);
	}
	
	public void addImage(ImageModel image){
		imageList.add(image);
	}

	public String getWebViewTitle() {
		return webViewTitle;
	}

	public void setWebViewTitle(String webViewTitle) {
		this.webViewTitle = webViewTitle;
	}
	
	public String getSectionId() {
		return sectionId;
	}
	
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getTeaserText() {
		return teaserText;
	}

	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}

	public String getSectionIdentifierName() {
		return sectionIdentifierName;
	}

	public void setSectionIdentifierName(String sectionIdentifierName) {
		this.sectionIdentifierName = sectionIdentifierName;
	}

	public String getSectionPageHeadline() {
		return sectionPageHeadline;
	}

	public void setSectionPageHeadline(String sectionPageHeadline) {
		this.sectionPageHeadline = sectionPageHeadline;
	}
	
	public List<VideoModel> getVideoList(){
		return videoList;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getInfographic() {
		return infographic;
	}

	public void setInfographic(String infographic) {
		this.infographic = infographic;
	}
	
	public void setSourceId(String sourceId){
		this.sourceId = sourceId;
	}
	
	public String getSourceId(){
		return sourceId;
	}
	
	public void setSharingUrl(String sharingUrl){
		this.sharingUrl = sharingUrl;
	}
	
	public String getSharingUrl(){
		return sharingUrl;
	}
	
	public void setUuid(String uuid){
		this.uuid = uuid;
	}
	
	public String getUuid(){
		return uuid;
	}

    public String getPushNotificationText() {
        return pushNotificationText;
    }

    public void setPushNotificationText(String pushNotificationText) {
        this.pushNotificationText = pushNotificationText;
    }

    public String getPushNotificationCheckBox() {
        return pushNotificationCheckBox;
    }

    public void setPushNotificationCheckBox(String getTimessportPushNotificationCb) {
        this.pushNotificationCheckBox = getTimessportPushNotificationCb;
    }

    public String getLiveMatchDescription() {
        return liveMatchDescription;
    }

    public void setLiveMatchDescription(String liveMatchDescription) {
        this.liveMatchDescription = liveMatchDescription;
    }

    public String getColumnistHeadLine() {
        return columnistHeadLine;
    }

    public void setColumnistHeadLine(String columnistHeadLine) {
        this.columnistHeadLine = columnistHeadLine;
    }
}

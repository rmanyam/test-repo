package com.newsuk.model.feeds;

public class VideoModel extends BaseCmsModel {

	private String videoTitle;
	private String videoCaption;
	private String videoEmbededCode;
	private String videoPlayerId;
	
	private ImageModel thumbnail;

	public VideoModel(){
		category = "video";
	}
	
	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoCaption() {
		return videoCaption;
	}

	public void setVideoCaption(String videoCaption) {
		this.videoCaption = videoCaption;
	}

	public String getVideoEmbededCode() {
		return videoEmbededCode;
	}

	public void setVideoEmbededCode(String videoEmbededCode) {
		this.videoEmbededCode = videoEmbededCode;
	}

	public String getVideoPlayerId() {
		return videoPlayerId;
	}

	public void setVideoPlayerId(String videoPlayerId) {
		this.videoPlayerId = videoPlayerId;
	}

	public ImageModel getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(ImageModel thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
}

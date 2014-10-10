package com.newsuk.model.feeds;

public class ImageModel extends BaseCmsModel {
	private String contentType;
	private String description;
	private String name;
	private String caption;
	private String credit;
	private String fileName;
	
	public ImageModel(){
		setCategory();
	}
	
	public ImageModel(String id){
		this.id = id;
		setCategory();
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	private void setCategory(){
		category = "image";
	}
	
}

package com.newsuk.model.feeds;

public class SectionModel extends BaseCmsModel{
	
	private String sectionName;
	private String uniqueName;
	private String sectionPiority;
	private String sectionTemplate;
	private String sectionParentUiqueName;
	private String sectionUrl;
	private String sectionColor;
	private String sectionHighlightColor;
	private boolean isPublished;
	private int sectionId;
	
	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public SectionModel(){
		isPublished = true;
	}
	
	public boolean isPublished() {
		return isPublished;
	}
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	public String getSectionPiority() {
		return sectionPiority;
	}
	public void setSectionPiority(String sectionPiority) {
		this.sectionPiority = sectionPiority;
	}
	public String getSectionTemplate() {
		return sectionTemplate;
	}
	public void setSectionTemplate(String sectionTemplate) {
		this.sectionTemplate = sectionTemplate;
	}
	public String getSectionParentUiqueName() {
		return sectionParentUiqueName;
	}
	public void setSectionParentUiqueName(String sectionParentUiqueName) {
		this.sectionParentUiqueName = sectionParentUiqueName;
	}
	public String getSectionUrl() {
		return sectionUrl;
	}
	public void setSectionUrl(String sectionUrl) {
		this.sectionUrl = sectionUrl;
	}
	public String getSectionColor() {
		return sectionColor;
	}
	public void setSectionColor(String sectionColor) {
		this.sectionColor = sectionColor;
	}
	public String getSectionHighlightColor() {
		return sectionHighlightColor;
	}
	public void setSectionHighlightColor(String sectionHighlightColor) {
		this.sectionHighlightColor = sectionHighlightColor;
	}
}

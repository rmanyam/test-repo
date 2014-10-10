package com.newsuk.common.utilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ArticlePage 
{

	@FindBy(how= How.CSS, using="#title" )
	public WebElement heading_title;
	
	@FindBy(how= How.CSS, using=".title" )
	private WebElement byLine;
	
	@FindBy(how= How.XPATH, using=".//*[@id='tab-1']/div[1]/div[2]/div[1]/Strong" )
	private WebElement author;
	
	@FindBy(how= How.CSS, using=".f-standfirst" )
	private WebElement standFirst;
	
	@FindBy(how= How.CSS, using=".f-regular-update" )
	private WebElement lastUpdated;
	
	
	
	public WebElement getHeadingTitle() {
		return heading_title;
	}
	public WebElement getByLine() {
		return byLine;
	}
	
	public WebElement getAuthor() {
		return author;
	}
	
	public WebElement getStandFirst() {
		return	standFirst;
	}
	
	public WebElement getLastUpdated() {
		return	lastUpdated;
	}
	
	
	
	
}

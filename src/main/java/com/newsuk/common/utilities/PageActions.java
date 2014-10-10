package com.newsuk.common.utilities;

 

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageActions
{
	public ArticlePage article;
	String URL;
	DriverProvider dp = new DriverProvider("10.198.12.37");
	WebDriver driver; 

	public String getAttributes(WebElement element,String attribute)
	{
		return element.getAttribute(attribute);
	}
	
	public void openArticlePage(String URL)
	{
		driver.navigate().to(URL);
		
	}
	
	public void setBrowser(String browser)
	{
		driver = dp.getDriver();
	}
	
	public void checkArticlePage()
	 {
		 article = PageFactory.initElements(driver,ArticlePage.class);
  	
	 }
	 
	public void closeDriver()
	{
		driver.close();
	}
	
	public void highlightElement(WebElement element) {

	   
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 5; i++) {

	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "border: 2px solid yellow;");
	    }
		
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");


	}

}

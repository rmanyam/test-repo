package com.newsuk.model.web.header;

import com.newsuk.model.web.AbstractWebPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by ranjithmanyam on 04/09/2014.
 */
public class PageHeader extends AbstractWebPage {

    public HeaderElements controls;

    public PageHeader(WebDriver driver) {
        super(driver, new HeaderElements());
        this.controls = this.elements instanceof HeaderElements
                ? (HeaderElements)this.elements : null;
    }

    public boolean isLogoDisplayed(){
        try{
            return controls.logo.getAttribute("alt").equals("THE TIMES");
        } catch (NoSuchElementException nse){
            return false;
        }
    }

    public boolean isHeaderDisplayed(String headerName){
        try{
            return controls.header.getAttribute("alt").equals(headerName);
        } catch (NoSuchElementException nse){
            return false;
        }
    }

    public static class HeaderElements extends Elements{

        @FindBy(css = "div.section-header>a>img")
        public WebElement logo;

        @FindBy(css = "div.section-title>a>img")
        public WebElement header;



        public HeaderElements(){
            super();
        }
    }
}

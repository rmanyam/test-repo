package com.newsuk.model.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;


/**
 * author: Ranjith Manyam
 */
public class HomePage extends AbstractWebPage {

    static Logger log = LogManager.getLogger(HomePage.class.getName());
    public HomePageElements controls;

    public HomePage(WebDriver driver) {
        super(driver, new HomePageElements());

        this.controls = this.elements instanceof HomePageElements
                ? (HomePageElements)this.elements : null;
    }

    public HomePage loadHomePage(URL baseUrl){
        log.info("Navigating to [" + baseUrl + "]");
        driver.navigate().to(baseUrl);
        return new HomePage(driver);
    }



    public static class HomePageElements extends Elements {

        public HomePageElements(){
            super();
        }

        @FindBy(css = "#homepage-logo>img")
        private WebElement logo;
    }
}

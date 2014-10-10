package com.newsuk.model.web.navigation.views;

import com.newsuk.model.web.AbstractWebPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ranjithmanyam on 05/09/2014.
 */
public class RSSFeedsViewPage extends AbstractWebPage{

    static Logger log = LogManager.getLogger(RSSFeedsViewPage.class.getName());
    public RSSFeedsViewPageElements controls;

    public RSSFeedsViewPage(WebDriver driver){
        super(driver, new RSSFeedsViewPageElements());
        this.controls = this.elements instanceof RSSFeedsViewPageElements
                ? (RSSFeedsViewPageElements)this.elements : null;
    }

    public String getHeading(){
        return controls.heading.getText();
    }

    public boolean isFeedsDisplayedCorrectly(){

        boolean isDisplayed = false;

        for(WebElement feed: controls.feedList){

            String feedTitle = feed.findElement(By.cssSelector("h3.f-hb>a")).getText();
            String feedContent = feed.findElement(By.cssSelector("p")).getText();
            String updatedOn = feed.findElement(By.cssSelector("span")).getText();

            log.debug("Feed Title: {}", feedTitle);
            log.debug("Feed Content: {}", feedContent);
            log.debug("Feed Updated On: {}", updatedOn);

            isDisplayed = !StringUtils.isEmpty(feedTitle)
                          && !StringUtils.isEmpty(feedContent)
                          && isUpdateDateDisplayedCorrectly(updatedOn);

        }
        return isDisplayed;
    }

    private boolean isUpdateDateDisplayedCorrectly(String updatedDate){
        SimpleDateFormat format = new SimpleDateFormat("d MMMM yyyy hh:mm a");

        boolean isDate;

        try {
            log.debug("Updated Date: {}", updatedDate);
            format.parse(updatedDate);
            isDate = true;
        } catch (ParseException e) {
            e.printStackTrace();
            isDate = false;
        }
        return isDate;
    }

    public static class RSSFeedsViewPageElements extends Elements{

        public RSSFeedsViewPageElements(){
            super();
        }

        @FindBy (css = "div.heading h1")
        private WebElement heading;

        @FindBy (css = "div.rss-list>ul>li")
        private List<WebElement> feedList;


    }
}

package com.newsuk.model.web.navigation.views;

import com.newsuk.model.web.AbstractWebPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by ranjithmanyam on 05/09/2014.
 */
public class ListViewPage extends AbstractWebPage{

    static Logger log = LogManager.getLogger(ListViewPage.class.getName());
    public ListViewPageElements controls;

    public ListViewPage(WebDriver driver){
        super(driver, new ListViewPageElements());
        this.controls = this.elements instanceof ListViewPageElements
                ? (ListViewPageElements)this.elements : null;
    }

    /**
     * Method to verify if the search box is displayed
     * @return True if displayed
     */
    public boolean isSearchBoxDisplayed(){
        try{
            return controls.searchBox.isDisplayed();
        } catch (NoSuchElementException nse){
            return false;
        }
    }

    /**
     * Method to verify if the Ad is displayed
     * @return True if displayed
     */
    public boolean isAdDisplayed(){
        try{
            return controls.ad.isDisplayed();
        } catch (NoSuchElementException nse){
            return false;
        }
    }

    /**
     * Method to check all the articles are displayed correctly on List View page i.e,
     * 1) Check for Article number
     * 2) Article Update date (Checks the date format)
     * 3) Article Title
     * @return <code>true</code> if all of the above steps are true
     *         <code>false</code> otherwise
     */
    public boolean isArticlesDisplayedCorrectly(){
        List<WebElement> listOfArticleRows = getListOfArticleRows();

        if(listOfArticleRows.size() <= 0){
            log.debug("No Articles found");
            return false;
        }
        else {
            return (isArticleNumberDisplayedCorrectly(listOfArticleRows) && isArticleUpdateDateDisplayedCorrectly(listOfArticleRows) && isArticleTitleDisplayedCorrectly(listOfArticleRows));
        }
    }

    /**
     * Method to verify that article numbers are displayed for each article in the list
     * @param listOfArticleRows
     * @return <code>true</code> if a number is displayed for each article in the row
     *         <code>false</code> otherwise
     */
    private boolean isArticleNumberDisplayedCorrectly(List<WebElement> listOfArticleRows){
        boolean isDisplayed = false;
        for(WebElement row : listOfArticleRows){
            isDisplayed = NumberUtils.isNumber(row.findElement(By.cssSelector("th>span>img")).getAttribute("alt"));
        }
        log.debug("Verifying Article number displayed correctly");
        return isDisplayed;
    }

    private boolean isArticleUpdateDateDisplayedCorrectly(List<WebElement> listOfArticleRows){
        SimpleDateFormat format = new SimpleDateFormat("d MMMM yyyy hh:mm a");

        boolean isDate = false;

        for(WebElement row: listOfArticleRows){
            try {
                String dateDisplayed = row.findElement(By.cssSelector("td.time-stamp>div.f-regular-update")).getText();
                log.debug("Updated Date: {}", dateDisplayed);
                format.parse(dateDisplayed);
                isDate = true;
            } catch (ParseException e) {
                e.printStackTrace();
                isDate = false;
            }
        }
        return isDate;
    }

    private boolean isArticleTitleDisplayedCorrectly(List<WebElement> listOfArticleRows){
        boolean isDisplayed = false;
        for(WebElement row : listOfArticleRows){
            if(!StringUtils.isEmpty(row.findElement(By.cssSelector("td.title>a")).getText())){
                log.debug("Article Title: {}", row.findElement(By.cssSelector("td.title>a")).getText());
                isDisplayed = true;
            }
            else{
                isDisplayed = false;
                break;
            }
        }
        log.debug("Verifying Article Title is displayed correctly");
        return isDisplayed;
    }

    private List<WebElement> getListOfArticleRows(){
        try{
            return controls.articlesTable.findElements(By.cssSelector("tr[class^='article-']"));
        } catch (NoSuchElementException nse){
            log.error("Unable to find article rows" + nse);
            // Should we throw PageOperationException??
            return Collections.emptyList();
        }
    }

    public static class ListViewPageElements extends Elements{

        public ListViewPageElements(){
            super();
        }

        @FindBy(css = "#listviewSearch")
        public WebElement searchBox;

        @FindBy(css = "#latestArticles")
        public WebElement articlesTable;

        @FindBy(css = "div[class^='flashAd']>div[class^='flashAd']")
        public WebElement ad;
    }
}

package com.newsuk.model.web.navigation.views;

import com.newsuk.common.utilities.CommonUtils;
import com.newsuk.model.web.AbstractWebPage;
import com.newsuk.model.web.HTMLPage;
import com.newsuk.model.web.SectionPage;
import com.newsuk.model.web.sections.*;
import com.newsuk.shuriken.core.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ranjithmanyam on 05/09/2014.
 */
public class SiteMapViewPage extends AbstractWebPage{

    private static Logger log = LogManager.getLogger(SiteMapViewPage.class.getName());
    public SiteMapViewPageElements controls;


    public SiteMapViewPage(WebDriver driver){
        super(driver, new SiteMapViewPageElements());
        this.controls = this.elements instanceof SiteMapViewPageElements
                ? (SiteMapViewPageElements)this.elements : null;
    }

    public boolean isAllSectionsDisplayed(){
        List<MenuItem> actualSectionList = getSectionList();

        return actualSectionList.containsAll(Arrays.asList(MenuItem.values()));
    }

    public boolean isSearchBoxDisplayed(){
        try{
            return controls.searchBox.isDisplayed();
        }catch (NoSuchElementException nse){
            log.debug("Unable to find Search box in Site Map View page");
            return false;
        }
    }

    public boolean isSectionSubTitlesDisplayed(){

        boolean isDisplayed = false;

        for(WebElement section: controls.sectionList)
        {
            MenuItem item = MenuItem.getMenuItemFromString(section.findElement(By.cssSelector("h2.section-title>a>img")).getAttribute("alt"));

            log.debug("Menu Item: {}", item.getMenuItem());
            switch (item){
                case NEWS: isDisplayed = CommonUtils.compareLists(NewsSubTitle.getValues(), getSubTitleList(section));
                    break;
                case OPINION: isDisplayed = CommonUtils.compareLists(OpinionSubTitle.getValues(), getSubTitleList(section));
                    break;
                case BUSINESS: isDisplayed = CommonUtils.compareLists(BusinessSubTitle.getValues(), getSubTitleList(section));
                    break;
                case LIFE: isDisplayed = CommonUtils.compareLists(LifeSubTitle.getValues(), getSubTitleList(section));
                    break;
                case MONEY: isDisplayed = CommonUtils.compareLists(MoneySubTitle.getValues(), getSubTitleList(section));
                    break;
                case SPORT: isDisplayed = CommonUtils.compareLists(SportSubTitle.getValues(), getSubTitleList(section));
                    break;
                case ARTS: isDisplayed = CommonUtils.compareLists(ArtsSubTitle.getValues(), getSubTitleList(section));
                    break;
                case PUZZLES: isDisplayed = CommonUtils.compareLists(PuzzlesSubTitle.getValues(), getSubTitleList(section));
                    break;
                case PAPERS: isDisplayed = CommonUtils.compareLists(PapersSubTitle.getValues(), getSubTitleList(section));
                    break;
            }
        }
        return isDisplayed;
    }

    public SectionPage clickOnSection(String sectionName){

        for(WebElement section: controls.sectionTitleList){
            if(section.getAttribute("alt").equals(sectionName)){
                section.click();
                break;
            }
        }
        return new SectionPage(driver);
    }

    private List<MenuItem> getSectionList(){
        return controls.sectionList.stream().map(section -> MenuItem.getMenuItemFromString(section.findElement(By.cssSelector("h2.section-title>a>img")).getAttribute("alt"))).collect(Collectors.toList());
    }

    private List<String> getSubTitleList(WebElement section){
        return CommonUtils.getStringList(section.findElements(By.cssSelector("h3.sub-title")));
    }

    public SectionPage clickOnSubTitle(String subTitle) {

        for(WebElement section: controls.subTitleList){
            if(section.getText().equals(subTitle)){
                section.click();
                break;
            }
        }
        return new SectionPage(driver);

    }

    public SectionPage clickOnSubSection(String subSection){

        for(WebElement section: controls.subSectionList){
            if(section.getText().equals(subSection)){
                section.click();
                break;
            }
        }
        return new SectionPage(driver);

    }

    public boolean isSearchResultsDisplayedCorrectly(String searchString){

        boolean isDisplayed = false;

        for(WebElement searchResultElement: controls.searchResultsList){
            isDisplayed = searchResultElement.getText().toLowerCase().contains(searchString.toLowerCase());
            log.debug("Search String: {}, Search Result: {}, isDisplayed: {}", searchString, searchResultElement.getText(), isDisplayed);
        }

        return isDisplayed;
    }

    public SiteMapViewPage search(String searchText){
        controls.searchBox.sendKeys(searchText);
        WaitUtils.waitForVisibleElement(controls.searchResultsTitle, driver);
        return new SiteMapViewPage(driver);
    }

    public SectionPage clickOnSearchResult(String searchResult) {

        for(WebElement searchResultElement: controls.searchResultsList){
            if(searchResultElement.getText().equalsIgnoreCase(searchResult)){

                log.debug("Clicking on : {}", searchResult);
                searchResultElement.click();
                break;
            }
        }
        return new SectionPage(driver);
    }

    public static class SiteMapViewPageElements extends Elements{

        public SiteMapViewPageElements(){
            super();
        }

        @FindBy(css = "#sitemapSearch")
        public WebElement searchBox;

        @FindBy(css = "div#sitemap-results>h3.sub-title")
        public WebElement searchResultsTitle;

        @FindBy(css = "div.section")
        public List<WebElement> sectionList;

        @FindBy(css = "h2.section-title img")
        public List<WebElement> sectionTitleList;

        @FindBy(css = "h3.sub-title a")
        public List<WebElement> subTitleList;

        @FindBy(css = "ul.sub-section>li>a")
        public List<WebElement> subSectionList;

        @FindBy(css = "div#sitemap-results ul.sub-section>li[style='display: list-item;']>a")
        public List<WebElement> searchResultsList;
    }
}

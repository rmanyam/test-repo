package com.newsuk.model.web.navigation;

import com.newsuk.model.web.AbstractWebPage;
import com.newsuk.model.web.navigation.views.ListViewPage;
import com.newsuk.model.web.navigation.views.RSSFeedsViewPage;
import com.newsuk.model.web.navigation.views.SiteMapViewPage;
import com.newsuk.model.web.sections.MenuItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.newsuk.shuriken.core.utils.SeleniumUtils.isElementPresent;
import static com.newsuk.shuriken.core.utils.SeleniumUtils.mouseOverOnElement;

public class Navigation extends AbstractWebPage {

    public NavigationElements controls;

    public Navigation(WebDriver driver) {
        super(driver, new NavigationElements());

        this.controls = this.elements instanceof NavigationElements
                ? (NavigationElements)this.elements : null;
    }

    /**
     * Method to check if the given Menu item is displayed.
     * @param item
     * @return True if Menu item is displayed.
     */
    public boolean isMenuItemDisplayed(MenuItem item){
        return isElementPresent(getMenuItemElement(item));
    }

    private WebElement getMenuItemElement(MenuItem item){
        WebElement element = null;
        switch (item){
            case NEWS:
                element =  controls.news;
                break;
            case OPINION:
                element =  controls.opinion;
                break;
            case BUSINESS:
                element =  controls.business;
                break;
            case MONEY:
                element =  controls.money;
                break;
            case SPORT:
                element =  controls.sport;
                break;
            case LIFE:
                element =  controls.life;
                break;
            case ARTS:
                element = controls.arts;
                break;
            case PUZZLES:
                element =  controls.puzzles;
                break;
            case PAPERS:
                element =  controls.papers;
                break;
        }

        return element;
    }

    public String getDate(){
        return controls.date.getText();
    }

    public NavigationPanel mouseOverOnMenuItem(MenuItem menuItem){
        mouseOverOnElement(getMenuItemElement(menuItem), driver);
        return new NavigationPanel(driver);
    }

    public ListViewPage clickOnListView(){
        controls.listView.click();
        return new ListViewPage(driver);
    }

    public SiteMapViewPage clickOnSiteMapView(){
        controls.siteMapView.click();
        return new SiteMapViewPage(driver);
    }

    public RSSFeedsViewPage clickOnRSSFeedsView(){
        controls.rssView.click();
        return new RSSFeedsViewPage(driver);
    }

    public static class NavigationElements extends Elements {

        @FindBy(css = "li[id='news']")
        public WebElement news;

        @FindBy(css = "li[id='opinion']")
        public WebElement opinion;

        @FindBy(css = "li[id='business']")
        public WebElement business;

        @FindBy(css = "li[id='money']")
        public WebElement money;

        @FindBy(css = "li[id='sport']")
        public WebElement sport;

        @FindBy(css = "li[id='life']")
        public WebElement life;

        @FindBy(css = "li[id='arts']")
        public WebElement arts;

        @FindBy(css = "li[id='puzzles']")
        public WebElement puzzles;

        @FindBy(css = "li[id='papers']")
        public WebElement papers;

        @FindBy(css = "ul[id='date']>li>p")
        public WebElement date;

        @FindBy(css = "ul#sub-prime>li.list>a")
        public WebElement listView;

        @FindBy(css = "ul#sub-prime>li.sitemap>a")
        public WebElement siteMapView;

        @FindBy(css = "ul#sub-prime>li.rss>a")
        public WebElement rssView;

        public NavigationElements() {
            super();
        }


    }
}

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

public class TopNavigation extends AbstractWebPage {

    public TopNavigationElements controls;

    public TopNavigation(WebDriver driver) {
        super(driver, new TopNavigationElements());

        this.controls = this.elements instanceof TopNavigationElements
                ? (TopNavigationElements)this.elements : null;
    }


    public static class TopNavigationElements extends Elements {

        @FindBy(css = "input[name='querystring']")
        public WebElement news;

        @FindBy(css = "input[title='Search']")
        public WebElement opinion;



        public TopNavigationElements() {
            super();
        }


    }
}

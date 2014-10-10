package com.newsuk.model.web;

import com.newsuk.model.web.header.PageHeader;
import com.newsuk.model.web.navigation.Navigation;
import com.newsuk.model.web.navigation.TopNavigation;
import com.newsuk.shuriken.test.selenium.PageObjectBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by ranjithmanyam on 28/08/2014.
 */
public class AbstractWebPage extends PageObjectBase implements HTMLPage{

    public AbstractWebPage(WebDriver driver, Elements elements) {
        super(driver, elements);
    }

    public Navigation getNavigation(){

        return new Navigation(driver);

    }

    public TopNavigation getTopNavigation(){
        return new TopNavigation(driver);
    }

    public PageHeader getPageHeader(){
        return new PageHeader(driver);
    }

    /**
     * Method to get page title
     * @return
     */
    public String getPageTitle(){
        return driver.getTitle();
    }





}

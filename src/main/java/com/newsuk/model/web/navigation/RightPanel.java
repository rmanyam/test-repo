package com.newsuk.model.web.navigation;

import com.newsuk.common.utilities.CommonUtils;
import com.newsuk.model.web.AbstractWebPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by ranjithmanyam on 02/09/2014.
 */
public class RightPanel extends AbstractWebPage{

    static Logger log = LogManager.getLogger(RightPanel.class.getName());
    public RightPanelElements controls;

    public WebElement rightPanelContainer;

    public RightPanel(WebDriver driver, WebElement container){
        super(driver, new RightPanelElements());
        this.rightPanelContainer = container;
        this.controls = this.elements instanceof RightPanelElements
                ? (RightPanelElements)this.elements : null;
    }

    private List<WebElement> getSectionElements(){
        return rightPanelContainer.findElements(By.cssSelector("div.p-nav-wrap>ul>li>h4>a"));
    }

    public String getHeader(){
        try{
            log.info("In Try block!!!");
            return rightPanelContainer.findElement(By.cssSelector("h3")).getText();
        }catch (NoSuchElementException nse){
            log.info("In Catch block!!!");
            log.error("Unable to find element with selector: " + "h3");
            return  "";
        }
    }

    public List<String> getSections(){
        return CommonUtils.getStringList(getSectionElements());
    }

    public static class RightPanelElements extends Elements{

    }
}

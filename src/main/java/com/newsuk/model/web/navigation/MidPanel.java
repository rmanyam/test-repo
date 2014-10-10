package com.newsuk.model.web.navigation;

import com.newsuk.common.utilities.CommonUtils;
import com.newsuk.model.web.AbstractWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by ranjithmanyam on 02/09/2014.
 */
public class MidPanel extends AbstractWebPage{

    public MidPanelElements controls;

    public WebElement midPanelContainer;

    public MidPanel(WebDriver driver, WebElement container){
        super(driver, new MidPanelElements());
        this.midPanelContainer = container;
        this.controls = this.elements instanceof MidPanelElements
                ? (MidPanelElements)this.elements : null;
    }

    private WebElement getHeaderElement(){
        return midPanelContainer.findElement(By.cssSelector("h3"));
    }

    private List<WebElement> getSectionElements(){
        return midPanelContainer.findElements(By.cssSelector("div.p-nav-wrap>ul>li>h4>a"));
    }

    public String getHeader(){
        return getHeaderElement().getText();
    }

    public List<String> getSections(){
        return CommonUtils.getStringList(getSectionElements());
    }

    public static class MidPanelElements extends Elements{

    }
}

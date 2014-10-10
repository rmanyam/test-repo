package com.newsuk.model.web.navigation;

import com.newsuk.common.utilities.CommonUtils;
import com.newsuk.model.web.AbstractWebPage;
import com.newsuk.model.web.SectionPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by ranjithmanyam on 02/09/2014.
 */
public class LeftPanel extends AbstractWebPage{

    public LeftPanelElements controls;

    private WebElement leftPanelContainer;

    public LeftPanel(WebDriver driver, WebElement container){
        super(driver, new LeftPanelElements());
        this.leftPanelContainer = container;
        this.controls = this.elements instanceof LeftPanelElements
                ? (LeftPanelElements)this.elements : null;
    }

    public String getHeader(){
        return getHeaderElement().getText();
    }


    public List<String> getSections(){
        return CommonUtils.getStringList(getSectionElements());
    }

    public SectionPage selectSection(String sectionName){
        List<WebElement> sectionsList = getSectionElements();

        for(WebElement section: sectionsList){
            if(section.getText().equals(sectionName)){
                section.click();
                break;
            }
        }
        return new SectionPage(driver);
    }

    private List<WebElement> getSectionElements(){
        return leftPanelContainer.findElements(By.cssSelector("div.p-nav-wrap>ul>li>h4>a"));
    }

    private WebElement getHeaderElement(){
        return leftPanelContainer.findElement(By.cssSelector("h3"));
    }

    public static class LeftPanelElements extends Elements{

    }
}

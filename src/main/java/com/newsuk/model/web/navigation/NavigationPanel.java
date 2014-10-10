package com.newsuk.model.web.navigation;

import com.newsuk.model.web.AbstractWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public class NavigationPanel extends AbstractWebPage {

    public NavPanelElements controls;

    public NavigationPanel(WebDriver driver) {
        super(driver, new NavPanelElements());

        this.controls = this.elements instanceof NavPanelElements
                ? (NavPanelElements)this.elements : null;
    }


    public LeftPanel getLeftPanel(){
        return new LeftPanel(driver, controls.navPanel.findElement(By.cssSelector("div.p-nav-lhs")));
    }

    public MidPanel getMiddlePanel(){
        return new MidPanel(driver, controls.navPanel.findElement(By.cssSelector("div.p-nav-mid")));
    }

    public RightPanel getRightPanel(){
        return new RightPanel(driver, controls.navPanel.findElement(By.cssSelector("div.p-nav-rhs")));
    }

    public static class NavPanelElements extends Elements{

        @FindBy(css = "div.p-nav-panel-wrap>div[class^='p-nav-child']")
        public WebElement navPanel;

        public NavPanelElements(){
            super();
        }


    }

}

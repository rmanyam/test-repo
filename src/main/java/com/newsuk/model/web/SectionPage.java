package com.newsuk.model.web;

import org.openqa.selenium.WebDriver;

/**
 * Created by ranjithmanyam on 04/09/2014.
 */
public class SectionPage extends AbstractWebPage {



    public SectionPage(WebDriver driver){
        super(driver, new SectionPageElements());
    }


    public static class SectionPageElements extends Elements{
        public SectionPageElements(){
            super();
        }
    }
}

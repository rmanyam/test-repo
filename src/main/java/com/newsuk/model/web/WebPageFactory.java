package com.newsuk.model.web;

import com.newsuk.model.web.exceptions.PageOperationException;
import org.openqa.selenium.WebDriver;

/**
 * Created by ranjithmanyam on 10/09/2014.
 */
public class WebPageFactory {

    public static HTMLPage resolvePage(WebDriver driver){
        return resolvePage(driver.getCurrentUrl(), driver);
    }

    private static HTMLPage resolvePage(String url, WebDriver driver){
        if(url.endsWith("tto/news/")){
            return new HomePage(driver);
        }else {
            return new SectionPage(driver);
        }
    }
}

package com.newsuk.prototype;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ranjithmanyam on 02/10/2014.
 */
public class AppiumDriverTest {


    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "iPhone Device");
        desiredCapabilities.setCapability("udid", "8a020fae297fb5780da1043c92e04eefa54a3bd2");
        desiredCapabilities.setCapability("browserName", "Safari");
        URL url = new URL("http://127.0.0.1:4444/wd/hub");
        AppiumDriver driver = new AppiumDriver(url, desiredCapabilities);

// Navigate to the page and interact with the elements on the guinea-pig page using id.
        driver.get("http://saucelabs.com/test/guinea-pig");
        WebElement div = driver.findElement(By.id("i_am_an_id"));
        Assert.assertEquals("I am a div", div.getText()); //check the text retrieved matches expected value
        driver.findElement(By.id("comments")).sendKeys("My comment"); //populate the comments field by id.

//close the app.
        driver.quit();
    }
}

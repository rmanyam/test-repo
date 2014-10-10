package com.newsuk.common.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverProvider
{
	public static String GRID_IP;
	public static URL GRID_URL;

	DriverProvider(String GRID_IP)
	{
		// GRID_IP = System.getProperty("env.GRID");
		try {
			GRID_URL = new URL("http://" + GRID_IP + ":4444/wd/hub");
		} catch (MalformedURLException e) 
		{
			System.err.println("Grid URL is malformatted.");
			e.printStackTrace();
		}

	}
	
	public static WebDriver getDriver()
	{
		String browserName = System.getProperty("BROWSER");
		boolean remoteDriver = Boolean.valueOf(System.getProperty("REMOTE"));
		
		WebDriver driver = null;

		DesiredCapabilities dc = new DesiredCapabilities();

		//If user wants a remote driver then setup a remote driver with desired browser capabilities
		if(remoteDriver){
			if (browserName.equals(null) || browserName.equals("chrome")) {
				dc = DesiredCapabilities.chrome();
				dc.setPlatform(Platform.WINDOWS);

			} else if (browserName.equalsIgnoreCase("firefox"))
			{
				FirefoxProfile profile = new FirefoxProfile();
				dc = DesiredCapabilities.firefox();
				dc.setCapability(FirefoxDriver.PROFILE, profile);
				dc.setPlatform(Platform.WINDOWS);

			} else if (browserName.equalsIgnoreCase("IE"))
			{
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				dc.setBrowserName("internet explorer");
				dc.setPlatform(Platform.WINDOWS);
			}

			driver = new RemoteWebDriver(GRID_URL, dc);
		}//Other create a local driver for the requested browser
		else{
			if (browserName.equals(null) || browserName.equals("chrome")) {
				driver = new ChromeDriver();

			} else if (browserName.equalsIgnoreCase("firefox"))
			{
				//FirefoxProfile profile = new FirefoxProfile();
				//dc = DesiredCapabilities.firefox();
				//dc.setCapability(FirefoxDriver.PROFILE, profile);
				driver = new FirefoxDriver();

			} else if (browserName.equalsIgnoreCase("IE"))
			{
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver();
			}

		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		return driver;
	}

}

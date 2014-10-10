package com.newsuk.uisteps;

import com.newsuk.shuriken.core.Configuration;
import com.newsuk.shuriken.test.selenium.WebDriverProvider;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by ranjithmanyam on 29/08/2014.
 */
public class DefaultSteps extends AbstractWebTest {

    static Logger log = LogManager.getLogger(DefaultSteps.class.getName());

    /**
     * Initializes a new instance of the Default Step Definition
     * @throws Exception  In the event something goes wrong
     */
    public DefaultSteps() throws Exception {
        super();
    }

    /**
     * Method to be invoked before the scenario has started execution
     */
    @Before
    public void beforeScenario() throws Exception {

        log.info("Starting scenario execution - Configuring the properties for execution");
        Configuration.loadProperties("environment.properties");

        // obtain the environment from the properties file
        environmentTarget = Configuration.SETTINGS.getProperty("environment.target");
        Configuration.loadProperties("environment-" + environmentTarget + ".properties");
        configureProperties();

        if (log.isEnabled(Level.DEBUG)) {
            Configuration.printProperties();
        }
    }

    /**
     * Method to be invoked after the scenario has completed execution
     */
    @After
    public void afterScenario() throws Exception {

        log.info("Completed scenario execution - Closing the browser instance");

        driver = WebDriverProvider.getDriver();
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }
}

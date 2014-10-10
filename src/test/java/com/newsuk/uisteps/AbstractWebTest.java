package com.newsuk.uisteps;

import com.newsuk.shuriken.core.Configuration;
import com.newsuk.shuriken.test.bdd.CucumberWebTestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URL;


public abstract class AbstractWebTest extends CucumberWebTestBase {

    static Logger log = LogManager.getLogger(AbstractWebTest.class.getName());
    protected static String environmentTarget = "si";

    public AbstractWebTest() throws Exception {
        super();
    }

    /**
     * Setup method for the test
     * @throws Exception   In the event something goes wrong
     */
    @Override
    protected void testSetup() throws Exception {

        // launch the application
        baseUrl = new URL(Configuration.SETTINGS.getProperty("aut.target"));

    }

}

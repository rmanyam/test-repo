package com.news.uk.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty","json:src/test/resources/reports/cucumber.json", 
		"html:target/cucumber-html-report", 
		"junit:target/cucumber-junit-report/allcukes.xml"}, 
		tags="@WorldCupHub1", features = "src/test/resources/features", glue = "com.newsuk.worldcuphub.feeds.steps")
public class WorldCupHubTest { }

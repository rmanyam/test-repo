package com.news.uk.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty","json:src/test/resources/reports/cucumber.json", 
		"html:target/cucumber-html-report", 
		"junit:target/cucumber-junit-report/allcukes.xml"}, 
		tags="@ImageVersionService", features = "src/test/resources/features", glue = "com.newsuk.image.version.service.steps")
public class ImageGenerationServiceTest { }

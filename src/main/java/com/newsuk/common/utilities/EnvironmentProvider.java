package com.newsuk.common.utilities;

import java.io.File;
import java.util.Properties;

import com.google.common.base.Strings;

public class EnvironmentProvider {
	
	private static final String PROPERTIES_FILE_SUFFIX = "-env.properties";
	private static final String DEFAULT_ENV = "uat";
	
	private static final Properties properties = new Properties(); ;

	public EnvironmentProvider() {
		String envName = getEnvironment();
		
		loadProperties(envName + PROPERTIES_FILE_SUFFIX);
	}

	public String getBaseUrl() {
		return properties.getProperty("baseURL");
	}
	
	public String getBaseFeedsEngineUrl(){
		return properties.getProperty("baseFeedsEngineUrl");
	}

	public String getBaseFeedsWorldCupUrl(){
		return properties.getProperty("baseFeedsWorldCupUrl");
	}
	
	public String getCmsUsername(){
		return properties.getProperty("cmsUsername");
	}
	
	public String getCmsPassword(){
		return properties.getProperty("cmsPassword");
	}
	
	public String getCmsWebServiceUrl(){
		return properties.getProperty("cmsWebServiceUrl");
	}
	
	public String getCmsWebServiceMoveToUrl(){
		return properties.getProperty("cmsWebServiceUrlMoveTo");
	}
	
	public String getCmsWebServiceImageUrl(){
		return properties.getProperty("cmsWebServiceImageUrl");	
	}
	
	public String getBaseWorldCupFeedDomain(){
		return properties.getProperty("baseWorldCupFeedDomain");
	}
	
	public String getEnvironment() {
		String env = System.getProperty("ENV");
		
		if (Strings.isNullOrEmpty(env)) {
			env = DEFAULT_ENV;
		}
		return env;
	}
	
	public String getWebDomain(){
		return properties.getProperty("webDomain");
	}

	private void loadProperties(String propertiesFile) {
		try {

			properties.load(getClass().getClassLoader().getResourceAsStream( "." + File.separator + "properties" + File.separator + "environment" + File.separator + propertiesFile));
			
		} catch (Exception e) {
			System.err.println("Could not read properties file");
			e.printStackTrace();
		}
	}

    public String getBaseTimesSportArticleURL(){
        return properties.getProperty("baseTimesSportArticleURL");
    }
}

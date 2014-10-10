package com.newsuk.model.web;

/**
 * Created by ranjithmanyam on 04/09/2014.
 */
public enum NewsSection {

    UK_NEWS("UK News"),
    WORLD_NEWS("World News"),
    POLITICS("Politics"),
    HEALTH("Health"),
    EDUCATION("Education"),
    SCIENCE("Science"),
    TECHNOLOGY("Technology"),
    ENVIRONMENT("Environment"),
    FAITH("Faith");

    private String sectionName;

    NewsSection(String sectionName){
        this.sectionName = sectionName;
    }

    public String getSectionName(){
        return this.sectionName;
    }
}

package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum NewsSubTitle {
    UK_NEWS("UK News"),
    WORLD_NEWS("World News"),
    ENVIRONMENT("Environment"),
    FAITH("Faith"),
    POLITICS("Politics"),
    EDUCATION("Education"),
    HEALTH("Health"),
    SCIENCE("Science"),
    TECHNOLOGY("Technology"),
    WEATHER("Weather");

    private String subTitle;

    NewsSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link NewsSubTitle} based on given value.
     * @param value
     * @return {@link NewsSubTitle}
     */
    public static NewsSubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(NewsSubTitle item: NewsSubTitle.values()){
            if(value.equalsIgnoreCase(item.subTitle)){
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid SubTitle value: " + value);
    }

    /**
     * Method to get values as a List
     * @return {@link java.util.List<String>}
     */
    public static List<String> getValues(){
        List<String> subTitles = new ArrayList<>();
        for(NewsSubTitle item: NewsSubTitle.values()){
            subTitles.add(item.getSubTitle());
        }
        return subTitles;
    }
}

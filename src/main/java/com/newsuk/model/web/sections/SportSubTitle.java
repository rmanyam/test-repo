package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum SportSubTitle {
    FOOTBALL("Football"),
    ATHLETICS("Athletics"),
    BOXING("Boxing"),
    CRICKET("Cricket"),
    CYCLING("Cycling"),
    FORMULA_ONE("Formula One"),
    GOLF("Golf"),
    LONDON_2012("London 2012"),
    RACING("Racing"),
    RUGBY_LEAGUE("Rugby League"),
    RUGBY_WORLD_CUP("Rugby World Cup"),
    SNOOKER("Snooker"),
    TENNIS("Tennis"),
    US_SPORTS("US Sports"),
    SPORTS_BLOGS("Sports Blogs"),
    COLUMNISTS("Columnists");

    private String subTitle;

    SportSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link com.newsuk.model.web.sections.SportSubTitle} based on given value.
     * @param value
     * @return {@link com.newsuk.model.web.sections.SportSubTitle}
     */
    public static SportSubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(SportSubTitle item: SportSubTitle.values()){
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
        for(SportSubTitle item: SportSubTitle.values()){
            subTitles.add(item.getSubTitle());
        }
        return subTitles;
    }
}

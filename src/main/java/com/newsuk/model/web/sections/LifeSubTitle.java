package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum LifeSubTitle {
    MENS_STYLE("Men's Style"),
    FASHION_AND_BEAUTY("Fashion and Beauty"),
    FOOD_AND_DRINK("Food and Drink"),
    FAMILIES("Families"),
    RELATIONSHIPS("Relationships"),
    CELEBRITY("Celebrity"),
    HEALTH("Health"),
    PROPERTY("Property"),
    MAGAZINE("Magazine"),
    COURT_AND_SOCIAL("Court & Social"),
    TRAVEL("Travel"),
    DATING("Dating");


    private String subTitle;

    LifeSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link com.newsuk.model.web.sections.LifeSubTitle} based on given value.
     * @param value
     * @return {@link com.newsuk.model.web.sections.LifeSubTitle}
     */
    public static LifeSubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(LifeSubTitle item: LifeSubTitle.values()){
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
        for(LifeSubTitle item: LifeSubTitle.values()){
            subTitles.add(item.getSubTitle());
        }
        return subTitles;
    }
}

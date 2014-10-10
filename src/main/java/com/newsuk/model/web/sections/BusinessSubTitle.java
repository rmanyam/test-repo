package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum BusinessSubTitle {
    MARKETS("Markets"),
    MARKET_DATA("Market Data"),
    ENTREPRENEUR("Entrepreneur"),
    ECONOMICS("Economics"),
    INDUSTRIES("Industries"),
    COLUMNISTS("Columnists"),
    MOVERS_AND_SHAKERS("Movers and Shakers"),
    LAW("Law"),
    CAREER_AND_JOBS("Career and Jobs"),
    PORTFOLIO("Portfolio"),
    EMERGENCY_BUDGET("Emergency Budget");

    private String subTitle;

    BusinessSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link com.newsuk.model.web.sections.BusinessSubTitle} based on given value.
     * @param value
     * @return {@link com.newsuk.model.web.sections.BusinessSubTitle}
     */
    public static BusinessSubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(BusinessSubTitle item: BusinessSubTitle.values()){
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
        for(BusinessSubTitle item: BusinessSubTitle.values()){
            subTitles.add(item.getSubTitle());
        }
        return subTitles;
    }
}

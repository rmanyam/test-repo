package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum PapersSubTitle {
    THE_TIMES_ARCHIVE("The Times Archive"),
    TIMES_MOBILE_EDITION("Times mobile edition"),
    TIMES_PLUS("Times+");

    private String subTitle;

    PapersSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link com.newsuk.model.web.sections.PapersSubTitle} based on given value.
     * @param value
     * @return {@link com.newsuk.model.web.sections.PapersSubTitle}
     */
    public static PapersSubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(PapersSubTitle item: PapersSubTitle.values()){
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
        for(PapersSubTitle item: PapersSubTitle.values()){
            subTitles.add(item.getSubTitle());
        }
        return subTitles;
    }
}

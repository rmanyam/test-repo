package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum ArtsSubTitle {
    MUSIC("Music"),
    FILM("Film"),
    FIRST_NIGHT_REVIEWS("First Night Reviews"),
    VISUAL_ARTS("Visual Arts"),
    TV_AND_RADIO("TV & Radio"),
    STAGE("Stage"),
    BOOKS("Books");

    private String subTitle;

    ArtsSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link com.newsuk.model.web.sections.ArtsSubTitle} based on given value.
     * @param value
     * @return {@link com.newsuk.model.web.sections.ArtsSubTitle}
     */
    public static ArtsSubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(ArtsSubTitle item: ArtsSubTitle.values()){
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

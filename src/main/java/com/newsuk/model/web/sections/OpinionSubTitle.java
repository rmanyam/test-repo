package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum OpinionSubTitle {
    LEADERS("Leaders"),
    THUNDERER("Thunderer"),
    LETTERS_TO_THE_EDITOR("Letters to the Editor"),
    FEEDBACK("Feedback"),
    BLOGS("Blogs"),
    COLUMNISTS("Columnists"),
    OBITUARIES("Obituaries"),
    CARTOONS("Cartoons"),
    TIMES_LIVE("Times Live");

    private String subTitle;

    OpinionSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link com.newsuk.model.web.sections.OpinionSubTitle} based on given value.
     * @param value
     * @return {@link com.newsuk.model.web.sections.OpinionSubTitle}
     */
    public static OpinionSubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(OpinionSubTitle item: OpinionSubTitle.values()){
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
        for(OpinionSubTitle item: OpinionSubTitle.values()){
            subTitles.add(item.getSubTitle());
        }
        return subTitles;
    }
}

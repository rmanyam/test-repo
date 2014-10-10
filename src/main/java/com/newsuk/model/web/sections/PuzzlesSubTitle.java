package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum PuzzlesSubTitle {
    BRIDGE("Bridge"),
    CHESS("Chess"),
    CODEWORD("Codeword"),
    CROSSWORDS("Crosswords"),
    SUDOKU("Su Doku");

    private String subTitle;

    PuzzlesSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link com.newsuk.model.web.sections.PuzzlesSubTitle} based on given value.
     * @param value
     * @return {@link com.newsuk.model.web.sections.PuzzlesSubTitle}
     */
    public static PuzzlesSubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(PuzzlesSubTitle item: PuzzlesSubTitle.values()){
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
        for(PuzzlesSubTitle item: PuzzlesSubTitle.values()){
            subTitles.add(item.getSubTitle());
        }
        return subTitles;
    }
}

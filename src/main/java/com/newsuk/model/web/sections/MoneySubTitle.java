package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum MoneySubTitle {
    PROPERTY_AND_MORTGAGES("Property & Mortgages"),
    SAVINGS("Savings"),
    INVESTMENT("Investment"),
    INSURANCE("Insurance"),
    PENSIONS("Pensions"),
    TAX("Tax"),
    BORROWING("Borrowing"),
    CONSUMER_AFFAIRS("Consumer Affairs"),
    CALCULATORS("Calculators"),
    MONEY_MAKEOVER("Money Makeover"),
    CREDIT_CLINIC("Credit Clinic"),
    READER_GUIDES("Reader Guides");

    private String subTitle;

    MoneySubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    /**
     * Returns {@link com.newsuk.model.web.sections.MoneySubTitle} based on given value.
     * @param value
     * @return {@link com.newsuk.model.web.sections.MoneySubTitle}
     */
    public static MoneySubTitle getSubTitleFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(MoneySubTitle item: MoneySubTitle.values()){
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
        for(MoneySubTitle item: MoneySubTitle.values()){
            subTitles.add(item.getSubTitle());
        }
        return subTitles;
    }
}

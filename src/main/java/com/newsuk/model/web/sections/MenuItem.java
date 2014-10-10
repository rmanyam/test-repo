package com.newsuk.model.web.sections;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ranjithmanyam on 01/09/2014.
 */
public enum MenuItem {
    NEWS("News"),
    OPINION("Opinion"),
    BUSINESS("Business"),
    MONEY("Money"),
    SPORT("Sport"),
    LIFE("Life"),
    ARTS("Arts"),
    PUZZLES("Puzzles"),
    PAPERS("Papers");

    private String menuItem;

    MenuItem(String menuItem){
        this.menuItem = menuItem;
    }

    public String getMenuItem(){
        return menuItem;
    }

    /**
     * Returns {@link MenuItem} based on given value.
     * @param value
     * @return {@link MenuItem}
     */
    public static MenuItem getMenuItemFromString(String value){

        if(StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("Value cannot be empty or null");
        }

        for(MenuItem item: MenuItem.values()){
            if(value.equalsIgnoreCase(item.menuItem)){
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid MenuItem value: " + value);
    }
}

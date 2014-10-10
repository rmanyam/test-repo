package com.newsuk.common.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjithmanyam on 02/09/2014.
 */
public class CommonUtils {

    static Logger log = LogManager.getLogger(CommonUtils.class.getName());;

    /**
     * Util method to return String list for given WebElement list
     * @param webElementList
     * @return {@link List<String>}
     */
    public static List<String> getStringList(List<WebElement> webElementList){
        List<String> stringList = new ArrayList<>();

        for(WebElement element: webElementList){
            stringList.add(element.getText());
        }

        return stringList;
    }

    public static boolean compareLists(List<String> listA, List<String> listB){

        if(listA.size() != listB.size()){
            return false;
        }

        boolean find = false;

        printList("ListA elements", listA);
        printList("ListB elements", listB);

        for(String s : listA){
            log.debug("ListA Value: {}, is in listB: {}", s, listB.contains(s));
            find = listB.contains(s);
        }
        return find;
    }

    public static void printList(String message, List<String> strings){

        log.debug("---------------------------{}------------------------", message);

        for(String s: strings){
            log.debug(s);
        }
    }
}

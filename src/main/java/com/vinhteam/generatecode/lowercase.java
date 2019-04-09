package com.vinhteam.generatecode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ztl1
 */
/**
 * This class provides an XSLT extension function that may be utilized by Xalan-Java extension mechanism.
 */
public class lowercase {

    /**
     * This method lowercase the first character in the provided string.
     *
     * @return modified string
     */
    public static String tolower(String str) {
        return str.toLowerCase();
    }
    public static String tolower1st(String str) {
        
        if("".equals(str)) return str;
        
        if(str.length() >= 2){
            if(str.substring(0, 2).equals(str.substring(0, 2).toUpperCase())){
                return str;
            }
        }
        return str.substring(0, 1).toLowerCase()+ str.substring(1);
    }
}

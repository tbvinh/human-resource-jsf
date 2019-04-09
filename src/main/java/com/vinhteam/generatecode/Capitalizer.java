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
public class Capitalizer {

    /**
     * This method capitalizes the first character in the provided string.
     *
     * @return modified string
     */
    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.util;

import java.io.File;

/**
 *
 * @author VinhE7440
 */
public class Configuration {
    public static String getUploadImageLocation(){
        String path = "D:/tmp/pictures";
            File file = new File(path);
            if (!file.exists()) {
                path = "/Projects/hr-pictures/pictures"; //Server side
            }
        return path;
    }
    public static String getUploafPDFLocation(){
        String path = "D:/tmp/pictures";
            File file = new File(path);
            if (!file.exists()) {
                path = "/Projects/hr-pictures/pdf"; //Server side
            }
        return path;
    }
}

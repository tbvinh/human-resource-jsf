/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.excel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VinhE7440
 */
public class ExcelImport implements CSVRowListener{
    String sheetName;
    List<String> headers = new ArrayList<>();
    
    public ExcelImport() {
    }

    public ExcelImport(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    
    @Override
    public void ProcessRow(int rowNum, List<String> cols) {
        if(rowNum == 0){
            headers.addAll(cols);
        }
        System.out.println(cols.get(0));
        
    }
    
}

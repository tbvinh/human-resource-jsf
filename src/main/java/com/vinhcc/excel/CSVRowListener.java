/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.excel;

import java.util.List;

/**
 *
 * @author VinhE7440
 */
public interface  CSVRowListener {
    void ProcessRow(int rowNum, List<String> cols);
}

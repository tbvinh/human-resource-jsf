/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.domain;

import java.util.Date;

/**
 *
 * @author VinhE7440
 */
public class EmployeeSalary {
    int id;
    String Khoi, PhongBan;
    Date dateFrom, dateTo;

    public EmployeeSalary(int id, String Khoi, String PhongBan, Date dateFrom, Date dateTo) {
        this.id = id;
        this.Khoi = Khoi;
        this.PhongBan = PhongBan;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKhoi() {
        return Khoi;
    }

    public void setKhoi(String Khoi) {
        this.Khoi = Khoi;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(String PhongBan) {
        this.PhongBan = PhongBan;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    
    
}

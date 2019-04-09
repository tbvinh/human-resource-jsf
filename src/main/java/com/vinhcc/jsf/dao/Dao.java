/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.dao;

import com.vinhcc.jsf.util.HRDataSource;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

/**
 *
 * @author VinhE7440
 */
public abstract class Dao<T>{
    protected String sWhere;
    protected String sSelect;
    protected String sOrderBy;
    protected String sStaticWhere;
    
    public Dao() {
        
        this.sWhere = "";
        this.sSelect = "";
        this.sOrderBy = "";
        this.sStaticWhere = "";
        
    }
    
    public abstract int insert(T clzz, List<String> err);
    public abstract int update(T clzz, List<String> err);
    
    public abstract int delete(T clzz, List<String> err);
    public abstract int delete(int id, List<String> err);
    public abstract List<T> selectAll();
    public abstract List<T> selectAll(int parentId);

    public abstract T findById(int id);
 
    protected DataSource getDataSource() throws Exception{
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        HRDataSource hr = (HRDataSource) sessionMap.get("HRDataSource");
        return hr.getDataSource();
    }
    protected String getAllWhere() {
        String ret = "";
        if (!"".equals(this.sWhere)) {
            ret += " WHERE " + this.sWhere + ((!"".equals(this.sStaticWhere)) ? " and " + this.sStaticWhere : "");
        } else if (!"".equals(this.sStaticWhere)) {
            ret += " WHERE " + this.sStaticWhere;
        }
        return ret;
    }
    public void setSelectClause(String value){
        this.sSelect = value;
    }
    public void setOrderBy(String value){
        this.sOrderBy = value;
    }
    public void setWhere(String value){
        this.sWhere = value;
    }
    public void setStaticWhere(String value){
        this.sStaticWhere = value;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.dao;

import com.vinhcc.jsf.domain.Employee;
import com.vinhcc.jsf.util.HRDataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author VinhE7440
 */
public class EmployeeDAO extends Dao<Employee> {

    public int getEmployeeId(String MSNV6){
        String sql = "Select id From Employee where MSNV6=?";
        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();
        Object result = null;
        QueryRunner run;
        int id = 0;
        try {
            run = new QueryRunner(getDataSource());
            id = run.query(sql, scalarHandler, MSNV6);
        } catch (Exception ex) {
            
        }
        
        return id;
    }
    
    public boolean isExist(String MSNV6){
        String sql = "Select count(*) From Employee where MSNV6=?";
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        Object result = null;
        QueryRunner run;
        long count = 0;
        try {
            run = new QueryRunner(getDataSource());
            count = run.query(sql, scalarHandler, MSNV6);
        } catch (Exception ex) {
            
        }
        
        return count > 0;
    }
    @Override
    public int insert(Employee clzz, List<String> err) {
        final String SQLInsert = "INSERT INTO Employee(MSNV, MSNV6 , HO, TEN, DATE_IN, DATE_OUT, CHUC_DANH, KHOI, PHONG_BAN, BO_PHAN, TOSX, CHUC_DANH_THUC, TRINH_DO, CHUYEN_MON, GIOI_TINH, NGAY_SINH, NOI_SINH, CMND, CMND_DATE, NOI_CAP, DAN_TOC, QUOC_TICH, NGUYEN_QUAN, DIA_CHI_01, DIA_CHI_02, EMAIL, DIEN_THOAI, NGUOI_THAN, MST, MST_NGAY, ATM, ATM_NGAN_HANG, SO_BKXH, MA_BENH_VIEN, TEN_BENH_VIEN, MA_BHYT, THAM_GIA_BHXH, KET_THUC_BHXH, LUONG_BHXH, SO_TK_BANK, NOTE, CREATED_DATE)"
                + "               VALUES (?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        QueryRunner run = null;
        int ret = 0;
        try {
            
            run = new QueryRunner(getDataSource());
            ret = run.update(SQLInsert, clzz.getMSNV(), clzz.getMSNV6() , clzz.getHO(), clzz.getTEN(), 
                    clzz.getDATE_IN(), clzz.getDATE_OUT(), clzz.getCHUC_DANH(), 
                    clzz.getKHOI(), clzz.getPHONG_BAN(), clzz.getBO_PHAN(), 
                    clzz.getTOSX(), clzz.getCHUC_DANH_THUC(), clzz.getTRINH_DO(), 
                    clzz.getCHUYEN_MON(), clzz.getGIOI_TINH(), clzz.getNGAY_SINH(), 
                    clzz.getNOI_SINH(), clzz.getCMND(), clzz.getCMND_DATE(), 
                    clzz.getNOI_CAP(), clzz.getDAN_TOC(), clzz.getQUOC_TICH(), 
                    clzz.getNGUYEN_QUAN(), clzz.getDIA_CHI_01(), clzz.getDIA_CHI_02(), 
                    clzz.getEMAIL(), clzz.getDIEN_THOAI(), clzz.getNGUOI_THAN(), 
                    clzz.getMST(), clzz.getMST_NGAY(), clzz.getATM(), 
                    clzz.getATM_NGAN_HANG(), clzz.getSO_BKXH(), clzz.getMA_BENH_VIEN(), 
                    clzz.getTEN_BENH_VIEN(), clzz.getMA_BHYT(), clzz.getTHAM_GIA_BHXH(), 
                    clzz.getKET_THUC_BHXH(), clzz.getLUONG_BHXH(), clzz.getSO_TK_BANK(), 
                    clzz.getNOTE(), clzz.getCREATED_DATE());
            
            if(ret > 0){
                String sql = "values IDENTITY_VAL_LOCAL()";
                
                BigDecimal identity = (BigDecimal) run.query(sql, new ScalarHandler());
                int id = identity.intValueExact();
                if(id > 0){
                    clzz.setId(id);
                }
            }
        } catch (SQLException sqlEx){
            err.add(sqlEx.getMessage());
        }
        catch (Exception ex) {
            err.add(ex.getMessage());
        }
        return ret;
    }

    @Override
    public int update(Employee clzz, List<String> err) {
        final String SQLUpdate = "UPDATE Employee set  MSNV = ?, MSNV6 = ?, HO= ?, TEN= ?, DATE_IN= ?, DATE_OUT= ?, CHUC_DANH= ?, KHOI= ?, PHONG_BAN= ?, BO_PHAN= ?, TOSX= ?, CHUC_DANH_THUC= ?, TRINH_DO= ?, CHUYEN_MON= ?, GIOI_TINH= ?, NGAY_SINH= ?, NOI_SINH= ?, CMND= ?, CMND_DATE= ?, NOI_CAP= ?, DAN_TOC= ?, QUOC_TICH= ?, NGUYEN_QUAN= ?, DIA_CHI_01= ?, DIA_CHI_02= ?, EMAIL= ?, DIEN_THOAI= ?, NGUOI_THAN= ?, MST= ?, MST_NGAY= ?, ATM= ?, ATM_NGAN_HANG= ?, SO_BKXH= ?, MA_BENH_VIEN= ?, TEN_BENH_VIEN= ?, MA_BHYT= ?, THAM_GIA_BHXH= ?, KET_THUC_BHXH= ?, LUONG_BHXH= ?, SO_TK_BANK= ?, NOTE= ? where id = ?";
        QueryRunner run = null;
        int ret = 0;
        try {
            
            run = new QueryRunner(getDataSource());
            ret = run.update(SQLUpdate, clzz.getMSNV(), clzz.getMSNV6() , clzz.getHO(), clzz.getTEN(), 
                    clzz.getDATE_IN(), clzz.getDATE_OUT(), clzz.getCHUC_DANH(), 
                    clzz.getKHOI(), clzz.getPHONG_BAN(), clzz.getBO_PHAN(), 
                    clzz.getTOSX(), clzz.getCHUC_DANH_THUC(), clzz.getTRINH_DO(), 
                    clzz.getCHUYEN_MON(), clzz.getGIOI_TINH(), clzz.getNGAY_SINH(), 
                    clzz.getNOI_SINH(), clzz.getCMND(), clzz.getCMND_DATE(), 
                    clzz.getNOI_CAP(), clzz.getDAN_TOC(), clzz.getQUOC_TICH(), 
                    clzz.getNGUYEN_QUAN(), clzz.getDIA_CHI_01(), clzz.getDIA_CHI_02(), 
                    clzz.getEMAIL(), clzz.getDIEN_THOAI(), clzz.getNGUOI_THAN(), 
                    clzz.getMST(), clzz.getMST_NGAY(), clzz.getATM(), 
                    clzz.getATM_NGAN_HANG(), clzz.getSO_BKXH(), clzz.getMA_BENH_VIEN(), 
                    clzz.getTEN_BENH_VIEN(), clzz.getMA_BHYT(), clzz.getTHAM_GIA_BHXH(), 
                    clzz.getKET_THUC_BHXH(), clzz.getLUONG_BHXH(), clzz.getSO_TK_BANK(), 
                    clzz.getNOTE(), clzz.getId());
            
        } catch (SQLException sqlEx){
            err.add(sqlEx.getMessage());
        }
        catch (Exception ex) {
            err.add(ex.getMessage());
        }
        return ret;
    }

    @Override
    public int delete(Employee clzz, List<String> err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id, List<String> err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public List<Employee> selectAll() {
        
        List<Employee> list = new LinkedList<>();
        
        ResultSetHandler<List<Employee>> resultListHandler = new BeanListHandler<>(Employee.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            String sql = "";
            
            sql = String.format("SELECT * FROM Employee");
            
            sql += getAllWhere();
                
            if(!this.sOrderBy.equals("")){
                sql += " order by " + sOrderBy;
            }
            
            list = run.query(sql, resultListHandler);
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> selectAll(int parentId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee findById(int id) {
        Employee ret = null;
        
        ResultSetHandler<Employee> resultHandler = new BeanHandler<>(Employee.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            ret = run.query("SELECT * FROM Employee where id = ?" , resultHandler, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return ret;
    }
    
}

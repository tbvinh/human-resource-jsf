/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.dao;

import com.vinhcc.jsf.domain.EmployeeContract;
import com.vinhcc.jsf.domain.EmployeeDependent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author VinhE7440
 */
public class EmployeeDependentDAO extends Dao<EmployeeDependent>{

    public int getEmployeeDependentId(int EmployeeId, String code){
        String sql = "Select id From EmployeeDependent where EmployeeId = ? and Code=?";
        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();
        Object result = null;
        QueryRunner run;
        int id = 0;
        try {
            run = new QueryRunner(getDataSource());
            id = run.query(sql, scalarHandler, EmployeeId, code);
        } catch (Exception ex) {
            
        }
        
        return id;
    }
    
    @Override
    public int insert(EmployeeDependent clzz, List<String> err) {
        final String SQLInsert = "INSERT INTO EmployeeDependent (EmployeeId, Code, Name, Active) VALUES (?,  ?,  ?,  ? )";
        QueryRunner run = null;
        int ret = 0;
        try {
            
            run = new QueryRunner(getDataSource());
            ret = run.update(SQLInsert, clzz.getEmployeeId(),  
                    clzz.getCode(),  clzz.getName(),  clzz.getActive());
            
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
    public int update(EmployeeDependent clzz, List<String> err) {
        final String SQLUpdate = "UPDATE EmployeeDependent set EmployeeId = ?, Code = ?, Name = ?, Active = ? where id = ?";
        QueryRunner run = null;
        int ret = 0;
        try {
            
            run = new QueryRunner(getDataSource());
            ret = run.update(SQLUpdate, clzz.getEmployeeId(),  
                    clzz.getCode(),  clzz.getName(),  clzz.getActive(), clzz.getId());
            
        } catch (SQLException sqlEx){
            err.add(sqlEx.getMessage());
        }
        catch (Exception ex) {
            err.add(ex.getMessage());
        }
        return ret;
    }

    @Override
    public int delete(EmployeeDependent clzz, List<String> err) {
        return delete(clzz.getId(), err);
    }

    @Override
    public int delete(int id, List<String> err) {
        final String SQLUpdate = "Delete From EmployeeDependent where id = ?";
        QueryRunner run = null;
        int ret = 0;
        try {
            
            run = new QueryRunner(getDataSource());
            ret = run.execute(SQLUpdate, id);
            
        } catch (SQLException sqlEx){
            err.add(sqlEx.getMessage());
        }
        catch (Exception ex) {
            err.add(ex.getMessage());
        }
        return ret;
    }

    @Override
    public List<EmployeeDependent> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmployeeDependent> selectAll(int parentId) {
        List<EmployeeDependent> list = new LinkedList<>();
        
        ResultSetHandler<List<EmployeeDependent>> resultListHandler = new BeanListHandler<>(EmployeeDependent.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            String sql = "";
            
            sql = String.format("SELECT * FROM EmployeeDependent where EmployeeId = " + parentId);
            
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
    public EmployeeDependent findById(int id) {
        EmployeeDependent ret = null;
        
        ResultSetHandler<EmployeeDependent> resultHandler = new BeanHandler<>(EmployeeDependent.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            ret = run.query("SELECT * FROM EmployeeDependent where id = ?" , resultHandler, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }
    
}

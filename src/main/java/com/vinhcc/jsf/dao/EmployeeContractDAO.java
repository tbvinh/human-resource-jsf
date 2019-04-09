/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.dao;

import com.vinhcc.jsf.domain.Document;
import com.vinhcc.jsf.domain.Employee;
import com.vinhcc.jsf.domain.EmployeeContract;
import com.vinhcc.jsf.util.HRDataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
public class EmployeeContractDAO extends Dao<EmployeeContract> {

    public int getEmployeeContractId(int employeeId, String code) {
        String sql = "Select id From EmployeeContract where employeeId = ? and Code=?";
        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();
        Object result = null;
        QueryRunner run;
        int id = 0;
        try {
            run = new QueryRunner(getDataSource());
            id = run.query(sql, scalarHandler, employeeId, code);
        } catch (Exception ex) {

        }

        return id;
    }

    @Override
    public int insert(EmployeeContract clzz, List<String> err) {

        final String SQLInsert = "INSERT INTO EmployeeContract (EmployeeId, Code, StartDate, EndDate, ContractType) VALUES (?,  ?,  ?,  ?, ? )";
        QueryRunner run = null;
        int ret = 0;
        try {

            run = new QueryRunner(getDataSource());
            ret = run.update(SQLInsert, clzz.getEmployeeId(),
                    clzz.getCode(), clzz.getStartDate(), clzz.getEndDate(), clzz.getContractType());

            if (ret > 0) {
                String sql = "values IDENTITY_VAL_LOCAL()";

                BigDecimal identity = (BigDecimal) run.query(sql, new ScalarHandler());
                int id = identity.intValueExact();
                if (id > 0) {
                    clzz.setId(id);
                    DocumentDAO documentDAO = new DocumentDAO();
                    for(int idx = 0; idx < clzz.getDocuments().size(); idx++){
                        Document doc = clzz.getDocuments().get(idx);
                        doc.setIdRef(id);
                        doc.setDocType("contract");
                        documentDAO.insert(doc, err);
                    }
                }
            }
        } catch (SQLException sqlEx) {
            err.add(sqlEx.getMessage());
        } catch (Exception ex) {
            err.add(ex.getMessage());
        }
        return ret;

    }
    
    @Override
    public int update(EmployeeContract clzz, List<String> err) {
        final String SQLUpdate = "UPDATE EmployeeContract set EmployeeId = ?, Code = ?, StartDate = ?, EndDate = ?, ContractType = ? where id = ?";
        QueryRunner run = null;
        int ret = 0;
        try {

            run = new QueryRunner(getDataSource());
            ret = run.update(SQLUpdate, clzz.getEmployeeId(),
                    clzz.getCode(), clzz.getStartDate(), clzz.getEndDate(), clzz.getContractType(), clzz.getId());

            DocumentDAO documentDAO = new DocumentDAO();
            for(int idx = 0; idx < clzz.getDocuments().size(); idx++){
                Document doc = clzz.getDocuments().get(idx);
                if(doc.getIdRef()==0){
                    doc.setIdRef(clzz.getId());
                    doc.setDocType("contract");
                    documentDAO.insert(doc, err);
                }
            }
        } catch (SQLException sqlEx) {
            err.add(sqlEx.getMessage());
        } catch (Exception ex) {
            err.add(ex.getMessage());
        }
        return ret;
    }

    @Override
    public int delete(EmployeeContract clzz, List<String> err) {
        return delete(clzz.getId(), err);
    }

    @Override
    public int delete(int id, List<String> err) {
        final String SQLUpdate = "Delete From EmployeeContract where id = ?";
        QueryRunner run = null;
        int ret = 0;
        try {

            run = new QueryRunner(getDataSource());
            ret = run.update(SQLUpdate, id);

        } catch (SQLException sqlEx) {
            err.add(sqlEx.getMessage());
        } catch (Exception ex) {
            err.add(ex.getMessage());
        }
        return ret;
    }

    @Override
    public List<EmployeeContract> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmployeeContract> selectAll(int parentId) {
        List<EmployeeContract> list = new LinkedList<>();

        ResultSetHandler<List<EmployeeContract>> resultListHandler = new BeanListHandler<>(EmployeeContract.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            String sql = "";

            sql = String.format("SELECT * FROM EmployeeContract where EmployeeId = " + parentId);

            sql += getAllWhere();

            if (!this.sOrderBy.equals("")) {
                sql += " order by " + sOrderBy;
            }

            list = run.query(sql, resultListHandler);
            
            getAllContractDocuments(list);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    private void getAllContractDocuments(List<EmployeeContract> contractlist){
        DocumentDAO documentDAO = new DocumentDAO();
        documentDAO.setStaticWhere("DocType='contract'");
        
        for(int idx = 0; idx < contractlist.size(); idx++){
            EmployeeContract contract = contractlist.get(idx);
            contract.setDocuments(documentDAO.selectAll(contract.getId()));
        }
    }
    @Override
    public EmployeeContract findById(int id) {
        EmployeeContract ret = null;

        ResultSetHandler<EmployeeContract> resultHandler = new BeanHandler<>(EmployeeContract.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            ret = run.query("SELECT * FROM EmployeeContract where id = ?", resultHandler, id);
            
            List<EmployeeContract> list = new LinkedList<>();
            list.add(ret);
            getAllContractDocuments(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

}

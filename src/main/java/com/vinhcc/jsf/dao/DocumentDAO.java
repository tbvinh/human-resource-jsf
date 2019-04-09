/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.dao;

import com.vinhcc.jsf.domain.Document;
import com.vinhcc.jsf.domain.EmployeeContract;
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
public class DocumentDAO extends Dao<Document>{

    @Override
    public int insert(Document clzz, List<String> err) {
        final String SQLInsert = "INSERT INTO Document (Code, FileName, FileType, DocType, IdRef) VALUES (?,  ?,  ?,  ?,  ?)";
        QueryRunner run = null;
        int ret = 0;
        try {

            run = new QueryRunner(getDataSource());
            ret = run.update(SQLInsert, clzz.getCode(),
                    clzz.getFileName(), clzz.getFileType(), 
                    clzz.getDocType(), clzz.getIdRef());

            if (ret > 0) {
                String sql = "values IDENTITY_VAL_LOCAL()";

                BigDecimal identity = (BigDecimal) run.query(sql, new ScalarHandler());
                int id = identity.intValueExact();
                if (id > 0) {
                    clzz.setId(id);
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
    public int update(Document clzz, List<String> err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Document clzz, List<String> err) {
        final String sql = "DELETE From Document where id = ?";
        QueryRunner run = null;
        int ret = 0;
        try {

            run = new QueryRunner(getDataSource());
            ret = run.execute(sql, clzz.getId());

        } catch (SQLException sqlEx) {
            err.add(sqlEx.getMessage());
        } catch (Exception ex) {
            err.add(ex.getMessage());
        }
        return ret;
    }

    @Override
    public int delete(int id, List<String> err) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Document> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Document> selectAll(int parentId) {
        List<Document> list = new LinkedList<>();

        ResultSetHandler<List<Document>> resultListHandler = new BeanListHandler<>(Document.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            String sql = "";

            sql = String.format("SELECT id, code, docType, fileName, fileType, idRef FROM Document");
            this.sWhere = "idref=" + parentId;
            sql += getAllWhere();

            if (!this.sOrderBy.equals("")) {
                sql += " order by " + sOrderBy;
            }

            list = run.query(sql, resultListHandler);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Document findById(int id) {
        Document doc = null;
        ResultSetHandler<Document> resultHandler = new BeanHandler<>(Document.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            String sql = "";

            sql = String.format("SELECT id, code, docType, fileName, fileType, idRef FROM Document");
            this.sWhere = "id = " + id;
            sql += getAllWhere();

            if (!this.sOrderBy.equals("")) {
                sql += " order by " + sOrderBy;
            }

            doc = run.query(sql, resultHandler);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }
    
    public Document findByCode(String code) {
        Document doc = null;
        ResultSetHandler<Document> resultHandler = new BeanHandler<>(Document.class);

        try {
            QueryRunner run = new QueryRunner(getDataSource());
            String sql = "";

            sql = String.format("SELECT id, code, docType, fileName, fileType, idRef FROM Document");
            this.sWhere = "code = ?";
            sql += getAllWhere();

            if (!this.sOrderBy.equals("")) {
                sql += " order by " + sOrderBy;
            }

            doc = run.query(sql, resultHandler, code);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }
}

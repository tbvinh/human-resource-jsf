package com.vinhcc.jsf.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDriver;

public class HRDataSource implements Serializable{

    DataSource dataSource = null;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void initDataSource(String user) throws Exception {

        try {
            Context initCtx = new InitialContext();
            if(user.equals("demo")){
                dataSource = (DataSource) initCtx.lookup("jdbc/laclong-hr-demo");
            }else{
                dataSource = (DataSource) initCtx.lookup("jdbc/laclong-hr");
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    public Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}

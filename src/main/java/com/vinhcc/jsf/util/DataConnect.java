package com.vinhcc.jsf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataConnect {

    static Connection con = null;

    public static Connection getConnection() {
        
        if(con != null) return con;
        
        try {
            
            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
            Class.forName(driver);
            String url = "jdbc:derby:/D:/Projects/Laclong-hr-java/database/hr-database.derby;create=true";
            con = DriverManager.getConnection(url);

            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
//        try {
//            con.close();
//            con = null;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
    }
}

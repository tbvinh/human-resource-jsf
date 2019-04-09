package com.vinhcc.jsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vinhcc.jsf.util.HRDataSource;

public class LoginDAO {

    public static boolean validate(HRDataSource hr, String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = hr.getConnection();
            ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
        return false;
    }
}

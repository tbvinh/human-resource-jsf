/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

import com.vinhcc.jsf.dao.EmployeeDAO;
import com.vinhcc.jsf.domain.Employee;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author VinhE7440
 */
@ManagedBean
@ViewScoped
@Named(value = "employeeBean")
public class EmployeeBean extends BaseBean implements Serializable {

    List<Employee> empl;
    List<Employee> emplInactive;

    @PostConstruct
    public void init() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.setStaticWhere("DATE_OUT is null");
        empl = employeeDAO.selectAll();
    }

    public List<Employee> getEmpls() {
        return empl;
    }
    public List<Employee> getEmplInactives() {
        if(emplInactive == null) {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.setStaticWhere("DATE_OUT is not null");
            emplInactive = employeeDAO.selectAll();
        }
        return emplInactive;
    }
}

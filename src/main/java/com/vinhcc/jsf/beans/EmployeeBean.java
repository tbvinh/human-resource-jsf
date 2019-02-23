/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

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
public class EmployeeBean implements Serializable {

    List<Employee> empl;

    @PostConstruct
    public void init() {
        empl = new LinkedList<>();
        
        empl.add(new Employee(1, "Vinh"));
        empl.add(new Employee(2, "Trần"));
    }

    public List<Employee> getEmpls() {
        return empl;
    }
    
}

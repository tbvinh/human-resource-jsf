/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

import com.vinhcc.jsf.domain.Employee;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author VinhE7440
 */
@ViewScoped
@Named(value = "newEmployeeBean")

public class NewEmployeeBean extends BaseBean implements Serializable {
    Employee empl;
    @PostConstruct
    public void init() {
        empl = new Employee();
    }

    public Employee getEmpl() {
        return empl;
    }

    public void setEmpl(Employee empl) {
        this.empl = empl;
    }
    
    public void save(){
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

import com.vinhcc.jsf.domain.Employee;
import com.vinhcc.jsf.domain.EmployeeSalary;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author VinhE7440
 */
@ManagedBean
@ViewScoped
@Named(value = "employeeEditBean")
public class EmployeeEditBean implements Serializable {
    
    Employee empl;
    List<EmployeeSalary> employeeSalaries;
    EmployeeSalary employeeSalary;
    
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        String data = params.get("id");
        empl = new Employee(Integer.parseInt(data), "Edit Vinh");
        
        employeeSalaries = new LinkedList<>();
        
        for(int i=1; i < 100; i++){
            employeeSalaries.add(new EmployeeSalary(i, "xxx", "yyy", 
                    new Timestamp(System.currentTimeMillis()), 
                    new Timestamp(System.currentTimeMillis())));
        }
    }

    public Employee getEmployee() {
        return empl;
    }
    public void save(){
        System.out.println("Save empl.name=" + empl.getName());
    }

    public List<EmployeeSalary> getEmployeeSalaries() {
        return employeeSalaries;
    }

    public void editEmployeeSalary(int id){
        for(EmployeeSalary itm: this.getEmployeeSalaries()){
            if(itm.getId() == id){
                employeeSalary = itm;
                break;
            }
        }
        
        //showEditSalaryPopup();
    }
    private void showEditSalaryPopup(){
        String script = "$('#dlgSalary').modal('show');";
        //script = "alert('');";
        FacesContext context = FacesContext.getCurrentInstance();
        context.getPartialViewContext()
                .getEvalScripts()
                .add(script);
    }
    public EmployeeSalary getEmployeeSalary() {
        return employeeSalary;
    }
    public void addSalary(){
        employeeSalary = new EmployeeSalary(0, "", "", null, null);
        showEditSalaryPopup();
    }
    public void saveSalary(){
        for(EmployeeSalary itm: this.getEmployeeSalaries()){
            if(itm.getId() == employeeSalary.getId()){
                itm.setKhoi(employeeSalary.getKhoi());
                break;
            }
        }
        recreateSalaryTable();
    }
    private void recreateSalaryTable(){
        String script = "$('#frmListSalary-tblSalary').DataTable({\n" +
"                           \"bStateSave\": true,"+
"                            \"columnDefs\": [\n" +
"                                {\"orderable\": false, \"targets\": 5}\n" +
"                            ]\n" +
"                        });";
              //  + "$('#exampleModal').modal('hide');";
        script = "";
        FacesContext context = FacesContext.getCurrentInstance();
        context.getPartialViewContext()
                .getEvalScripts()
                .add(script);
    }
}

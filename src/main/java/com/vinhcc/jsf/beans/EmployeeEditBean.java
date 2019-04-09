/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

import com.vinhcc.jsf.dao.EmployeeContractDAO;
import com.vinhcc.jsf.dao.EmployeeDAO;
import com.vinhcc.jsf.dao.EmployeeDependentDAO;
import com.vinhcc.jsf.domain.Document;
import com.vinhcc.jsf.domain.Employee;
import com.vinhcc.jsf.domain.EmployeeContract;
import com.vinhcc.jsf.domain.EmployeeContract;
import com.vinhcc.jsf.domain.EmployeeDependent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author VinhE7440
 */
@ManagedBean
@ViewScoped
@Named(value = "employeeEditBean")
public class EmployeeEditBean extends BaseBean implements Serializable {

    private Employee empl;
    private List<EmployeeContract> employeeContracts;
    private EmployeeContract employeeContract;
    private Part filePicture;

    private EmployeeDependent employeeDependent;
    private List<EmployeeDependent> employeeDependents;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("id"));

        EmployeeDAO emplDAO = new EmployeeDAO();
        empl = emplDAO.findById(id);

        EmployeeContractDAO contractDAO = new EmployeeContractDAO();
        employeeContracts = contractDAO.selectAll(id);

        EmployeeDependentDAO dependentDAO = new EmployeeDependentDAO();
        employeeDependents = dependentDAO.selectAll(id);

    }

    public Employee getEmployee() {
        return empl;
    }

    public void save() {
        EmployeeDAO emplDAO = new EmployeeDAO();
        List<String> err = new LinkedList<>();
        emplDAO.update(empl, err);

        showNotify(err);
    }

    public List<EmployeeContract> getEmployeeContracts() {
        return employeeContracts;
    }

    public List<EmployeeDependent> getEmployeeDependents() {
        return employeeDependents;
    }

    public void editEmployeeContract(int id) {
        EmployeeContractDAO contractDAO = new EmployeeContractDAO();
        employeeContract = contractDAO.findById(id);
//        employeeContract.getDocuments().add(new Document(1, "xxxcode",
//                "fileName.pdf", "docType", 12));
    }

    public void editEmployeeDependent(int id) {
        EmployeeDependentDAO dependentDAO = new EmployeeDependentDAO();
        employeeDependent = dependentDAO.findById(id);
    }

    private void showEditContractPopup() {
        String script = "$('#dlgContract').modal('show');";
        //script = "alert('');";
        FacesContext context = FacesContext.getCurrentInstance();
        context.getPartialViewContext()
                .getEvalScripts()
                .add(script);
    }

    public EmployeeDependent getEmployeeDependent() {
        return employeeDependent;
    }

    public EmployeeContract getEmployeeContract() {
        return employeeContract;
    }

    public void addContract() {
        employeeContract = new EmployeeContract(this.empl.getId());
        employeeContract.setGuid(java.util.UUID.randomUUID().toString());
        
    }

    public void deleteContract() {
        EmployeeContractDAO contractDAO = new EmployeeContractDAO();
        List<String> err = new LinkedList<>();
        if (employeeContract.getId() == 0) {
            err.add("Delete Error, the Contract ID is null.");
        } else {
            contractDAO.delete(employeeContract, err);
        }

        //Reload Contracts
        employeeContracts = contractDAO.selectAll(this.empl.getId());

        showNotifyDelete(err);
    }

    public void addDependent() {

        employeeDependent = new EmployeeDependent(this.empl.getId());
    }

    public void deleteDependent() {
        EmployeeDependentDAO dependentDAO = new EmployeeDependentDAO();
        List<String> err = new LinkedList<>();

        if (employeeDependent.getId() == 0) {
            err.add("Delete Error, the Dependent ID is null.");
        } else {
            dependentDAO.delete(employeeDependent, err);
        }

        //Reload
        employeeDependents = dependentDAO.selectAll(this.empl.getId());
        employeeDependent = null;

        showNotifyDelete(err);
    }

    public void saveDependent() {
        EmployeeDependentDAO dependentDAO = new EmployeeDependentDAO();

        List<String> err = new LinkedList<>();

        if (employeeDependent.getId() == 0) {
            dependentDAO.insert(employeeDependent, err);
        } else {
            dependentDAO.update(employeeDependent, err);
        }

        //Reload Dependents
        employeeDependents = dependentDAO.selectAll(this.empl.getId());

        showNotify(err);
    }

    public void saveContract() {
        EmployeeContractDAO contractDAO = new EmployeeContractDAO();

        List<String> err = new LinkedList<>();

        if (employeeContract.getId() == 0) {
            contractDAO.insert(employeeContract, err);
        } else {
            contractDAO.update(employeeContract, err);
        }
        //Reload Contracts
        employeeContracts = contractDAO.selectAll(this.empl.getId());

        showNotify(err);
    }

    private void recreateContractTable() {
        String script = "$('#frmListContract-tblContract').DataTable({\n"
                + "                           \"bStateSave\": true,"
                + "                            \"columnDefs\": [\n"
                + "                                {\"orderable\": false, \"targets\": 5}\n"
                + "                            ]\n"
                + "                        });";
        //  + "$('#exampleModal').modal('hide');";
        script = "";
        FacesContext context = FacesContext.getCurrentInstance();
        context.getPartialViewContext()
                .getEvalScripts()
                .add(script);
    }

    public Part getFilePicture() {
        return filePicture;
    }

    public void setFilePicture(Part filePicture) {
        this.filePicture = filePicture;
    }

    public void upload() {
        if (filePicture != null) {
            try (InputStream input = filePicture.getInputStream()) {
                String fileName = filePicture.getSubmittedFileName();
                Files.copy(input, new File("d:/tmp/", fileName).toPath());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload successfully ended!"));
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
                e.printStackTrace();
            }
        }
    }
}

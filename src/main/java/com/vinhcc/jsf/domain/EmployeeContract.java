/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author VinhE7440
 */
public class EmployeeContract {
    int id;
    int EmployeeId;
    String Code; 
    Date StartDate, EndDate;
    String Note;
    int Active;
    String ContractType;
    String guid;
    List<Document> documents;
    
    public EmployeeContract() {
        documents = new LinkedList<>();
        
    }
    public EmployeeContract(int EmployeeId) {
        this();
        this.EmployeeId = EmployeeId;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int Active) {
        this.Active = Active;
    }

    public String getContractType() {
        return ContractType;
    }

    public void setContractType(String ContractType) {
        this.ContractType = ContractType;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
    
    
}

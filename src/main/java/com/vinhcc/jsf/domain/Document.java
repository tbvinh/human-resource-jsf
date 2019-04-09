/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.domain;

/**
 *
 * @author VinhE7440
 */
public class Document {
    int id;
    String code;
    String fileName;
    String fileType;
    String docType;
    int idRef;
    
    public Document(){

    }
    public Document(int id, String code, String fileName, String fileType, String docType, int idRef) {
        this.id = id;
        this.code = code;
        this.fileName = fileName;
        this.docType = docType;
        this.fileType = fileType;
        this.idRef = idRef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idRef) {
        this.idRef = idRef;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    
}

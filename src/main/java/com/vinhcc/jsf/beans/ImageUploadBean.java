/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

import com.vinhcc.jsf.util.Configuration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.servlet.http.Part;

@ManagedBean
@ViewScoped
@Named(value = "imageUploadBean")
public class ImageUploadBean {

    private Part fileUpload;
    private String fileName;
    private File uploadedFile;
    
    @PostConstruct
    public void init() {
        fileName="empl-blank.jpg";    
    }
    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void handleFileUpload(AjaxBehaviorEvent event) {
//        System.out.println("file size: " + fileUpload.getSize());
//        System.out.println("file type: " + fileUpload.getContentType());
//        System.out.println("file info: " + fileUpload.getHeader("Content-Disposition"));
        upload();
    }
    public void save(String msnv6){
        if(uploadedFile!=null){
            
            Path source;
            try {
                source = Paths.get(uploadedFile.getCanonicalPath());
                
                if(!source.toFile().exists()) return;
                
                Path oldPath = source.resolveSibling(msnv6 +".jpg");
                if(oldPath.toFile().exists()){
                    oldPath.toFile().delete();
                }
                Files.move(source, source.resolveSibling(msnv6 +".jpg"));
                
                String script = "$('#profileUploadModal').modal('hide');location.reload();";
                
                FacesContext context = FacesContext.getCurrentInstance();
                context.getPartialViewContext()
                        .getEvalScripts()
                        .add(script);
            } catch (IOException ex) {
                Logger.getLogger(ImageUploadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    private void upload() {
        this.fileName = "blank";
        if (fileUpload != null) {
            String path = Configuration.getUploadImageLocation();

            try (InputStream input = fileUpload.getInputStream()) {
                fileName = new File(fileUpload.getSubmittedFileName()).getName();
                
                uploadedFile = new File(path, fileName);
                
                Files.copy(input, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload successfully!"));
                
                
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
                e.printStackTrace();
            }
        }
    }
}

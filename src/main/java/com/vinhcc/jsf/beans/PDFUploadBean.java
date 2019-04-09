/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

import com.vinhcc.jsf.dao.DocumentDAO;
import com.vinhcc.jsf.domain.Document;
import com.vinhcc.jsf.domain.EmployeeContract;
import com.vinhcc.jsf.util.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@ManagedBean
@ViewScoped
@Named(value = "PDFUploadBean")
public class PDFUploadBean extends BaseBean {

    private Part fileUpload;
    private String fileName;
    private File uploadedFile;
    private String fileCode;

    @PostConstruct
    public void init() {
        fileName = "";
    }

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void handleFileUpload(AjaxBehaviorEvent event) {
        upload();
    }

    public void delete(EmployeeContract contract) {
        DocumentDAO docDao = new DocumentDAO();
        Document doc = null;
        for(Document idx: contract.getDocuments()){
            if(idx.getCode().equals(fileCode)){
                doc = idx;
                break;
            }
        }
        contract.getDocuments().remove(doc);
        //doc = docDao.findByCode(fileCode);
        
        List<String> err = new LinkedList<String>();

        if (doc == null) {
            err.add("Cannot find the Document.");
        } else {

            File file = new File(Configuration.getUploafPDFLocation() + File.separatorChar + doc.getFileName());
            if (file.exists()) {
                file.delete();
            }

            docDao.delete(doc, err);
        }
        if (err.size() > 0) {
            showNotifyDelete(err);
        }
    }

    public void save(EmployeeContract contract) {
        if (uploadedFile != null) {

            String guid = contract.getGuid();

            if (guid == null || guid.isEmpty()) {//edit

                guid = java.util.UUID.randomUUID().toString();

            } else {//add

            }
            Path source;
            try {
                String extension = "";

                int i = uploadedFile.getName().lastIndexOf('.');
                if (i >= 0) {
                    extension = uploadedFile.getName().substring(i + 1);
                }

                source = Paths.get(uploadedFile.getCanonicalPath());

                if (!source.toFile().exists()) {
                    return;
                }

                Path oldPath = source.resolveSibling(guid + "." + extension);
                if (oldPath.toFile().exists()) {
                    oldPath.toFile().delete();
                }
                Path newFile = source.resolveSibling(guid + "_" + uploadedFile.getName());
                Files.copy(source, newFile);
                contract.getDocuments().add(new Document(0, guid, uploadedFile.getName(), extension, "contract", 0));
//                String script = "$('#profileUploadModal').modal('hide');location.reload();";
//                
//                FacesContext context = FacesContext.getCurrentInstance();
//                context.getPartialViewContext()
//                        .getEvalScripts()
//                        .add(script);

            } catch (IOException ex) {
                Logger.getLogger(PDFUploadBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void upload() {
        this.fileName = "blank";
        if (fileUpload != null) {
            String path = Configuration.getUploafPDFLocation();

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

    public void download(int docid) throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        DocumentDAO docDao = new DocumentDAO();
        Document doc = docDao.findById(docid);

        String docFileName = doc.getFileName();
        File file = new File(Configuration.getUploafPDFLocation() + File.separatorChar + docFileName);

        int contentLength = (int) file.length();

        String contentType = doc.getFileType();
        if (contentType != null) {
            if (contentType.equalsIgnoreCase("pdf")) {
                contentType = "application/pdf";
            } else if (contentType.equalsIgnoreCase("xlsx") || contentType.equalsIgnoreCase("xls")) {
                contentType = "application/vnd.ms-excel";
            } else {
                contentType = "application/octet-stream";
            }
        } else {
            contentType = "application/octet-stream";
        }

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType(contentType); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + docFileName + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        OutputStream output = ec.getResponseOutputStream();
        final HttpServletResponse response = (HttpServletResponse) ec.getResponse();

        FileInputStream input = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        final ServletOutputStream out = response.getOutputStream();

        while ((input.read(buffer)) != -1) {
            out.write(buffer);
        }

        out.flush();

        fc.responseComplete();
    }
}

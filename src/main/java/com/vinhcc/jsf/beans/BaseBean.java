/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhcc.jsf.beans;

import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author VinhE7440
 */
public class BaseBean {
    protected void showNotify(List<String> err){
        String script;
        if (err.size() > 0) {
            String err_msg = "";
            for(String s : err){
                err_msg += s + "<br/>";
            }
            script = "$.notify('<strong>Saving</strong> Không lưu được, bị lỗi: "+ 
                    err_msg.replaceAll("'", "`") + 
                    "', { type: 'info',allow_dismiss: true, z_index:1060,animate: { enter: 'animated fadeInDown', exit: 'animated fadeOutUp' } });";
        } else {
            script = "$.notify('<strong>Saving</strong> Đã lưu dữ liệu thành công', { type: 'info', delay: '1000' , allow_dismiss: true, z_index:1060, animate: { enter: 'animated fadeInDown', exit: 'animated fadeOutUp' } });";
        }
        FacesContext context = FacesContext.getCurrentInstance();
            context.getPartialViewContext()
                    .getEvalScripts()
                    .add(script);
    }
    protected void showNotifyDelete(List<String> err){
        String script;
        if (err.size() > 0) {
            String err_msg = "";
            for(String s : err){
                err_msg += s + "<br/>";
            }
            script = "$.notify('<strong>Saving</strong> Không xóa được, bị lỗi: "+ 
                    err_msg.replaceAll("'", "`") + 
                    "', { type: 'info',allow_dismiss: true, z_index:1060, animate: { enter: 'animated fadeInDown', exit: 'animated fadeOutUp' } });";
        } else {
            script = "$.notify('<strong>Saving</strong> Đã xóa dữ liệu thành công', { type: 'info', delay: '1000' , allow_dismiss: true, z_index:1060,animate: { enter: 'animated fadeInDown', exit: 'animated fadeOutUp' } });";
        }
        FacesContext context = FacesContext.getCurrentInstance();
            context.getPartialViewContext()
                    .getEvalScripts()
                    .add(script);
    }
}

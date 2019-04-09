package com.vinhcc.jsf.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.vinhcc.jsf.dao.LoginDAO;
import com.vinhcc.jsf.util.HRDataSource;
import com.vinhcc.jsf.util.SessionUtils;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();

        HRDataSource hr = (HRDataSource) sessionMap.get("HRDataSource");
        if (hr == null) {
            try {
                hr = new HRDataSource();
                hr.initDataSource(user);
                sessionMap.put("HRDataSource", hr);
            } catch (Exception ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Cannot connect Database",
                                ex.getMessage()));
            }
        }

        boolean valid = LoginDAO.validate(hr, user, pwd);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);

            String contextPath = origRequest.getContextPath();
            try {
                context.getExternalContext().redirect(contextPath + "/faces/employee/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        } else {
            context.addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }
}

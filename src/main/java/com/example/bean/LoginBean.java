package com.example.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Managed Bean for Login operations
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    private boolean loggedIn;
    
    public String login() {
        // Simple validation - in real app, check against database
        if ("admin".equals(username) && "admin".equals(password)) {
            loggedIn = true;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Đăng nhập thành công", "Chào mừng bạn trở lại!"));
            return "/index.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Đăng nhập thất bại", "Tên đăng nhập hoặc mật khẩu không đúng"));
            return null;
        }
    }
    
    public String logout() {
        loggedIn = false;
        username = null;
        password = null;
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Đăng xuất thành công", "Hẹn gặp lại!"));
        return "/index.xhtml?faces-redirect=true";
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}

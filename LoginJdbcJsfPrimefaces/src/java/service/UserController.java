/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author stefanbanu
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable {

    private String username;
    private String password;

    public String login() {
        String res = UserService.INSTANCE.login(username, password);
        if (res.isEmpty()) {
            sendGuiMessage(FacesMessage.SEVERITY_ERROR, "Error", "username or password invalid!!!");
            clearInputText();
        } else {
            sendGuiMessage(FacesMessage.SEVERITY_INFO, "Success", "You logged in successfully!");
            return "home.xhtml?faces-redirect=true";
        }
        return res;
    }

    public String register() {
        String res = UserService.INSTANCE.register(username, password);
        if (res.isEmpty()) {
            sendGuiMessage(FacesMessage.SEVERITY_INFO, "Success", "You registered successfully!");
            clearInputText();
        } else {
            sendGuiMessage(FacesMessage.SEVERITY_INFO, "Info", "Username already exist!");
            clearInputText();
        }
        return "";
    }

    private void clearInputText() {
        username = "";
        password = "";
    }

    private void sendGuiMessage(FacesMessage.Severity severity, String typeMessage, String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext.getCurrentInstance().update("growl");
        context.addMessage(null, new FacesMessage(severity, typeMessage, message));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

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
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.web;

import hospital.data.DoctorBean;
import hospital.model.Doctor;
import hospital.util.Util;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import org.primefaces.context.RequestContext;

/**
 *
 * @author e.tylepov
 */
@Named
@SessionScoped
public class Authentication implements Serializable {

    @Inject
    private DoctorBean user;

    @Inject
    private FacesContext fc;

    @NotNull
    private String login;

    @NotNull
    private String password;

    private String fullName;

    public String login() {
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();

        try {
            req.login(login, Util.hashSha256(login + password));
            user.resetLoginTries(login);
            fullName = user.find(login).getFullName();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | ServletException e) {
            user.loginFailed(login);

            Util.addError("Доступ запрещен");
            RequestContext.getCurrentInstance().execute("jQuery('#dialog').effect('shake', { times:3 }, 500)");

            return null;
        }

        return "/doctor/main.xhtml";
    }

    public String logout() {
        fc.getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public Doctor getDoctor() {
        return user.find(login);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.web;

import hospital.data.Bean;
import hospital.data.CheckoutBean;
import hospital.model.Checkout;
import hospital.model.Doctor;
import hospital.util.Util;
import hospital.web.event.PatientEvent;
import hospital.web.event.Selection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author yerzhan
 */
@Named
@SessionScoped
public class Checkouts extends Controller<Checkout> {

    @Inject
    private FacesContext fc;

    public void print() {
        try {
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentType("application/pdf");
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"Checkout.pdf\"");
            bean.report(getSelected(), ec.getResponseOutputStream());
            fc.responseComplete();
        } catch (SQLException | JRException | IOException ex) {
            Util.addError(ex);
        }
    }

    @Inject
    private CheckoutBean bean;

    @Override
    protected Bean getBean() {
        return bean;
    }

    public void patientSelected(@Observes @Selection PatientEvent event) {
        items = null;
        setSelected(null);
    }

    @Override
    public List<Checkout> getItems() {
        if (items == null) {
            items = bean.find(patients.getSelected());
        }
        return items;
    }

    @Inject
    private Authentication authentication;

    private Doctor doctor;

    @PostConstruct
    public void setUp() {
        doctor = authentication.getDoctor();
    }

    @Inject
    private Patients patients;

    @Override
    protected Checkout createEntity() {
        Checkout checkout = new Checkout();
        checkout.setDoctor(doctor);
        checkout.setPatient(patients.getSelected());
        return checkout;
    }

}

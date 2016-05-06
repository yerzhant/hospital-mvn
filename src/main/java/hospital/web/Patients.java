/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.web;

import hospital.data.Bean;
import hospital.data.PatientBean;
import hospital.model.Patient;
import hospital.web.event.PatientEvent;
import hospital.web.event.Selection;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Patients extends Controller<Patient> {

    @Inject
    @Selection
    private Event<PatientEvent> selection;

    @Override
    public void setSelected(Patient selected) {
        super.setSelected(selected);
        selection.fire(new PatientEvent(selected));
    }

    public void filter() {
        items = bean.filter(filter);
        setSelected(null);
    }

    // by (part of) last name, case insensetivly
    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Inject
    private PatientBean bean;

    @Override
    protected Bean getBean() {
        return bean;
    }

    @Override
    protected Patient createEntity() {
        return new Patient();
    }

}

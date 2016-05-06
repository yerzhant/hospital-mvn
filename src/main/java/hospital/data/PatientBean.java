/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.data;

import hospital.model.Patient;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yerzhan
 */
@Stateless
public class PatientBean extends Bean<Patient> {

    public PatientBean() {
        super(Patient.class);
    }

    public List<Patient> filter(String filter) {
        filter = filter == null ? "" : filter;
        return em.createNamedQuery(Patient.FILTER).setParameter("filter", filter).getResultList();
    }

}

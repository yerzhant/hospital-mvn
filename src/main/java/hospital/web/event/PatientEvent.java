/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.web.event;

import hospital.model.Patient;

/**
 *
 * @author yerzhan
 */
public class PatientEvent {

    public Patient patient;

    public PatientEvent(Patient patient) {
        this.patient = patient;
    }

}

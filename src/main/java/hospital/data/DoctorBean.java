/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.data;

import hospital.model.Doctor;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yerzhan
 */
@Stateless
public class DoctorBean extends Bean<Doctor> {

    public DoctorBean() {
        super(Doctor.class);
    }

    public void loginFailed(String login) {
        Doctor u = find(login);

        if (u != null) {
            short t = u.getLeftLoginTries();
            if (t > 0) {
                u.setLeftLoginTries((short) (t - 1));
                edit(u);
            }
        }
    }

    public void resetLoginTries(String login) {
        Doctor u = find(login);

        if (u != null) {
            u.resetLeftLoginTries();
            edit(u);
        }
    }

    public Doctor find(String login) {
        List<Doctor> l = em.createNamedQuery(Doctor.FIND_BY_LOGIN).setParameter("login", login).getResultList();
        return l.size() == 1 ? l.get(0) : null;
    }

}

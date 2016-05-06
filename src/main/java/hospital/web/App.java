/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author yerzhan
 */
@Named
@ApplicationScoped
public class App {

    private final String verion = "0.1.alfa1";

    public String getVerion() {
        return verion;
    }

}

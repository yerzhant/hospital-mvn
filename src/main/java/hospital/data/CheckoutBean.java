/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.data;

import hospital.model.Checkout;
import hospital.model.Patient;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author yerzhan
 */
@Stateless
public class CheckoutBean extends Bean<Checkout> {

    public CheckoutBean() {
        super(Checkout.class);
    }

    public List<Checkout> find(Patient patient) {
        return em.createNamedQuery(Checkout.FIND_BY_PATIENT).setParameter("patient", patient).getResultList();
    }

    @Resource(lookup = "java:jboss/datasources/hospital")
    private DataSource ds;

    @Inject
    private FacesContext fc;

    public void report(Checkout selected, OutputStream out) throws SQLException, JRException, FileNotFoundException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", selected.getId());
        InputStream in = fc.getExternalContext().getResourceAsStream("/WEB-INF/reports/checkout.jasper");
        JasperPrint jp = JasperFillManager.fillReport(in, params, ds.getConnection());
        JasperExportManager.exportReportToPdfStream(jp, out);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author yerzhan
 */
public class Util {

    public static void addError(Exception e) {
        addError(null, e);
    }

    public static void addError(String clientId, Exception e) {
        addError(clientId, e.getLocalizedMessage());
    }

    public static void addError(String s) {
        addError(null, s);
    }

    @SuppressWarnings("ThrowableResultIgnored")
    public static void addError(Throwable t) {
        while (t.getCause() != null) {
            t = t.getCause();
        }
        addError(t.getLocalizedMessage());
    }

    public static void addError(String clientId, String s) {
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, s, s);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(clientId, m);
        fc.validationFailed();
    }

    public static String hashSha256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return bytesToHex(md.digest(input.getBytes("UTF-8")));
    }

    // http://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
    public static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}

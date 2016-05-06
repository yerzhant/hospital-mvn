/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author yerzhan
 */
@Entity
@Table(schema = "core", name = "doctors")
@NamedQueries({
    @NamedQuery(name = Doctor.FIND_BY_LOGIN, query = "SELECT d FROM Doctor d WHERE d.login = :login")
})
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final short MAX_LOGIN_TRIES = 3;

    public static final String FIND_BY_LOGIN = "Doctor.findByLogin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "personal_number")
    private int personalNumber;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String position;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "last_name")
    private String lastName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 2147483647)
    private String surname;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String login;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String password;

    @Basic(optional = false)
    @NotNull
    @Min(0)
    @Max(3)
    @Column(name = "left_login_tries")
    private short leftLoginTries;

    @OneToMany(mappedBy = "doctor")
    private List<Checkout> checkoutList;

    public final void resetLeftLoginTries() {
        leftLoginTries = MAX_LOGIN_TRIES;
    }

    public final String getFullName() {
        String sn = getSurname();
        sn = sn != null ? " " + sn : "";
        return getLastName() + " " + getFirstName() + sn;
    }

    public Doctor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public short getLeftLoginTries() {
        return leftLoginTries;
    }

    public void setLeftLoginTries(short leftLoginTries) {
        this.leftLoginTries = leftLoginTries;
    }

    public List<Checkout> getCheckoutList() {
        return checkoutList;
    }

    public void setCheckoutList(List<Checkout> checkoutList) {
        this.checkoutList = checkoutList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "hospital.model.Doctor[ id=" + id + " ]";
    }
}

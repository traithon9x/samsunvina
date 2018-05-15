package com.samsun.samsunvina.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends Basic {

    @NotNull
    private String fullName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String gender;

    private String clinicName;

    private String dentistName;

    private String address;

    private String hardPhone;

    private String phoneNumber;

    private String email;

    private Integer dentalChairQuantity;

    private String speciality;

    private Integer employeeQuantity;

    private String dentistryNo;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Patient> patients;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "township_id")
    private Township township;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rc_id")
    private Rc rc;

    public User() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHardPhone() {
        return hardPhone;
    }

    public void setHardPhone(String hardPhone) {
        this.hardPhone = hardPhone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDentalChairQuantity() {
        return dentalChairQuantity;
    }

    public void setDentalChairQuantity(Integer dentalChairQuantity) {
        this.dentalChairQuantity = dentalChairQuantity;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getEmployeeQuantity() {
        return employeeQuantity;
    }

    public void setEmployeeQuantity(Integer employeeQuantity) {
        this.employeeQuantity = employeeQuantity;
    }

    public String getDentistryNo() {
        return dentistryNo;
    }

    public void setDentistryNo(String dentistryNo) {
        this.dentistryNo = dentistryNo;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Township getTownship() {
        return township;
    }

    public void setTownship(Township township) {
        this.township = township;
    }

    public Rc getRc() {
        return rc;
    }

    public void setRc(Rc rc) {
        this.rc = rc;
    }
}

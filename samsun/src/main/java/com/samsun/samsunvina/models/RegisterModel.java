package com.samsun.samsunvina.models;

import org.springframework.stereotype.Component;

@Component
public class RegisterModel {

    private String username;

    private String password;

    private String passwordConfirm;

    private String clinicName;

    private String dentistName;

    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private int rc_id;

    private String phoneNumber;

    private String hardPhone;

    private String email;

    private Integer employeeQuantity;

    private Integer dentalChairQuantity;

    private String speciality;

    private String address;

    private Integer townshipId;

    public RegisterModel() {
    }

    public int getRc_id() {
        return rc_id;
    }

    public void setRc_id(int rc_id) {
        this.rc_id = rc_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHardPhone() {
        return hardPhone;
    }

    public void setHardPhone(String hardPhone) {
        this.hardPhone = hardPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployeeQuantity() {
        return employeeQuantity;
    }

    public void setEmployeeQuantity(Integer employeeQuantity) {
        this.employeeQuantity = employeeQuantity;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTownshipId() {
        return townshipId;
    }

    public void setTownshipId(Integer townshipId) {
        this.townshipId = townshipId;
    }
}

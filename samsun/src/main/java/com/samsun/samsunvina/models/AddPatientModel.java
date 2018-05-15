package com.samsun.samsunvina.models;

import com.samsun.samsunvina.entities.Xray;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

public class AddPatientModel {
    private String name;
    private String birthday;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String chartno;
//    private int xrayid[];
    private List<Integer> xrayid;
    private String teethposition;
    private String implant;
    private String otherrequired;

    public AddPatientModel() {
    }

    public String getChartno() {
        return chartno;
    }

    public void setChartno(String chartno) {
        this.chartno = chartno;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Integer> getXrayid() {
        return xrayid;
    }

    public void setXrayid(List<Integer> xrayid) {
        this.xrayid = xrayid;
    }
//    public int[] getXrayid() {
//        return xrayid;
//    }

//    public void setXrayid(int[] xrayid) {
//        this.xrayid = xrayid;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }





    public String getTeethposition() {
        return teethposition;
    }

    public void setTeethposition(String teethposition) {
        this.teethposition = teethposition;
    }

    public String getImplant() {
        return implant;
    }

    public void setImplant(String implant) {
        this.implant = implant;
    }

    public String getOtherrequired() {
        return otherrequired;
    }

    public void setOtherrequired(String otherrequired) {
        this.otherrequired = otherrequired;
    }
}

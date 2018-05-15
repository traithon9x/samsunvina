package com.samsun.samsunvina.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends Basic {

    @NotNull
    @Column(name = "chartNo", unique = true)
    private String chartNo;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "comment")
    private String comment;

    @Column(name = "reply")
    private String reply;

    @Column(name = "address")
    private String address;

    @Column(name = "implant")
    private String implant;

    @Column(name = "teethPosition")
    private String teethPosition;

    @Column(name = "otherRequired")
    private String otherRequired;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;



    @OneToMany(mappedBy = "patient")
    private List<Image> images;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<XrayPatient> xrayPatients;

    public Patient() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getChartNo() {
        return chartNo;
    }

    public void setChartNo(String chartNo) {
        this.chartNo = chartNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getImplant() {
        return implant;
    }

    public void setImplant(String implant) {
        this.implant = implant;
    }

    public String getTeethPosition() {
        return teethPosition;
    }

    public void setTeethPosition(String teethPosition) {
        this.teethPosition = teethPosition;
    }

    public String getOtherRequired() {
        return otherRequired;
    }

    public void setOtherRequired(String otherRequired) {
        this.otherRequired = otherRequired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<XrayPatient> getXrayPatients() {
        return xrayPatients;
    }

    public void setXrayPatients(List<XrayPatient> xrayPatients) {
        this.xrayPatients = xrayPatients;
    }
}

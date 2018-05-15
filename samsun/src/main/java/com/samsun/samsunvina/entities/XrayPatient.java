package com.samsun.samsunvina.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "xray_patient")
public class XrayPatient extends Basic {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "xray_id")
    private Xray xray;

    public XrayPatient() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Xray getXray() {
        return xray;
    }

    public void setXray(Xray xray) {
        this.xray = xray;
    }
}

package com.samsun.samsunvina.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.apache.commons.io.FilenameUtils;

@Entity
@Table(name = "image")
public class Image extends Basic {

    @NotNull
    private String name;

    @NotNull
    private String type;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Image() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getNameWithoutExtension(){
        return FilenameUtils.removeExtension(name);
    }

    public String getFileExtension(){
        return FilenameUtils.getExtension(name);
    }
}

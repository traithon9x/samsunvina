package com.samsun.samsunvina.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "xray")
public class Xray extends Basic {
    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "xray", fetch = FetchType.LAZY)
    private List<XrayPatient> xrayPatients;

    public Xray() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XrayPatient> getXrayPatients() {
        return xrayPatients;
    }

    public void setXrayPatients(List<XrayPatient> xrayPatients) {
        this.xrayPatients = xrayPatients;
    }
}

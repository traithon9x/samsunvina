package com.samsun.samsunvina.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "city")
public class City extends Basic {

    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    private List<Township> townships;


    public City() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Township> getTownships() {
        return townships;
    }

    public void setTownships(List<Township> townships) {
        this.townships = townships;
    }

//    public List<Rc> getRcs() {
//        return rcs;
//    }
//
//    public void setRcs(List<Rc> rcs) {
//        this.rcs = rcs;
//    }
}

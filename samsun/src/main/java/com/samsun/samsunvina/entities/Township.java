package com.samsun.samsunvina.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "township")
public class Township extends Basic {

    @NotNull
    private String name;

    @NotNull
    private String acronym;

    @JsonIgnore
    @OneToMany(mappedBy = "township")
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "township")
    private List<Rc> rcs;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Township() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Rc> getRcs() {
        return rcs;
    }

    public void setRcs(List<Rc> rcs) {
        this.rcs = rcs;
    }
}

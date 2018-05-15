package com.samsun.samsunvina.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "rc")
public class Rc extends Basic {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Max(15)
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "township_id")
    private Township township;

//    @ManyToOne
//    @JoinColumn(name = "city_id")
//    private City city;

    @OneToMany(mappedBy = "rc")
    private List<User> users;

    public Rc() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }

    public Township getTownship() {
        return township;
    }

    public void setTownship(Township township) {
        this.township = township;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

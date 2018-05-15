package com.samsun.samsunvina.entities;

import com.samsun.samsunvina.enumerations.RoleName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Role extends Basic {

    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @OneToMany(mappedBy = "role")
    private List<Account> accounts;

    public Role() {
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

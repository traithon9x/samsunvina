package com.samsun.samsunvina.entities;

import com.samsun.samsunvina.constants.DatabaseConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account extends Basic {

    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 30)
    private String username;

    @NotNull
    @Size(min = 5, max = 255)
    private String password;

    @Transient
    private String passwordConfirm;

    private String avatar;

    private String token;


    @ManyToOne

    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "account")
    private User user;

    public Account() {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}

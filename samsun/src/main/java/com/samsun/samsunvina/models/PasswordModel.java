package com.samsun.samsunvina.models;

public class PasswordModel {

    private String Password;
    private String newpassword;
    private String ConfimnewPassword;

    public String getPassword() {
        return Password;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public String getConfimnewPassword() {
        return ConfimnewPassword;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public void setConfimnewPassword(String confimnewPassword) {
        ConfimnewPassword = confimnewPassword;
    }

    public PasswordModel() {
    }
}

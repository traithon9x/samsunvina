package com.samsun.samsunvina.models;

public class ContactModel {
    private String name;
    private String subject;
    private String mail;
    private String phone;
    private String write_comment;

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getWrite_comment() {
        return write_comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWrite_comment(String write_comment) {
        this.write_comment = write_comment;
    }

    public ContactModel() {
    }
}

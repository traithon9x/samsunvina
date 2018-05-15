package com.samsun.samsunvina.entities;

import javax.persistence.Entity;

@Entity
public class Promotion extends Basic{

    private String image;

    public Promotion() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

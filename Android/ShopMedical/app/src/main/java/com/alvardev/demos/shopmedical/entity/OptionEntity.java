package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;

public class OptionEntity implements Serializable{

    private int image;
    private String text;

    public OptionEntity() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "OptionEntity{" +
                "image=" + image +
                ", text='" + text + '\'' +
                '}';
    }
}

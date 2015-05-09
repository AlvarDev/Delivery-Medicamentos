package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;

public class OptionEntity implements Serializable{

    private int idOption;
    private int image;
    private String text;
    private boolean selected;

    public OptionEntity() {
    }

    public OptionEntity(int idOption, int image, String text) {
        this.idOption = idOption;
        this.image = image;
        this.text = text;
        this.selected = false;
    }

    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "OptionEntity{" +
                "idOption=" + idOption +
                ", image=" + image +
                ", text='" + text + '\'' +
                ", selected=" + selected +
                '}';
    }
}

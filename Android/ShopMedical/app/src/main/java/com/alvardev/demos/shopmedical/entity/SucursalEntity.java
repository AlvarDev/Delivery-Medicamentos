package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;

public class SucursalEntity implements Serializable{

    private String codSucursale;
    private String direction;
    private String distrito;
    private String latlng;

    public SucursalEntity(String codSucursale, String direction, String distrito, String latlng) {
        this.codSucursale = codSucursale;
        this.direction = direction;
        this.distrito = distrito;
        this.latlng = latlng;
    }

    public String getCodSucursale() {
        return codSucursale;
    }

    public void setCodSucursale(String codSucursale) {
        this.codSucursale = codSucursale;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    @Override
    public String toString() {
        return "SucursalEntity{" +
                "codSucursale='" + codSucursale + '\'' +
                ", direction='" + direction + '\'' +
                ", distrito='" + distrito + '\'' +
                ", latlng='" + latlng + '\'' +
                '}';
    }
}

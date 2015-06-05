package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;

public class DireccionEntity implements Serializable{

    private String nombre;
    private String direccion;
    private int distrito;
    private String latlong;
    private String referencia;
    private String sucursal;


    public DireccionEntity() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getDistrito() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "DireccionEntity{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", distrito=" + distrito +
                ", latlong='" + latlong + '\'' +
                ", referencia='" + referencia + '\'' +
                ", sucursal='" + sucursal + '\'' +
                '}';
    }
}

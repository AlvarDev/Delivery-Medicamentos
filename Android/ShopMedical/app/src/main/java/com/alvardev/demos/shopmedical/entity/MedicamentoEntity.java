package com.alvardev.demos.shopmedical.entity;


import java.io.Serializable;

public class MedicamentoEntity implements Serializable{
    private String nombre;
    private String presentacion;
    private double precio;
    private int cantidad;
    private boolean selected;
    private boolean restricted;
    private String superId;

    public MedicamentoEntity() {
    }

    public MedicamentoEntity(String nombre, String presentacion, double precio,
                             int cantidad, boolean selected, boolean restricted, String superId) {
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.selected = selected;
        this.restricted = restricted;
        this.superId = superId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public String getSuperId() {
        return superId;
    }

    public void setSuperId(String superId) {
        this.superId = superId;
    }

    @Override
    public String toString() {
        return "MedicamentoEntity{" +
                "nombre='" + nombre + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", selected=" + selected +
                ", restricted=" + restricted +
                ", superId='" + superId + '\'' +
                '}';
    }
}

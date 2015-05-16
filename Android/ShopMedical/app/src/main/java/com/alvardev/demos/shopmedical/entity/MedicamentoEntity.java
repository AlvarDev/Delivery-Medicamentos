package com.alvardev.demos.shopmedical.entity;


import java.io.Serializable;

public class MedicamentoEntity implements Serializable{

    private String medicamentoxUnidad;
    private String medicamentoxPresentacion;
    private double precio;
    private int cantidad;
    private boolean selected;
    private boolean restricted;
    private String superId;

    private int codSucursal;
    private int codMedicamento;
    private int codCantxPresentacion;
    private int codUnidad;
    private String descripcionMedicamento;

    public MedicamentoEntity() {
    }

    public String getMedicamentoxUnidad() {
        return medicamentoxUnidad;
    }

    public void setMedicamentoxUnidad(String medicamentoxUnidad) {
        this.medicamentoxUnidad = medicamentoxUnidad;
    }

    public String getMedicamentoxPresentacion() {
        return medicamentoxPresentacion;
    }

    public void setMedicamentoxPresentacion(String medicamentoxPresentacion) {
        this.medicamentoxPresentacion = medicamentoxPresentacion;
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

    public int getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(int codSucursal) {
        this.codSucursal = codSucursal;
    }

    public int getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(int codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public int getCodCantxPresentacion() {
        return codCantxPresentacion;
    }

    public void setCodCantxPresentacion(int codCantxPresentacion) {
        this.codCantxPresentacion = codCantxPresentacion;
    }

    public int getCodUnidad() {
        return codUnidad;
    }

    public void setCodUnidad(int codUnidad) {
        this.codUnidad = codUnidad;
    }

    public String getDescripcionMedicamento() {
        return descripcionMedicamento;
    }

    public void setDescripcionMedicamento(String descripcionMedicamento) {
        this.descripcionMedicamento = descripcionMedicamento;
    }

    @Override
    public String toString() {
        return "MedicamentoEntity{" +
                "medicamentoxUnidad='" + medicamentoxUnidad + '\'' +
                ", medicamentoxPresentacion='" + medicamentoxPresentacion + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", selected=" + selected +
                ", restricted=" + restricted +
                ", superId='" + superId + '\'' +
                ", codSucursal=" + codSucursal +
                ", codMedicamento=" + codMedicamento +
                ", codCantxPresentacion=" + codCantxPresentacion +
                ", codUnidad=" + codUnidad +
                ", descripcionMedicamento='" + descripcionMedicamento + '\'' +
                '}';
    }
}

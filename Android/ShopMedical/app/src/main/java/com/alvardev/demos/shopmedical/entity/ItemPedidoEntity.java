package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;

public class ItemPedidoEntity implements Serializable{

    private String codPedido;
    private int codSucursal;
    private int codMedicamento;
    private int codCantidadxPresentacion;
    private int codUnidad;
    private int cantidad;
    private double precioTotal;
    private String nombreMedicamento;
    private String presentacion;
    private double precioUnitario;

    public ItemPedidoEntity() {
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
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

    public int getCodCantidadxPresentacion() {
        return codCantidadxPresentacion;
    }

    public void setCodCantidadxPresentacion(int codCantidadxPresentacion) {
        this.codCantidadxPresentacion = codCantidadxPresentacion;
    }

    public int getCodUnidad() {
        return codUnidad;
    }

    public void setCodUnidad(int codUnidad) {
        this.codUnidad = codUnidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "ItemPedidoEntity{" +
                "codPedido='" + codPedido + '\'' +
                ", codSucursal=" + codSucursal +
                ", codMedicamento=" + codMedicamento +
                ", codCantidadxPresentacion=" + codCantidadxPresentacion +
                ", codUnidad=" + codUnidad +
                ", cantidad=" + cantidad +
                ", precioTotal=" + precioTotal +
                ", nombreMedicamento='" + nombreMedicamento + '\'' +
                ", presentacion='" + presentacion + '\'' +
                '}';
    }
}

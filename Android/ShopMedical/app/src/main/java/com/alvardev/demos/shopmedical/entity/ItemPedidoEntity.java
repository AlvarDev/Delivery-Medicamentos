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

    public ItemPedidoEntity() {
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
                '}';
    }
}

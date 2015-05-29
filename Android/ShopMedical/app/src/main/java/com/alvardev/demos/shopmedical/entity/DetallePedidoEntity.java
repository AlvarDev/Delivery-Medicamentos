package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;

public class DetallePedidoEntity implements Serializable{

    private String codPedido;
    private int codSucursal;
    private int codMedicamento;
    private int codCantidadxPresentacion;
    private int codUnidad;
    private String medicamentoxUnidad;
    private String medicamentoxPresentacion;
    private String descripcionMedicamento;
    private String tipoMedicamento;
    private String medicamentoReceta;
    private double precioUnitario;
    private int  cantidad;
    private double precioTotal;

    public DetallePedidoEntity() {
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

    public String getDescripcionMedicamento() {
        return descripcionMedicamento;
    }

    public void setDescripcionMedicamento(String descripcionMedicamento) {
        this.descripcionMedicamento = descripcionMedicamento;
    }

    public String getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public String getMedicamentoReceta() {
        return medicamentoReceta;
    }

    public void setMedicamentoReceta(String medicamentoReceta) {
        this.medicamentoReceta = medicamentoReceta;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
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
        return "DetallePedidoEntity{" +
                "codPedido='" + codPedido + '\'' +
                ", codSucursal=" + codSucursal +
                ", codMedicamento=" + codMedicamento +
                ", codCantidadxPresentacion=" + codCantidadxPresentacion +
                ", codUnidad=" + codUnidad +
                ", medicamentoxUnidad='" + medicamentoxUnidad + '\'' +
                ", medicamentoxPresentacion='" + medicamentoxPresentacion + '\'' +
                ", descripcionMedicamento='" + descripcionMedicamento + '\'' +
                ", tipoMedicamento='" + tipoMedicamento + '\'' +
                ", medicamentoReceta='" + medicamentoReceta + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidad=" + cantidad +
                ", precioTotal=" + precioTotal +
                '}';
    }
}

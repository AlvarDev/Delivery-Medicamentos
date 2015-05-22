package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;

public class PedidoHeaderEntity implements Serializable{

    private int codPersona;
    private int codUsuario;
    private String codPedido;
    private String fechaPedido;
    private String horaPedido;
    private double montoTotal;
    private double montoCancelar;
    private String tipoComprobante;
    private String ruc;
    private int codEstadoPedido;

    public PedidoHeaderEntity() {
    }

    public int getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(int codPersona) {
        this.codPersona = codPersona;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(String horaPedido) {
        this.horaPedido = horaPedido;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoCancelar() {
        return montoCancelar;
    }

    public void setMontoCancelar(double montoCancelar) {
        this.montoCancelar = montoCancelar;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getCodEstadoPedido() {
        return codEstadoPedido;
    }

    public void setCodEstadoPedido(int codEstadoPedido) {
        this.codEstadoPedido = codEstadoPedido;
    }


    @Override
    public String toString() {
        return "PedidoHeader{" +
                "codPersona=" + codPersona +
                ", codUsuario=" + codUsuario +
                ", codPedido='" + codPedido + '\'' +
                ", fechaPedido='" + fechaPedido + '\'' +
                ", horaPedido='" + horaPedido + '\'' +
                ", montoTotal=" + montoTotal +
                ", montoCancelar=" + montoCancelar +
                ", tipoComprobante='" + tipoComprobante + '\'' +
                ", ruc='" + ruc + '\'' +
                ", codEstadoPedido=" + codEstadoPedido +
                '}';
    }
}

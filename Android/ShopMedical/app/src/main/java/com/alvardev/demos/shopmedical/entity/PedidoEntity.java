package com.alvardev.demos.shopmedical.entity;


import java.io.Serializable;

public class PedidoEntity implements Serializable{

    private String nro;
    private String fecha;
    private String tipoDoc;
    private int tipoPedido;
    private int estado;

    public PedidoEntity() {
    }

    public PedidoEntity(String nro, String fecha, String tipoDoc, int tipoPedido, int estado) {
        this.nro = nro;
        this.fecha = fecha;
        this.tipoDoc = tipoDoc;
        this.tipoPedido = tipoPedido;
        this.estado = estado;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(int tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "nro='" + nro + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tipoDoc='" + tipoDoc + '\'' +
                ", tipoPedido=" + tipoPedido +
                ", estado=" + estado +
                '}';
    }
}

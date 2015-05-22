package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarEntity implements Serializable{

    private PedidoHeaderEntity pedido;
    private List<MedicamentoEntity> detalle;

    public CarEntity() {
        this.detalle = new ArrayList<MedicamentoEntity>();
    }

    public PedidoHeaderEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoHeaderEntity pedido) {
        this.pedido = pedido;
    }

    public List<MedicamentoEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<MedicamentoEntity> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "pedido=" + pedido +
                ", detalle=" + detalle +
                '}';
    }
}

package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarSendEntity implements Serializable{

    private PedidoHeaderEntity pedido;
    private List<ItemPedidoEntity> detalle;

    public CarSendEntity() {

        this.detalle = new ArrayList<ItemPedidoEntity>();

    }

    public PedidoHeaderEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoHeaderEntity pedido) {
        this.pedido = pedido;
    }

    public List<ItemPedidoEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<ItemPedidoEntity> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "CarSendEntity{" +
                "pedido=" + pedido +
                ", detalle=" + detalle +
                '}';
    }
}

package com.alvardev.demos.shopmedical.view.interfaces;

import com.alvardev.demos.shopmedical.entity.PedidoEntity;

import java.util.List;

public interface PedidosInterface {
    void getPedidosPro(List<PedidoEntity> pedidos);
    void getPedidosHis(List<PedidoEntity> pedidos);
}

package com.alvardev.demos.shopmedical.entity.response;

import com.alvardev.demos.shopmedical.entity.DetallePedidoEntity;

import java.util.ArrayList;
import java.util.List;

public class ResponseDetallePedido {

    private boolean success;
    private List<DetallePedidoEntity> lista;
    private String mensaje;

    public ResponseDetallePedido() {
        lista = new ArrayList<DetallePedidoEntity>();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DetallePedidoEntity> getLista() {
        return lista;
    }

    public void setLista(List<DetallePedidoEntity> lista) {
        this.lista = lista;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "ResponseDetallePedido{" +
                "success=" + success +
                ", lista=" + lista +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}

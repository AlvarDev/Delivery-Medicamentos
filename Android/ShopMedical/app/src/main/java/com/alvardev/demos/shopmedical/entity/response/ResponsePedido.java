package com.alvardev.demos.shopmedical.entity.response;

import com.alvardev.demos.shopmedical.entity.PedidoEntity;

import java.io.Serializable;
import java.util.List;

public class ResponsePedido implements Serializable{

    private boolean success;
    private String mensaje;
    private List<PedidoEntity> lista;

    public ResponsePedido() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<PedidoEntity> getLista() {
        return lista;
    }

    public void setLista(List<PedidoEntity> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "ResponsePedidoProceso{" +
                "success=" + success +
                ", mensaje='" + mensaje + '\'' +
                ", lista=" + lista +
                '}';
    }
}

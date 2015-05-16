package com.alvardev.demos.shopmedical.entity.response;

import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;

import java.io.Serializable;
import java.util.List;

public class ResponseSearch implements Serializable{

    private boolean success;
    private String mensaje;
    private List<MedicamentoEntity> lista;

    public ResponseSearch() {
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

    public List<MedicamentoEntity> getLista() {
        return lista;
    }

    public void setLista(List<MedicamentoEntity> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "ResponseSearch{" +
                "success=" + success +
                ", mensaje='" + mensaje + '\'' +
                ", lista=" + lista +
                '}';
    }
}

package com.alvardev.demos.shopmedical.entity.response;

import com.alvardev.demos.shopmedical.entity.MedEntity;

import java.util.List;

public class ResponseSintomas {

    private boolean success;
    private String mensaje;
    private List<MedEntity> lista;

    public ResponseSintomas() {
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

    public List<MedEntity> getLista() {
        return lista;
    }

    public void setLista(List<MedEntity> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "ResponseSintomas{" +
                "success=" + success +
                ", mensaje='" + mensaje + '\'' +
                ", lista=" + lista +
                '}';
    }
}

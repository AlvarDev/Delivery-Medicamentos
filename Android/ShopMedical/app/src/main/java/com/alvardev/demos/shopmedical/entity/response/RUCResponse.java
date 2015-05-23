package com.alvardev.demos.shopmedical.entity.response;

import com.alvardev.demos.shopmedical.entity.RUCEntity;

import java.io.Serializable;

public class RUCResponse implements Serializable{

    private boolean success;
    private RUCEntity object;
    private String mensaje;

    public RUCResponse() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RUCEntity getObject() {
        return object;
    }

    public void setObject(RUCEntity object) {
        this.object = object;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "RUCResponse{" +
                "success=" + success +
                ", object=" + object +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}

package com.alvardev.demos.shopmedical.entity.response;

import java.io.Serializable;

public class ResponseObject implements Serializable {

	private boolean success;
	private Object object;
	private String mensaje;

    public ResponseObject() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
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
        return "ResponseObject{" +
                "success=" + success +
                ", object=" + object +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}

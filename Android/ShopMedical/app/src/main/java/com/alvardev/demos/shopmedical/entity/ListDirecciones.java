package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ListDirecciones implements Serializable {
    private List<DireccionEntity> direcciones;

    public ListDirecciones() {
        direcciones = new LinkedList<DireccionEntity>();
    }

    public List<DireccionEntity> getDirecciones() {
        return direcciones;
    }

    @Override
    public String toString() {
        return "ListDirecciones{" +
                "direcciones=" + direcciones +
                '}';
    }

    public void setDirecciones(List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }
}
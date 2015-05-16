package com.alvardev.demos.shopmedical.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarEntity implements Serializable{

    private DireccionEntity direction;
    private List<MedicamentoEntity> medicamentos;

    public CarEntity() {
        this.medicamentos = new ArrayList<MedicamentoEntity>();
    }

    public DireccionEntity getDirection() {
        return direction;
    }

    public void setDirection(DireccionEntity direction) {
        this.direction = direction;
    }

    public List<MedicamentoEntity> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoEntity> medicamentos) {
        this.medicamentos = medicamentos;
    }


    @Override
    public String toString() {
        return "CarEntity{" +
                "direction=" + direction +
                ", medicamentos=" + medicamentos +
                '}';
    }
}

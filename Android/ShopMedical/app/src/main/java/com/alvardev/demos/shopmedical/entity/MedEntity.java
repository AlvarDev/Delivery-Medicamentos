package com.alvardev.demos.shopmedical.entity;


import java.io.Serializable;

public class MedEntity implements Serializable{

    private int codSintomasComunes;
    private int codMedicamento;
    private String nombreMedicamento;
    private String sintoma;

    public MedEntity() {
    }

    public int getCodSintomasComunes() {
        return codSintomasComunes;
    }

    public void setCodSintomasComunes(int codSintomasComunes) {
        this.codSintomasComunes = codSintomasComunes;
    }

    public int getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(int codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    @Override
    public String toString() {
        return "MedEntity{" +
                "codSintomasComunes=" + codSintomasComunes +
                ", codMedicamento=" + codMedicamento +
                ", nombreMedicamento='" + nombreMedicamento + '\'' +
                ", sintoma='" + sintoma + '\'' +
                '}';
    }
}

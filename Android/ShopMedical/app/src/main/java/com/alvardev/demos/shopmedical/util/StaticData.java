package com.alvardev.demos.shopmedical.util;


import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;

import java.util.ArrayList;
import java.util.List;

public class StaticData {

    public static final int SEARCH_RESULT = 0;

    public static List<MedicamentoEntity> getMedicamentos(){
        List<MedicamentoEntity> medicamentos = new ArrayList<MedicamentoEntity>();

        medicamentos.add(new MedicamentoEntity("Apronax 550mg", "tableta", 10.00, 0, false, false));
        medicamentos.add(new MedicamentoEntity("Apronax 275mg", "tableta", 10.00, 0, false, false));
        medicamentos.add(new MedicamentoEntity("Apronax 100mg", "tableta", 10.00, 0, false, false));
        medicamentos.add(new MedicamentoEntity("Apronax Gel 5.5%", "Crema - Ungüento", 10.00, 0, false, false));

        return medicamentos;
    }

}

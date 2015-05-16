package com.alvardev.demos.shopmedical.util;


import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.entity.OptionEntity;

import java.util.ArrayList;
import java.util.List;

public class StaticData {

    public static final int SEARCH_RESULT = 0;

    //Options LeftMenu

    public static final int BUSCAR_MEDICAMENTO = 1;
    public static final int CARRITO_DE_COMPRAS = 2;
    public static final int SINTOMAS_FRECUENTES = 3;
    public static final int PEDIDOS_EN_PROCESO = 4;
    public static final int HISTORIAL_DE_PEDIDO = 5;
    public static final int ACTUALIZAR_INFORMACION = 6;
    public static final int CERRAR_SESION = 7;

    public static final String NAME_PREFERENCE = "com.alvardev.demos.shopmedical.preferences";



    public static List<MedicamentoEntity> getMedicamentos(){
        List<MedicamentoEntity> medicamentos = new ArrayList<MedicamentoEntity>();

        medicamentos.add(new MedicamentoEntity("Apronax 550mg", "tableta", 10.00, 0, false, false,"0001"));
        medicamentos.add(new MedicamentoEntity("Apronax 275mg", "tableta", 10.00, 0, false, false,"0002"));
        medicamentos.add(new MedicamentoEntity("Apronax 100mg", "tableta", 10.00, 0, false, false,"0003"));
        medicamentos.add(new MedicamentoEntity("Apronax Gel 5.5%", "Crema - Ungüento", 10.00, 0, false, false,"0004"));
        medicamentos.add(new MedicamentoEntity("Apronax 550mg", "tableta", 10.00, 0, false, false,"0005"));
        medicamentos.add(new MedicamentoEntity("Apronax 275mg", "tableta", 10.00, 0, false, false,"0006"));
        medicamentos.add(new MedicamentoEntity("Apronax 100mg", "tableta", 10.00, 0, false, false,"0007"));
        medicamentos.add(new MedicamentoEntity("Apronax Gel 5.5%", "Crema - Ungüento", 10.00, 0, false, false,"0008"));
        medicamentos.add(new MedicamentoEntity("Apronax 550mg", "tableta", 10.00, 0, false, false,"0009"));

        return medicamentos;
    }

    public static List<OptionEntity> getOptionsList(){
        List<OptionEntity> options = new ArrayList<OptionEntity>();

        options.add(new OptionEntity(BUSCAR_MEDICAMENTO, R.drawable.buscar,"Buscar Medicamentos"));
        options.add(new OptionEntity(CARRITO_DE_COMPRAS, R.drawable.logo,"Carrito de Compras"));
        options.add(new OptionEntity(SINTOMAS_FRECUENTES, R.drawable.logo,"Sintomas Frecuentes"));
        options.add(new OptionEntity(PEDIDOS_EN_PROCESO, R.drawable.logo,"Pedidos en Proceso"));
        options.add(new OptionEntity(HISTORIAL_DE_PEDIDO, R.drawable.logo,"Historial de Pedidos"));
        options.add(new OptionEntity(ACTUALIZAR_INFORMACION, R.drawable.logo,"Actualizar Información Personal"));
        options.add(new OptionEntity(CERRAR_SESION, R.drawable.logo,"Cerrar Sesión"));

        return options;
    }

}

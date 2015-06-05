package com.alvardev.demos.shopmedical.util;


import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.entity.OptionEntity;
import com.alvardev.demos.shopmedical.entity.PedidoEntity;
import com.alvardev.demos.shopmedical.entity.SucursalEntity;

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

    public static final int BOLETA = 8;
    public static final int FACTURA = 9;
    public static final int FINISH = 10;

    public static final int PENDIENTE  = 11;
    public static final int TERMINADO = 4;
    public static final int CANCELADO = 3;

    public static final int VALIDAR_RUC = 12;
    public static final int RECOVERY_PASSWORD = 13;
    public static final int DETALLE_PEDIDO = 14;


    public static final String NAME_PREFERENCE = "com.alvardev.demos.shopmedical.preferences";

    public static List<OptionEntity> getOptionsList(){
        List<OptionEntity> options = new ArrayList<OptionEntity>();

        options.add(new OptionEntity(BUSCAR_MEDICAMENTO, R.drawable.buscar_medicamento,"Buscar Medicamentos"));
        options.add(new OptionEntity(CARRITO_DE_COMPRAS, R.drawable.neutral,"Carrito de Compras"));
        options.add(new OptionEntity(SINTOMAS_FRECUENTES, R.drawable.sintomas,"Sintomas Frecuentes"));
        options.add(new OptionEntity(PEDIDOS_EN_PROCESO, R.drawable.pending,"Pedidos en Proceso"));
        options.add(new OptionEntity(HISTORIAL_DE_PEDIDO, R.drawable.historial,"Historial de Pedidos"));
        options.add(new OptionEntity(ACTUALIZAR_INFORMACION, R.drawable.actualizar,"Actualizar Información Personal"));
        options.add(new OptionEntity(CERRAR_SESION, R.drawable.cerrar_sesion,"Cerrar Sesión"));

        return options;
    }

    public static SucursalEntity getSucursalById(int codSucursak){
        switch (codSucursak){
            case 0: return new SucursalEntity("1","Av Aguarico 879","Breña","-12.056789,-77.051527");
            case 1: return new SucursalEntity("2","Avenida Felipe Salaverry 1049","Jesús María","-12.076267,-77.041948");
            case 2: return new SucursalEntity("3","Avenida.Bolivar 860","Pueblo Libre","-12.071298,-77.060872");
            case 3: return new SucursalEntity("4","Avenida Arequipa 2665","San Isidro 15046","-12.091686,-77.033239");
            case 4: return new SucursalEntity("5","PRONABEC, Arequipa 1935","Lince 15046","-12.083889,-77.034259");
            case 5: return new SucursalEntity("6","Av República de Panamá 6239","Lima","-12.126689,-77.017820");
            case 6: return new SucursalEntity("7","Av Los Frutales 1111","Lima","-12.075332,-76.964027");
            default: return new SucursalEntity("7","Av Los Frutales 1111","Lima","-12.075332,-76.964027");
        }
    }


}

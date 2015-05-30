package com.alvardev.demos.shopmedical.view.interfaces;

import com.alvardev.demos.shopmedical.entity.DireccionEntity;
import com.alvardev.demos.shopmedical.entity.MedEntity;
import com.alvardev.demos.shopmedical.entity.UserEntity;

import java.util.List;

public interface DashboardInterface {
    void updateInformation(UserEntity user);
    void searchMedicine(String text, String sucursal);
    void goToSearchResultNew(DireccionEntity direccion);
    void goToSearchResultFromSintomas();
    void getSintomas();
    void goToPedidoProceso(int tipoDoc, double total, double pagar);
}

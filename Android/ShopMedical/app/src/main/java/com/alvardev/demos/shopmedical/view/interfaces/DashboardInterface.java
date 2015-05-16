package com.alvardev.demos.shopmedical.view.interfaces;

import com.alvardev.demos.shopmedical.entity.DireccionEntity;
import com.alvardev.demos.shopmedical.entity.UserEntity;

public interface DashboardInterface {
    void updateInformation(UserEntity user);
    void goToSearchResult(DireccionEntity direccion);
    void goToPedidoProceso(int tipoDoc, String nro);
}

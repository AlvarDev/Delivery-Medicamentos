package com.alvardev.demos.shopmedical;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.entity.CarEntity;
import com.alvardev.demos.shopmedical.entity.CarSendEntity;
import com.alvardev.demos.shopmedical.entity.ItemPedidoEntity;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.entity.UserEntity;
import com.alvardev.demos.shopmedical.entity.response.RUCResponse;
import com.alvardev.demos.shopmedical.entity.response.ResponseObject;
import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.util.CustomDialog;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.alvardev.demos.shopmedical.view.interfaces.RUCInterface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RUCActivity extends BaseActionBarActivity implements RUCInterface{

    private static final String TAG = "RUCActivity";
    @InjectView(R.id.eteRUC) EditText eteRUC;
    @InjectView(R.id.btnValidateRUC) Button btnValidateRUC;
    @InjectView(R.id.llaValidate) LinearLayout llaValidate;
    @InjectView(R.id.tviRazonSocial) TextView tviRazonSocial;
    @InjectView(R.id.tviNombreComercial) TextView tviNombreComercial;
    @InjectView(R.id.tviDireccion) TextView tviDireccion;
    @InjectView(R.id.btnBackRUC) Button btnBackRUC;

    @InjectView(R.id.btnSendPedidoRUC) Button btnSendPedidoRUC;
    @InjectView(R.id.rlayLoading) View rlayLoading;

    private double total;
    private double cancelar;
    private String ruc;
    //private String lastnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruc);
        ButterKnife.inject(this);

        total = getIntent().getDoubleExtra("total",0);
        cancelar = getIntent().getDoubleExtra("cancelar",0);
        //lastnumber = "";

        btnValidateRUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rucTemp = eteRUC.getText().toString();
                if(validateRUC(rucTemp)){
                    ruc = rucTemp;
                    rlayLoading.setVisibility(View.VISIBLE);
                    connectGet(getString(R.string.url_get_ruc) + ruc, StaticData.VALIDAR_RUC);
                }
            }
        });

        btnBackRUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eteRUC.setText("");
                eteRUC.setEnabled(true);
                llaValidate.setVisibility(View.GONE);
                btnValidateRUC.setVisibility(View.VISIBLE);
            }
        });

        btnSendPedidoRUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarPedido();
            }
        });

    }

    private void enviarPedido(){
        String carString =  getPreference("car");
        if(!carString.isEmpty()){
            CarEntity car = new Gson().fromJson(carString, CarEntity.class);

            String userString = getPreference("user");
            UserEntity user = new Gson().fromJson(userString, UserEntity.class);

            car.getPedido().setCodPedido("00000000");
            car.getPedido().setTipoComprobante("Factura");
            car.getPedido().setFechaPedido("2015-05-22");
            car.getPedido().setHoraPedido("11:20:00");
            car.getPedido().setMontoTotal(total);
            car.getPedido().setMontoCancelar(cancelar);
            car.getPedido().setRuc(ruc);

            CarSendEntity send = new CarSendEntity();
            send.setPedido(car.getPedido());

            for(MedicamentoEntity med : car.getDetalle()){


                ItemPedidoEntity item = new ItemPedidoEntity();
                item.setCodPedido("00000000");
                item.setCodSucursal(med.getCodSucursal());
                item.setCodMedicamento(med.getCodMedicamento());
                item.setCodCantidadxPresentacion(med.getCodCantxPresentacion());
                item.setCodUnidad(med.getCodUnidad());
                item.setCantidad(med.getCantidad());
                item.setPrecioTotal(med.getPrecio() * med.getCantidad());
                item.setNombreMedicamento(med.getMedicamentoxUnidad());
                item.setPresentacion(med.getMedicamentoxPresentacion());
                item.setPrecioUnitario(med.getPrecio());

                send.getDetalle().add(item);
            }
            savePreference("doneRUC", "done");
            savePreference("send",new Gson().toJson(send));
            Dialog dialogOk = new CustomDialog().showMessage(RUCActivity.this,
                    "Pedido enviado, tiene 2 minutos para cancelar su pedido");
            dialogOk.show();

            /*try {
                rlayLoading.setVisibility(View.VISIBLE);
                connectPost("http://farmaciaaa.jelasticlw.com.br/carrito",
                        new JSONObject(new Gson().toJson(send)),
                        StaticData.CARRITO_DE_COMPRAS);

                Log.i(TAG,"Sent pedido: "+new JSONObject(new Gson().toJson(send)));
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

        }
    }


    public boolean validateRUC(String ruc){

        eteRUC.setError(null);

        if(ruc.isEmpty()){
            eteRUC.setError(getString(R.string.error_field));
            return false;
        }

        return true;
    }

    @Override
    protected void onRESTResultado(int code, String result, int accion) {
        HttpCode codigo = HttpCode.forValue(code);
        rlayLoading.setVisibility(View.GONE);

        switch (codigo) {
            case OK:
                switch (accion){
                    case StaticData.CARRITO_DE_COMPRAS:
                        try {
                            ResponseObject response = new Gson().fromJson(result,ResponseObject.class);

                            if(response.isSuccess()){
                                //savePreference("doneRUC", "done");
                                //savePreference("lastNumber",lastnumber);
                                /*Dialog dialogOk = new CustomDialog().showMessage(RUCActivity.this,
                                        "Pedido enviado, tiene 5 minutos para cancelar su pedido");
                                dialogOk.show();
                            */
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getMensaje(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){
                            Log.e(TAG, e.getMessage());
                        }

                        break;
                    case StaticData.VALIDAR_RUC:

                        try {
                            RUCResponse rucResp = new Gson().fromJson(result,RUCResponse.class);

                            if(rucResp.isSuccess()){

                                btnValidateRUC.setVisibility(View.GONE);
                                llaValidate.setVisibility(View.VISIBLE);
                                eteRUC.setEnabled(false);

                                tviRazonSocial.setText(rucResp.getObject().getRazonSocial());
                                tviNombreComercial.setText(rucResp.getObject().getNombreComercial());
                                tviDireccion.setText(rucResp.getObject().getDireccion());


                                /*Toast.makeText(getApplicationContext(),
                                        rucResp.getObject().getRazonSocial()+"",
                                        Toast.LENGTH_SHORT).show();
                                enviarPedido();*/
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        rucResp.getMensaje(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){
                            Log.e(TAG, e.getMessage());
                        }

                        break;

                }
                break;
            case BAD_REQUEST:
                generalError("BAD REQUEST");
                break;
            case ERROR:
                generalError("ERROR");
                break;
            case TIMEOUT:
                generalError("TIME OUT");
                break;
            default:
                generalError("DEFAULT");
                break;
        }
    }


    @Override
    public void pedidoEnviado() {
        finish();
    }
}

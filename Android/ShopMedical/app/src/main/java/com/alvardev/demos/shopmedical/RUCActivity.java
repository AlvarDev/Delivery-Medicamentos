package com.alvardev.demos.shopmedical;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RUCActivity extends BaseActionBarActivity{

    private static final String TAG = "RUCActivity";
    @InjectView(R.id.eteRUC) EditText eteRUC;
    @InjectView(R.id.btnGetRUC) Button btnGetRUC;
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


        btnGetRUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rucTemp = eteRUC.getText().toString();
                if(validateRUC(rucTemp)){
                    ruc = rucTemp;
                    rlayLoading.setVisibility(View.VISIBLE);
                    connectGet(getString(R.string.url_get_ruc)+ruc, StaticData.VALIDAR_RUC);
                }
            }
        });

    }

    private void enviarPedido(){
        String carString =  getPreference("car");
        if(!carString.isEmpty()){
            CarEntity car = new Gson().fromJson(carString, CarEntity.class);

            String userString = getPreference("user");
            UserEntity user = new Gson().fromJson(userString, UserEntity.class);


            /*String codPedido = user.getCodPersona() <10 ? "0"+user.getCodPersona(): ""+user.getCodPersona();
            int lastNumberTemp =  getPreference("lastNumber").isEmpty() ? 1 : Integer.parseInt(getPreference("lastNumber"))+1;
            lastnumber  = lastNumberTemp+"";

            while (lastnumber.length()<6){
                lastnumber = "0"+lastnumber;
            }*/


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
                item.setPrecioTotal(med.getPrecio()*med.getCantidad());

                send.getDetalle().add(item);
            }

            try {
                rlayLoading.setVisibility(View.VISIBLE);
                connectPost("http://farmaciaa.jelasticlw.com.br/carrito",
                        new JSONObject(new Gson().toJson(send)),
                        StaticData.CARRITO_DE_COMPRAS);

                Log.i(TAG,"Sent pedido: "+new JSONObject(new Gson().toJson(send)));
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
                                savePreference("doneRUC", "done");
                                //savePreference("lastNumber",lastnumber);
                                finish();
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

                                Toast.makeText(getApplicationContext(),
                                        rucResp.getObject().getRazonSocial()+"",
                                        Toast.LENGTH_SHORT).show();
                                enviarPedido();
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


}

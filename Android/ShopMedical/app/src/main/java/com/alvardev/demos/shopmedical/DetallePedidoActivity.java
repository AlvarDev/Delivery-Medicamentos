package com.alvardev.demos.shopmedical;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.adapter.DetallePedidoAdapter;
import com.alvardev.demos.shopmedical.entity.CarSendEntity;
import com.alvardev.demos.shopmedical.entity.DetallePedidoEntity;
import com.alvardev.demos.shopmedical.entity.ItemPedidoEntity;
import com.alvardev.demos.shopmedical.entity.PedidoEntity;
import com.alvardev.demos.shopmedical.entity.response.ResponseDetallePedido;
import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.util.CustomDialog;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.alvardev.demos.shopmedical.view.interfaces.CancelarPedidoInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetallePedidoActivity extends BaseActionBarActivity implements CancelarPedidoInterface{

    private static final String TAG = "DetallePedido";

    @InjectView(R.id.lviItemsAdded) ListView lviItemsAdded;
    @InjectView(R.id.tviNroPedido) TextView tviNroPedido;
    @InjectView(R.id.tviTipoComp) TextView tviTipoComp;
    @InjectView(R.id.tviTotal) TextView tviTotal;
    @InjectView(R.id.eteEfectivo) TextView eteEfectivo;
    @InjectView(R.id.eteVuelto) TextView eteVuelto;
    @InjectView(R.id.rlayLoading) View rlayLoading;

    private PedidoEntity pedido;
    private int tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);
        ButterKnife.inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        pedido = (PedidoEntity) getIntent().getSerializableExtra("pedido");
        tipo = getIntent().getIntExtra("tipo",StaticData.PEDIDOS_EN_PROCESO);

        if(pedido.getCodPedido().equals("En proceso...")){
            setDataTemp();
        }else {
            rlayLoading.setVisibility(View.VISIBLE);
            connectGet(getString(R.string.url_detalle_pedido) + pedido.getCodPedido(), StaticData.DETALLE_PEDIDO);
        }
    }


    private void setData(final List<DetallePedidoEntity> lista){

        tviNroPedido.setText("N° Pedido" + pedido.getCodPedido());
        tviTipoComp.setText("Comprobante de pago: " + pedido.getTipoComprobante());

        DetallePedidoAdapter adapter = new DetallePedidoAdapter(getApplicationContext(), lista);
        lviItemsAdded.setAdapter(adapter);
        /*lviItemsAdded.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialogOk = new CustomDialog().descriptionDialog(getApplicationContext(),
                        lista.get(i).getDescripcionMedicamento());
                dialogOk.show();
            }
        });*/

        tviTotal.setText("Precio Total S/." + pedido.getMontoTotal());
        eteEfectivo.setText("" + pedido.getMontoCancelar());
        eteVuelto.setText("" + (pedido.getMontoCancelar() - pedido.getMontoTotal()));

    }

    private void setDataTemp(){
        tviNroPedido.setText("N° Pedido " + pedido.getCodPedido());
        tviTipoComp.setText("Comprobante de pago: " + pedido.getTipoComprobante());

        CarSendEntity tempCar = new Gson().fromJson(getPreference("send"),CarSendEntity.class);

        List<DetallePedidoEntity> lista = new ArrayList<DetallePedidoEntity>();

        for(ItemPedidoEntity item : tempCar.getDetalle()){
            DetallePedidoEntity detalle = new DetallePedidoEntity();
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioTotal(item.getPrecioTotal());
            detalle.setPrecioUnitario(item.getPrecioUnitario());
            detalle.setMedicamentoxUnidad(item.getNombreMedicamento());
            detalle.setMedicamentoxPresentacion(item.getPresentacion());
            lista.add(detalle);
        }

        DetallePedidoAdapter adapter = new DetallePedidoAdapter(getApplicationContext(), lista);
        lviItemsAdded.setAdapter(adapter);


        tviTotal.setText("Precio Total S/." + pedido.getMontoTotal());
        eteEfectivo.setText("" + pedido.getMontoCancelar());
        eteVuelto.setText("" + (pedido.getMontoCancelar() - pedido.getMontoTotal()));
    }


    @Override
    protected void onRESTResultado(int code, String result, int accion) {
        HttpCode codigo = HttpCode.forValue(code);
        rlayLoading.setVisibility(View.GONE);

        switch (codigo) {
            case OK:
                try {
                    final ResponseDetallePedido response = new Gson().fromJson(result, ResponseDetallePedido.class);

                    if(response.isSuccess()){
                        setData(response.getLista());
                    }else{
                        Toast.makeText(getApplicationContext(), response.getMensaje(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalle_pedido, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.cancel_pedido:
                if(tipo == StaticData.PEDIDOS_EN_PROCESO){
                    Dialog cancelarPedidoDialog = new CustomDialog().cancelarPedidoDialog(DetallePedidoActivity.this);
                    cancelarPedidoDialog.show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "No puede cancelar pedidos del historial",
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void cancelarPedidoPositive() {
        if(!getPreference("send").isEmpty()){
            savePreference("send",null);
            finish();
            Toast.makeText(getApplicationContext(),
                    "Pedido Cancelado",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),
                    "Ya han pasado los dos minutos",
                    Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void cancelatPedidoNegative() {

    }
}

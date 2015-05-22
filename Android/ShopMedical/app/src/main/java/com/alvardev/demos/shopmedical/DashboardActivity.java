package com.alvardev.demos.shopmedical;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.adapter.OptionsDashboardAdapter;
import com.alvardev.demos.shopmedical.entity.CarEntity;
import com.alvardev.demos.shopmedical.entity.DireccionEntity;
import com.alvardev.demos.shopmedical.entity.OptionEntity;
import com.alvardev.demos.shopmedical.entity.PedidoHeaderEntity;
import com.alvardev.demos.shopmedical.entity.UserEntity;
import com.alvardev.demos.shopmedical.entity.response.ResponseObject;
import com.alvardev.demos.shopmedical.entity.response.ResponsePedido;
import com.alvardev.demos.shopmedical.entity.response.ResponseSearch;
import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.util.CustomDialog;
import com.alvardev.demos.shopmedical.util.PedidoDialogFragment;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.alvardev.demos.shopmedical.view.fragment.ActualizarInformacionFragment;
import com.alvardev.demos.shopmedical.view.fragment.BuscarMedicamentoFragment;
import com.alvardev.demos.shopmedical.view.fragment.CarritoComprasFragment;
import com.alvardev.demos.shopmedical.view.fragment.PedidosFragment;
import com.alvardev.demos.shopmedical.view.fragment.SearchResultFragment;
import com.alvardev.demos.shopmedical.view.fragment.SintomasFrecuentesFragment;
import com.alvardev.demos.shopmedical.view.interfaces.DashboardInterface;
import com.alvardev.demos.shopmedical.view.interfaces.PedidoInterface;
import com.alvardev.demos.shopmedical.view.interfaces.PedidosInterface;
import com.alvardev.demos.shopmedical.view.interfaces.SearchInterface;
import com.alvardev.demos.shopmedical.view.interfaces.SesionDialogInterface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DashboardActivity extends BaseActionBarActivity
        implements SesionDialogInterface, DashboardInterface, PedidoDialogFragment.PedidoDialogListener{

    private static final String TAG = "DashboardActivity";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<OptionEntity> options;
    private OptionsDashboardAdapter adapter;
    private int currentSelected;
    private UserEntity user;
    private UserEntity userTemp;
    private String carString;

    @InjectView(R.id.rlayLoading) View rlayLoading;

    private SearchResultFragment searchResultFragment = SearchResultFragment.newInstance(null,null);
    private BuscarMedicamentoFragment buscarMedicamentoFragment = BuscarMedicamentoFragment.newInstance(null,null);
    private CarritoComprasFragment carritoComprasFragment = CarritoComprasFragment.newInstance(null,null);
    private SintomasFrecuentesFragment sintomasFrecuentesFragment = SintomasFrecuentesFragment.newInstance(null,null);
    private PedidosFragment pedidosProcesoFragment = PedidosFragment.newInstance(null,null);
    private PedidosFragment historialPedidosFragment = PedidosFragment.newInstance(null,null);
    private ActualizarInformacionFragment actualizarInformacionFragment = ActualizarInformacionFragment.newInstance(null,null);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Buscar Medicamento");
        ButterKnife.inject(this);

        String userString = getPreference("user");
        user = new Gson().fromJson(userString, UserEntity.class);
        carString = getPreference("car");
        setDrawer(savedInstanceState);
    }

    private void setDrawer(Bundle savedInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dlaOptions);
        mDrawerList = (ListView) findViewById(R.id.lviOptions);

        options = StaticData.getOptionsList();
        adapter = new OptionsDashboardAdapter(this, options);

        LinearLayout header = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_header, null);
        TextView tviUserName = (TextView)header.findViewById(R.id.tviUserName);
        tviUserName.setText(user.getNombres()+" "+user.getApellidoPaterno() + " "+user.getApellidoMaterno());
        mDrawerList.addHeaderView(header);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.hamburger_icon,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        );

        if (savedInstanceState == null) {
            currentSelected = StaticData.SINTOMAS_FRECUENTES;
            /*if(!carString.isEmpty()){
                CarEntity car = new Gson().fromJson(carString, CarEntity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("car",car);
                new ChangeFragmentsTask(bundle).execute(StaticData.SEARCH_RESULT);
            }else {
                new ChangeFragmentsTask(null).execute(StaticData.BUSCAR_MEDICAMENTO);
            }*/
            new ChangeFragmentsTask(null).execute(StaticData.SINTOMAS_FRECUENTES);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            Log.i(TAG,"currentSelected: "+currentSelected);
            Log.i(TAG,"pos: "+position);

            if(currentSelected != position-1){

                carString = getPreference("car");
                Bundle bundle = new Bundle();

                switch (position){
                    case StaticData.CERRAR_SESION:
                        Dialog dialogOk = new CustomDialog().cerrarSesionDialog(DashboardActivity.this);
                        dialogOk.show();
                        break;

                    case StaticData.BUSCAR_MEDICAMENTO:
                        if(!carString.isEmpty()){
                            CarEntity car = new Gson().fromJson(carString, CarEntity.class);
                            bundle.putSerializable("car", car);
                            new ChangeFragmentsTask(bundle).execute(StaticData.SEARCH_RESULT);
                        }else{
                            new ChangeFragmentsTask(null).execute(position);
                        }
                        break;

                    case StaticData.PEDIDOS_EN_PROCESO:
                        rlayLoading.setVisibility(View.VISIBLE);
                        connectGet(getString(R.string.url_get_pedidos_proceso) + "9", StaticData.PEDIDOS_EN_PROCESO);
                        new ChangeFragmentsTask(null).execute(StaticData.PEDIDOS_EN_PROCESO);
                        break;

                    case StaticData.HISTORIAL_DE_PEDIDO:
                        rlayLoading.setVisibility(View.VISIBLE);
                        connectGet(getString(R.string.url_get_pedido_historial)+"9",StaticData.HISTORIAL_DE_PEDIDO);
                        new ChangeFragmentsTask(null).execute(StaticData.HISTORIAL_DE_PEDIDO);
                        break;

                    default:
                        new ChangeFragmentsTask(null).execute(position);
                        break;
                }


            }else{
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
            mDrawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);
    }

    private class ChangeFragmentsTask extends AsyncTask<Integer, Integer, Boolean> {
        private Bundle bundle;
        private int position;

        public ChangeFragmentsTask(Bundle bundle) {
            this.bundle = bundle;
        }

        @Override
        protected Boolean doInBackground(Integer... itemAction) {
            position = itemAction[0];
            changeScreen(bundle, position);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            closeDrawer(position);
        }

    }

    private void changeScreen(Bundle bundle, int option) {

        switch (option){
            case StaticData.BUSCAR_MEDICAMENTO:
                changeFragment(bundle,buscarMedicamentoFragment);
                break;
            case StaticData.CARRITO_DE_COMPRAS:
                changeFragment(bundle,carritoComprasFragment);
                break;
            case StaticData.SINTOMAS_FRECUENTES:
                changeFragment(bundle,sintomasFrecuentesFragment);
                break;
            case StaticData.PEDIDOS_EN_PROCESO:
                changeFragment(bundle,pedidosProcesoFragment);
                break;
            case StaticData.HISTORIAL_DE_PEDIDO:
                changeFragment(bundle,historialPedidosFragment);
                break;
            case StaticData.ACTUALIZAR_INFORMACION:
                changeFragment(bundle, actualizarInformacionFragment);
                break;
            case  StaticData.SEARCH_RESULT:
                changeFragment(bundle,searchResultFragment);
            default:
                break;
        }
    }

    private void changeFragment(Bundle args, Fragment fragment) {
        if (args != null) fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();

    }

    private void closeDrawer(int position){
        position = position > 0 ? position-1:0;
        options.get(currentSelected).setSelected(false);
        options.get(position).setSelected(true);
        currentSelected = position;
        mDrawerLayout.closeDrawer(mDrawerList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void cerrarSesionPositive() {
        Log.i(TAG, "cerrando sesión...");
        savePreference("user", null);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void cerrarSesionNegative() {
        Log.i(TAG, "no cerras sesion");
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, int cantidad, int position) {
        PedidoInterface mListener  = searchResultFragment;
        mListener.updateItemAtPosition(position, cantidad);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void updateInformation(UserEntity user){

        userTemp = user;

        try {
            connectPost(
                    getString(R.string.url_update_user),
                    new JSONObject(new Gson().toJson(userTemp)),
                    StaticData.ACTUALIZAR_INFORMACION);

            rlayLoading.setVisibility(View.VISIBLE);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void searchMedicine(String text, String sucursal) {
        try {
            String query = URLEncoder.encode(text, "utf-8");
            connectGet(getString(R.string.url_search)+"codSucursal="+sucursal+
                    "&medicamento="+query, StaticData.SEARCH_RESULT);
            rlayLoading.setVisibility(View.VISIBLE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Problemas al realizar la búsqueda",
                    Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onRESTResultado(int code, String result, int accion) {

        HttpCode codigo = HttpCode.forValue(code);
        rlayLoading.setVisibility(View.GONE);

        switch (codigo) {
            case OK:

                switch (accion){
                    case StaticData.ACTUALIZAR_INFORMACION:
                        ResponseObject response = new Gson().fromJson(result,ResponseObject.class);
                        if(response.isSuccess()){
                            Toast.makeText(getApplicationContext(),
                                    "Sus datos se guardaron satisfactoriamente",
                                    Toast.LENGTH_SHORT).show();
                            user = userTemp;
                            savePreference("user", new Gson().toJson(user));
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    response.getMensaje(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case StaticData.SEARCH_RESULT:
                        ResponseSearch responseSearch = new Gson().fromJson(result, ResponseSearch.class);
                        if(responseSearch.isSuccess()){
                            SearchInterface mListener  = searchResultFragment;
                            mListener.getResultSearch(responseSearch.getLista());
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    responseSearch.getMensaje(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case StaticData.PEDIDOS_EN_PROCESO:

                        ResponsePedido responsePedidoPro = new Gson().fromJson(result, ResponsePedido.class);
                        if(responsePedidoPro.isSuccess()){
                            PedidosInterface mListener  = pedidosProcesoFragment;
                            mListener.getPedidosPro(responsePedidoPro.getLista());
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    responsePedidoPro.getMensaje(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        break;

                    case StaticData.HISTORIAL_DE_PEDIDO:

                        //Log.i(TAG,"[H]Result: "+result);

                        ResponsePedido responsePedidoHis = new Gson().fromJson(result, ResponsePedido.class);
                        if(responsePedidoHis.isSuccess()){
                            PedidosInterface mListener  = historialPedidosFragment;
                            mListener.getPedidosHis(responsePedidoHis.getLista());
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    responsePedidoHis.getMensaje(),
                                    Toast.LENGTH_SHORT).show();
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
    public void goToSearchResult(DireccionEntity direccion) {
        CarEntity car = new CarEntity();
        PedidoHeaderEntity header = new PedidoHeaderEntity();

        header.setCodEstadoPedido(2);
        header.setCodPedido("");
        header.setCodPersona(user.getCodPersona());
        header.setCodUsuario(user.getCodUsuario());
        header.setFechaPedido("");
        header.setHoraPedido("");
        header.setMontoTotal(0);
        header.setMontoCancelar(0);
        header.setTipoComprobante("");
        header.setRuc("");
        car.setPedido(header);

        Bundle bundle = new Bundle();
        bundle.putSerializable("car",car);

        getSupportActionBar().setTitle("Pedido para: "+direccion.getNombre());
        new ChangeFragmentsTask(bundle).execute(StaticData.SEARCH_RESULT);
    }

    @Override
    public void goToPedidoProceso(int tipoDoc, String nro) {
        //TODO Crear all to send

        switch (tipoDoc){
            case StaticData.BOLETA:
                Toast.makeText(this, "Pedido enviado", Toast.LENGTH_LONG).show();
                savePreference("car", null);
                Bundle bundle = new Bundle();
                bundle.putInt("typePedido", StaticData.PEDIDOS_EN_PROCESO);
                new ChangeFragmentsTask(bundle).execute(StaticData.PEDIDOS_EN_PROCESO);
                break;
            case StaticData.FACTURA:
                Intent intent = new Intent(this, RUCActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        String doneRUC = getPreference("doneRUC");
        if(!doneRUC.isEmpty()){
            Toast.makeText(this, "Pedido enviado", Toast.LENGTH_LONG).show();
            savePreference("car", null);
            savePreference("doneRUC",null);
            Bundle bundle = new Bundle();
            bundle.putInt("typePedido", StaticData.PEDIDOS_EN_PROCESO);
            new ChangeFragmentsTask(bundle).execute(StaticData.PEDIDOS_EN_PROCESO);
        }
    }
}

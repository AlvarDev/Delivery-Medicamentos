package com.alvardev.demos.shopmedical;

import android.app.Dialog;
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

import com.alvardev.demos.shopmedical.adapter.OptionsDashboardAdapter;
import com.alvardev.demos.shopmedical.entity.OptionEntity;
import com.alvardev.demos.shopmedical.util.CustomDialog;
import com.alvardev.demos.shopmedical.util.PedidoDialogFragment;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.alvardev.demos.shopmedical.view.fragment.ActualizarInformacionFragment;
import com.alvardev.demos.shopmedical.view.fragment.BuscarMedicamentoFragment;
import com.alvardev.demos.shopmedical.view.fragment.CarritoComprasFragment;
import com.alvardev.demos.shopmedical.view.fragment.PedidosFragment;
import com.alvardev.demos.shopmedical.view.fragment.SintomasFrecuentesFragment;

import java.util.List;

public class DashboardActivity extends BaseActionBarActivity
        implements PedidoDialogFragment.PedidoDialogListener{

    private static final String TAG = "DashboardActivity";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private List<OptionEntity> options;
    private OptionsDashboardAdapter adapter;
    private int currentSelected;

    //private SearchResultFragment searchResultFragment = SearchResultFragment.newInstance(null,null);
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
        setDrawer(savedInstanceState);
    }

    private void setDrawer(Bundle savedInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dlaOptions);
        mDrawerList = (ListView) findViewById(R.id.lviOptions);

        options = StaticData.getOptionsList();
        adapter = new OptionsDashboardAdapter(this, options);

        LinearLayout header = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_header, null);
        TextView tviUserName = (TextView)header.findViewById(R.id.tviUserName);
        tviUserName.setText("Sandy Sosa Vargas");
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
            currentSelected = StaticData.BUSCAR_MEDICAMENTO;
            //new ChangeFragmentsTask(null).execute(StaticData.SEARCH_RESULT);
        }
    }


    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position = position > 0 ? position-1:0;

            if(options.get(position).getIdOption() == StaticData.CERRAR_SESION){
                Dialog dialogOk = new CustomDialog().cerrarSesionDialog(DashboardActivity.this);
                dialogOk.show();
            }else{
                new ChangeFragmentsTask(null).execute(position);
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, int cantidad, int position) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

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
                break;
            case StaticData.SINTOMAS_FRECUENTES:
                break;
            case StaticData.PEDIDOS_EN_PROCESO:
                break;
            case StaticData.HISTORIAL_DE_PEDIDO:
                break;
            case StaticData.ACTUALIZAR_INFORMACION:
                break;
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
    protected void onRESTResultado(int code, String result, int accion) {

    }


}

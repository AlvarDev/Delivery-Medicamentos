package com.alvardev.demos.shopmedical;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.entity.DireccionEntity;
import com.alvardev.demos.shopmedical.entity.DistritoEntity;
import com.alvardev.demos.shopmedical.entity.ListDirecciones;
import com.alvardev.demos.shopmedical.entity.SucursalEntity;
import com.alvardev.demos.shopmedical.entity.response.ResponseObject;
import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.util.CustomDialog;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.alvardev.demos.shopmedical.view.fragment.BuscarMedicamentoFragment;
import com.alvardev.demos.shopmedical.view.interfaces.SucursalInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class AddDirectionActivity extends BaseActionBarActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        SucursalInterface{

    private static final String TAG = "AddDirectionActivity";
    private final int GET_DISTRITOS = 100;
    private final int GET_SUCURSAL = 101;

    //@InjectView(R.id.spiDistritos) Spinner spiDistritos;
    @InjectView(R.id.eteNameDirection) EditText eteNameDirection;
    @InjectView(R.id.eteDirection) EditText eteDirection;
    @InjectView(R.id.eteReferencia) EditText eteReferencia;
    @InjectView(R.id.btnSaveDirection) Button btnSaveDirection;
    @InjectView(R.id.searchMap) ImageButton searchMap;
    @InjectView(R.id.rlayLoading) View rlayLoading;

    private List<DistritoEntity> listaD;
    private GoogleMap mMap;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    private boolean ready;
    private boolean directionSet;
    private DireccionEntity direction;
    private int nroBusqueda;
    private static final int DURATION_LIMIT = 30 * 60;//30 minutes
    private int[] times;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_direction);
        ButterKnife.inject(this);
        directionSet = false;
        nroBusqueda = 0;
        times = new int[7];
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_root)).getMap();
        //connectGet(getString(R.string.url_get_distritos),GET_DISTRITOS);
        buildGoogleApiClient();
        setComponents();

    }

    protected synchronized void buildGoogleApiClient(){
        ready = false;
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void setComponents(){
        btnSaveDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ready) {
                    direction = new DireccionEntity();
                    direction.setNombre(eteNameDirection.getText().toString());
                    direction.setDireccion(eteDirection.getText().toString());
                    //direction.setDistrito(listaD.get(spiDistritos.getSelectedItemPosition()).getCodDistrito());
                    direction.setLatlong(mLastLocation.getLatitude() + "," + mLastLocation.getLongitude());
                    direction.setReferencia(eteReferencia.getText().toString() + "");

                    if (isDirectionOk(direction)) {
                        rlayLoading.setVisibility(View.VISIBLE);
                        SucursalEntity sucursal = StaticData.getSucursalById(nroBusqueda);
                        String url = "http://maps.googleapis.com/maps/api/directions/json?" +
                                "origin=" +sucursal.getLatlng()+"&" +
                                "destination=" +direction.getLatlong()+ "&" +
                                "mode=driving";

                        connectGet(url,GET_SUCURSAL);

                    }

                }
            }
        });


        searchMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String direction = eteDirection.getText().toString();
                    Geocoder geocoder = new Geocoder(getBaseContext());

                    if(!direction.isEmpty()){
                        List<Address>gotAddresses = geocoder.getFromLocationName(direction + " lima peru", 1);

                        if(gotAddresses.size()>0) {
                            Address address = gotAddresses.get(0);

                            Location locDir = new Location(mLastLocation);
                            locDir.setLatitude(address.getLatitude());
                            locDir.setLongitude(address.getLongitude());
                            setCurrentLocation(locDir);

                            directionSet = true;
                        }else{
                            eteDirection.setError(getString(R.string.error_field));
                            Toast.makeText(getApplicationContext(),
                                    "No se encontro direccón ",
                                    Toast.LENGTH_SHORT).show();
                            directionSet = false;
                        }
                    }else{
                        eteDirection.setError(getString(R.string.error_field));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        eteDirection.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    try {
                        String direction = eteDirection.getText().toString();
                        Geocoder geocoder = new Geocoder(getBaseContext());

                        if (!direction.isEmpty()) {
                            List<Address> gotAddresses = geocoder.getFromLocationName(direction + " lima peru", 1);

                            if (gotAddresses.size() > 0) {
                                Address address = gotAddresses.get(0);

                                Location locDir = new Location(mLastLocation);
                                locDir.setLatitude(address.getLatitude());
                                locDir.setLongitude(address.getLongitude());
                                setCurrentLocation(locDir);

                                directionSet = true;
                            } else {
                                eteDirection.setError(getString(R.string.error_field));
                                Toast.makeText(getApplicationContext(),
                                        "No se encontro dirección ",
                                        Toast.LENGTH_SHORT).show();
                                directionSet = false;
                            }
                        } else {
                            eteDirection.setError(getString(R.string.error_field));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return false;
            }
        });

        rlayLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void saveDirection(){
        String direccionesString = getPreference("direcciones");
        ListDirecciones direcciones = new ListDirecciones();
        if (!direccionesString.isEmpty()) {
            direcciones = new Gson().fromJson(direccionesString, ListDirecciones.class);
        } else {
            Log.i(TAG, "no directions");
        }

        direcciones.getDirecciones().add(direction);
        savePreference("direcciones", new Gson().toJson(direcciones));
        finish();
    }



    private boolean isDirectionOk(DireccionEntity direction){

        eteNameDirection.setError(null);
        eteDirection.setError(null);

        if(direction.getDireccion().isEmpty()){
            eteDirection.setError(getString(R.string.error_field));
            return false;
        }

        if(direction.getNombre().isEmpty()){
            eteNameDirection.setError(getString(R.string.error_field));
            return false;
        }

        if(!directionSet){
            Toast.makeText(getApplicationContext(),
                    "Por favor ingrese la dirección en el mapa",
                    Toast.LENGTH_SHORT).show();
            eteDirection.setError(getString(R.string.error_field));
            return false;
        }

        return true;
    }


    private void setListDistritos(List<DistritoEntity> listaD){
        List<String> distritos = new ArrayList<String>(listaD.size());
        for(DistritoEntity distrito : listaD){
            distritos.add(distrito.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,distritos);
        //spiDistritos.setAdapter(adapter);
    }



    @Override
    protected void onRESTResultado(int code, String result, int accion) {
        HttpCode codigo = HttpCode.forValue(code);

        switch (codigo) {
            case OK:
                ResponseObject response = new Gson().fromJson(result,ResponseObject.class);
                switch (accion){
                    case GET_DISTRITOS:
                        if(response.isSuccess()){
                            listaD = response.getLista();
                            setListDistritos(listaD);
                        }else{
                            Toast.makeText(getApplicationContext(), "Problemas al cargar distritos", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case GET_SUCURSAL:

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONObject jsonDistance = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs")
                                    .getJSONObject(0).getJSONObject("duration");

                            int time = jsonDistance.getInt("value");
                            times[nroBusqueda] = time;

                            if(nroBusqueda < 6){
                                nroBusqueda++;
                                SucursalEntity sucursal = StaticData.getSucursalById(nroBusqueda);
                                String url = "http://maps.googleapis.com/maps/api/directions/json?" +
                                        "origin=" +sucursal.getLatlng()+"&" +
                                        "destination=" +direction.getLatlong()+ "&" +
                                        "mode=driving";
                                connectGet(url,GET_SUCURSAL);
                            }else{
                                int tempTime = DURATION_LIMIT +1;
                                int indicator = 0;
                                for(int i=0; i<7;i++){
                                    Log.i(TAG,"duration: "+(times[i]/60));
                                    if(tempTime>times[i]){
                                        tempTime = times[i];
                                        indicator = i;
                                    }
                                }

                                if(tempTime<=DURATION_LIMIT){
                                    SucursalEntity finalSucursal = StaticData.getSucursalById(indicator);
                                    direction.setSucursal(finalSucursal.getCodSucursale());

                                    String msn = "Sucursal encontrada: "+finalSucursal.getDirection()+
                                            " - "+finalSucursal.getDistrito();

                                    Dialog dialogOk = new CustomDialog().showFinalSucursal(AddDirectionActivity.this,msn);
                                    dialogOk.show();

                                    //Log.i(TAG,"sucursal: "+finalSucursal.getDirection());
                                    //Log.i(TAG,"time: "+(tempTime/60));
                                }else{
                                    rlayLoading.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),
                                            "Lo sentimos, no hay sucursales cerca a su ubicación",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "error: " + e.getMessage());
                            Toast.makeText(getApplicationContext(), "Problemas al obtener sucursal", Toast.LENGTH_SHORT).show();
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
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocation != null){
            setCurrentLocation(mLastLocation);
        }else{
            Toast.makeText(getApplicationContext(), "Problemas al obtener su ubicación", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
    }

    private void setCurrentLocation(Location location){

        try{

            int zoom =  ready ? 17:13;
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(),location.getLongitude()))      // Sets the center of the map to Mountain View
                    .zoom(zoom)                   // Sets the zoom
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            if(mMap!=null){
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                if(ready){
                    mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
                            .title("Tu ubicación"));
                    mLastLocation = location;
                }
                ready = true;
            }
        }catch (Exception e){
            Log.i(TAG, "error message: " + e.getMessage());
        }
    }

    @Override
    public void closeAddDirection() {
        saveDirection();
    }
}


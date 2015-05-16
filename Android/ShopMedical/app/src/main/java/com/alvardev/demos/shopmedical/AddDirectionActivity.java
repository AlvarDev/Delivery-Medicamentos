package com.alvardev.demos.shopmedical;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.entity.DireccionEntity;
import com.alvardev.demos.shopmedical.entity.DistritoEntity;
import com.alvardev.demos.shopmedical.entity.ListDirecciones;
import com.alvardev.demos.shopmedical.entity.response.ResponseObject;
import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.alvardev.demos.shopmedical.view.fragment.BuscarMedicamentoFragment;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class AddDirectionActivity extends BaseActionBarActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "AddDirectionActivity";
    private final int GET_DISTRITOS = 100;

    @InjectView(R.id.spiDistritos) Spinner spiDistritos;
    @InjectView(R.id.eteNameDirection) EditText eteNameDirection;
    @InjectView(R.id.eteDirection) EditText eteDirection;
    @InjectView(R.id.eteReferencia) EditText eteReferencia;
    @InjectView(R.id.btnSaveDirection) Button btnSaveDirection;

    private List<DistritoEntity> listaD;
    private GoogleMap mMap;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    private boolean ready;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_direction);
        ButterKnife.inject(this);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_root)).getMap();
        connectGet(getString(R.string.url_get_distritos),GET_DISTRITOS);
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
                if(ready){
                    DireccionEntity direction = new DireccionEntity();
                    direction.setNombre(eteNameDirection.getText().toString());
                    direction.setDireccion(eteDirection.getText().toString());
                    direction.setDistrito(listaD.get(spiDistritos.getSelectedItemPosition()).getCodDistrito());
                    direction.setLatlong(mLastLocation.getLatitude()+","+mLastLocation.getLongitude());
                    direction.setReferencia(eteReferencia.getText().toString()+"");

                    if(isDirectionOk(direction)){
                        String direccionesString = getPreference("direcciones");
                        ListDirecciones direcciones = new ListDirecciones();
                        if(!direccionesString.isEmpty()){
                            direcciones = new Gson().fromJson(direccionesString, ListDirecciones.class);
                        }else{
                            Log.i(TAG, "no directions");
                        }

                        //Log.w(TAG,"direcciones: "+direccionesString);
                        //Log.i(TAG,"[]direcciones: "+direcciones.toString());

                        direcciones.getDirecciones().add(direction);
                        savePreference("direcciones", new Gson().toJson(direcciones));
                        finish();
                    }

                }


            }
        });
    }


    private boolean isDirectionOk(DireccionEntity direction){

        eteNameDirection.setError(null);
        eteDirection.setError(null);

        if(direction.getNombre().isEmpty()){
            eteNameDirection.setError(getString(R.string.error_field));
            return false;
        }

        if(direction.getDireccion().isEmpty()){
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
        spiDistritos.setAdapter(adapter);
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
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(),location.getLongitude()))      // Sets the center of the map to Mountain View
                    .zoom(17)                   // Sets the zoom
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            if(mMap!=null){
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title("Tu ubicación"));
                ready = true;
            }
        }catch (Exception e){
            Log.i(TAG, "error message: " + e.getMessage());
        }
    }

}

package com.alvardev.demos.shopmedical;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.entity.DistritoEntity;
import com.alvardev.demos.shopmedical.entity.UserEntity;
import com.alvardev.demos.shopmedical.entity.response.ResponseObject;
import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
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


public class AddDirectionActivity extends BaseActionBarActivity{

    private final int GET_DISTRITOS = 100;

    @InjectView(R.id.spiDistritos) Spinner spiDistritos;
    private List<DistritoEntity> listaD;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_direction);
        ButterKnife.inject(this);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_root)).getMap();
        connectGet(getString(R.string.url_get_distritos),GET_DISTRITOS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LatLng inicio = new LatLng(-12.0967424,-77.0526641);

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (resultCode == ConnectionResult.SUCCESS) {
            // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(inicio)      // Sets the center of the map to Mountain View
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            mMap.addMarker(new MarkerOptions()
                    .position(inicio)
                    .title("Dota2"));

        } else if (resultCode == ConnectionResult.SERVICE_MISSING ||
                resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ||
                resultCode == ConnectionResult.SERVICE_DISABLED) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1);
            dialog.show();
        }
        super.onResume();
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
                            listaD = response.getListaD();
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


}

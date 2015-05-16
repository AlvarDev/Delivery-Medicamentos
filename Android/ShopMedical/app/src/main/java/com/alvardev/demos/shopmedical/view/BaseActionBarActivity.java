package com.alvardev.demos.shopmedical.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.alvardev.demos.shopmedical.http.HttpMethod;
import com.alvardev.demos.shopmedical.http.RestJsonService;
import com.alvardev.demos.shopmedical.util.StaticData;

import org.json.JSONObject;

public abstract class BaseActionBarActivity extends ActionBarActivity {

    private boolean doingRest;
    private ResultReceiver resultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildResultReceiver();
    }

    public void buildResultReceiver(){
        resultReceiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                onRESTResultado(
                        resultCode,
                        resultData.getString(RestJsonService.LLAVE_CONTENIDO_RESPUESTA),
                        resultData.getInt(RestJsonService.LLAVE_TIPO_ACCION));
            }

        };
    }

    public void connectGet(String url, int accion){

        Intent intentServicio = new Intent(this, RestJsonService.class);

        Bundle parametros = new Bundle();

        parametros.putParcelable(RestJsonService.LLAVE_RECIBIDOR,resultReceiver);
        parametros.putInt(RestJsonService.LLAVE_METODO_PETICION,HttpMethod.GET.getValue());
        parametros.putString(RestJsonService.LLAVE_URL, url);
        parametros.putInt(RestJsonService.LLAVE_TIPO_ACCION,accion);

        intentServicio.putExtras(parametros);
        startService(intentServicio);

    }

    public void connectPost(String url, JSONObject json, int accion){

        Intent intentServicio = new Intent(this, RestJsonService.class);


        Bundle parametros = new Bundle();
        parametros.putString(RestJsonService.LLAVE_CONTENIDO_PETICION,json.toString());
        parametros.putParcelable(RestJsonService.LLAVE_RECIBIDOR,resultReceiver);
        parametros.putInt(RestJsonService.LLAVE_METODO_PETICION,HttpMethod.POST.getValue());
        parametros.putString(RestJsonService.LLAVE_URL, url);
        parametros.putInt(RestJsonService.LLAVE_TIPO_ACCION, accion);

        intentServicio.putExtras(parametros);
        startService(intentServicio);
    }


    public void connectPut(String url, JSONObject json, int accion){

        Intent intentServicio = new Intent(this, RestJsonService.class);

        Bundle parametros = new Bundle();
        parametros.putString(RestJsonService.LLAVE_CONTENIDO_PETICION, json.toString());
        parametros.putParcelable(RestJsonService.LLAVE_RECIBIDOR, resultReceiver);
        parametros.putInt(RestJsonService.LLAVE_METODO_PETICION, HttpMethod.PUT.getValue());
        parametros.putString(RestJsonService.LLAVE_URL, url);
        parametros.putInt(RestJsonService.LLAVE_TIPO_ACCION, accion);

        intentServicio.putExtras(parametros);
        startService(intentServicio);
    }

    public void connectDelete(String url, JSONObject json, int accion){

        Intent intentServicio = new Intent(this, RestJsonService.class);


        Bundle parametros = new Bundle();
        parametros.putString(RestJsonService.LLAVE_CONTENIDO_PETICION, json.toString());
        parametros.putParcelable(RestJsonService.LLAVE_RECIBIDOR, resultReceiver);
        parametros.putInt(RestJsonService.LLAVE_METODO_PETICION, HttpMethod.DELETE.getValue());
        parametros.putString(RestJsonService.LLAVE_URL, url);
        parametros.putInt(RestJsonService.LLAVE_TIPO_ACCION, accion);

        intentServicio.putExtras(parametros);
        startService(intentServicio);
    }

    public void generalError(String msn){
        Log.e("HttpClient",msn);
    }

    public String getPreference(String llave) {

        SharedPreferences preferencias = this.getSharedPreferences(StaticData.NAME_PREFERENCE, MODE_PRIVATE);
        return preferencias.getString(llave, "");
    }

    public void savePreference(String llave, String valor) {

        SharedPreferences preferencias = this.getSharedPreferences(StaticData.NAME_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(llave, valor);
        editor.apply();

    }



    protected abstract void onRESTResultado(int code, String result, int accion);

}

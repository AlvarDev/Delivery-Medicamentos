package com.alvardev.demos.shopmedical.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBarActivity;

import com.alvardev.demos.shopmedical.http.HttpMethod;
import com.alvardev.demos.shopmedical.http.RestJsonService;

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

    public void connectGet(String peticion, int accion){

        String url = "http://www.alvardev.com";

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


    protected abstract void onRESTResultado(int code, String result, int accion);

}

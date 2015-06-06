package com.alvardev.demos.shopmedical.util;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CancelarPedidoService extends IntentService {

    public static final String ACTION_FIN = "com.alvardev.demos.shopmedical.FIN";
    private static final String TAG = "CancelarPedidoService";

    public CancelarPedidoService() {
        super("CancelarPedidoService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            Log.i(TAG,"onHandleIntent");
            Thread.sleep(120 * 1000);
            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_FIN);
            sendBroadcast(bcIntent);
        } catch(InterruptedException e) {
            Log.i(TAG, "error: " + e.getMessage());
        }

    }

}

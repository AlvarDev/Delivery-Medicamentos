package com.alvardev.demos.shopmedical.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.LoginActivity;
import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.view.interfaces.DashboardInterface;
import com.alvardev.demos.shopmedical.view.interfaces.SesionDialogInterface;

public class CustomDialog {

    private static final String TAG = "CustomDialog";
    private Dialog dialog;
    private boolean done;

    public Dialog getDNIDialog(final Context context){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = View.inflate(context, R.layout.dialog_dni, null);

        final TextView eteDNI = (TextView) view.findViewById(R.id.eteDNI);
        Button btnDNI = (Button) view.findViewById(R.id.btnDNI);
        btnDNI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = eteDNI.getText().toString();
                done = dni.equals("72716164");
                if(done){
                    Toast.makeText(context, "Datos cargados correctamente", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                }else{
                    Toast.makeText(context, "Datos cargados correctamente", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setView(view);
        dialog = builder.create();

        return dialog;
    }


    public Dialog cerrarSesionDialog(final Context context){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("¿Seguro que desea cerrar sesión?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SesionDialogInterface mListener = (SesionDialogInterface) context;
                        mListener.cerrarSesionPositive();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SesionDialogInterface mListener = (SesionDialogInterface) context;
                        mListener.cerrarSesionNegative();
                    }
                });


        dialog = builder.create();

        return dialog;
    }

    public Dialog descriptionDialog(final Context context, String description){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(description)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


        dialog = builder.create();

        return dialog;
    }

    public Dialog selectDocumentDialog(final Context context){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = View.inflate(context, R.layout.layout_select_document, null);
        Button btnBoleta = (Button) view.findViewById(R.id.btnBoleta);
        Button btnFactura = (Button) view.findViewById(R.id.btnFactura);


        btnBoleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardInterface mListener = (DashboardInterface) context;
                mListener.goToPedidoProceso(StaticData.BOLETA, "");
                dialog.dismiss();
            }
        });

        btnFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardInterface mListener = (DashboardInterface) context;
                mListener.goToPedidoProceso(StaticData.FACTURA, "");
                dialog.dismiss();
            }
        });


        builder.setTitle("Escoja Comprobante de Pago");


        builder.setView(view);
        dialog = builder.create();

        return dialog;
    }





}

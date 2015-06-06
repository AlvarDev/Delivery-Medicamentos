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
import com.alvardev.demos.shopmedical.entity.CarSendEntity;
import com.alvardev.demos.shopmedical.entity.SucursalEntity;
import com.alvardev.demos.shopmedical.view.interfaces.CancelarPedidoInterface;
import com.alvardev.demos.shopmedical.view.interfaces.DashboardInterface;
import com.alvardev.demos.shopmedical.view.interfaces.RUCInterface;
import com.alvardev.demos.shopmedical.view.interfaces.SesionDialogInterface;
import com.alvardev.demos.shopmedical.view.interfaces.SucursalInterface;

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

    public Dialog selectDocumentDialog(final Context context, final double total, final double cancelar){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = View.inflate(context, R.layout.layout_select_document, null);
        Button btnBoleta = (Button) view.findViewById(R.id.btnBoleta);
        Button btnFactura = (Button) view.findViewById(R.id.btnFactura);


        btnBoleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardInterface mListener = (DashboardInterface) context;
                mListener.goToPedidoProceso(StaticData.BOLETA, total, cancelar);
                dialog.dismiss();
            }
        });

        btnFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardInterface mListener = (DashboardInterface) context;
                mListener.goToPedidoProceso(StaticData.FACTURA, total, cancelar);
                dialog.dismiss();
            }
        });


        builder.setTitle("Escoja Comprobante de Pago");


        builder.setView(view);
        dialog = builder.create();

        return dialog;
    }



    public Dialog showFinalSucursal(final Context context, String msn){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(msn)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //SucursalInterface mListener = (SucursalInterface) context;
                        //mListener.closeAddDirection();
                        dialog.dismiss();
                    }
                });


        dialog = builder.create();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                SucursalInterface mListener = (SucursalInterface) context;
                mListener.closeAddDirection();
            }
        });

        return dialog;
    }

    public Dialog showMessage(final Context context, String msn){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("¡Alerta!")
                .setMessage(msn)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialog.dismiss();
                    }
                });


        dialog = builder.create();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                RUCInterface mListener = (RUCInterface) context;
                mListener.pedidoEnviado();
            }
        });


        return dialog;
    }


    public Dialog cancelarPedidoDialog(final Context context){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("¿Seguro que desea cancelar su pedido?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CancelarPedidoInterface mListener = (CancelarPedidoInterface) context;
                        mListener.cancelarPedidoPositive();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //SesionDialogInterface mListener = (SesionDialogInterface) context;
                        //mListener.cerrarSesionNegative();
                    }
                });


        dialog = builder.create();

        return dialog;
    }




}

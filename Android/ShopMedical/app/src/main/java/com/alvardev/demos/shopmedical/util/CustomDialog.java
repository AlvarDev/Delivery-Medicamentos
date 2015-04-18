package com.alvardev.demos.shopmedical.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.R;

public class CustomDialog {

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

}

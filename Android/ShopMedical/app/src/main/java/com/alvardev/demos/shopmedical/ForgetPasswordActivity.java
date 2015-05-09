package com.alvardev.demos.shopmedical;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ForgetPasswordActivity extends BaseActionBarActivity{

    @InjectView(R.id.btnEnviarPassword) Button btnEnviarPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        setTitle("Solicitar contraseña");
        ButterKnife.inject(this);
        setComponents();
    }


    public void setComponents(){
        btnEnviarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Solicitud enviada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onRESTResultado(int code, String result, int accion) {

    }


}

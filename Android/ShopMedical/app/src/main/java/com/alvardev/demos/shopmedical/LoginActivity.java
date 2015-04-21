package com.alvardev.demos.shopmedical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends BaseActionBarActivity {

    private static final String TAG = "LoginActivity";
    @InjectView(R.id.btnRegister) Button btnRegister;
    @InjectView(R.id.btnLogin) Button btnLogin;
    @InjectView(R.id.eteUser) EditText eteUser;
    @InjectView(R.id.etePassword) EditText etePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.inject(this);
        setComponents();
//        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=-12.0967424,-77.0526472&destination=-12.0967044,-77.0526641&mode=walking";
//        connectGet(url, 100);
    }

    private void setComponents(){

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = eteUser.getText().toString();
                String pass = etePassword.getText().toString();


                if (user.equals("sandysv") && pass.equals("sistemas")) {
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext()," Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onRESTResultado(int code, String result, int accion) {

        HttpCode codigo = HttpCode.forValue(code);

        switch (codigo) {
            case OK:
                Log.i(TAG, result);
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

package com.alvardev.demos.shopmedical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends BaseActionBarActivity {

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



}

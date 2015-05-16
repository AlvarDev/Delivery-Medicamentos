package com.alvardev.demos.shopmedical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.entity.UserEntity;
import com.alvardev.demos.shopmedical.entity.request.LoginBean;
import com.alvardev.demos.shopmedical.entity.response.ResponseObject;
import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends BaseActionBarActivity {

    private static final String TAG = "LoginActivity";
    @InjectView(R.id.btnRegister) Button btnRegister;
    @InjectView(R.id.btnLogin) Button btnLogin;
    @InjectView(R.id.eteUser) EditText eteUser;
    @InjectView(R.id.etePassword) EditText etePassword;
    @InjectView(R.id.btnForgetPass) Button btnForgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.inject(this);
        setComponents();

        String userString = getPreference("user");
        if(!userString.isEmpty()){
            UserEntity user = new Gson().fromJson(userString, UserEntity.class);
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Bienvenido "+ user.getNombres(), Toast.LENGTH_SHORT).show();
            finish();
        }


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

                LoginBean login = new LoginBean(user, pass);

                try {
                    connectPost(getString(R.string.url_login), new JSONObject(new Gson().toJson(login)), 100);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginSuccess(UserEntity user){
        String userString = eteUser.getText().toString();
        user.setUsuario(userString);
        user.setCodUsuario(user.getCodPersona());
        savePreference("user", new Gson().toJson(user));
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Bienvenido "+ user.getNombres(), Toast.LENGTH_SHORT).show();
        finish();

    }

    private void loginNotSuccess(String msn){
        Toast.makeText(getApplicationContext(), msn, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onRESTResultado(int code, String result, int accion) {

        HttpCode codigo = HttpCode.forValue(code);

        switch (codigo) {
            case OK:
                try {
                    ResponseObject response = new Gson().fromJson(result,ResponseObject.class);

                    if(response.isSuccess()){
                        String tmp = new Gson().toJson(response.getObject());
                        UserEntity user = new Gson().fromJson(tmp, UserEntity.class);
                        loginSuccess(user);
                    }else{
                        loginNotSuccess(response.getMensaje());
                    }

                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
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

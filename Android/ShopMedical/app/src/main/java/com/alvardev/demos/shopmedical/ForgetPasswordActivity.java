package com.alvardev.demos.shopmedical;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ForgetPasswordActivity extends BaseActionBarActivity{

    private static final String TAG = "Forget";
    @InjectView(R.id.btnEnviarPassword) Button btnEnviarPassword;
    @InjectView(R.id.eteUserRecovery) EditText eteUserRecovery;
    @InjectView(R.id.rlayLoading) View rlayLoading;

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
                if (validareUserRecovery(eteUserRecovery.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Solicitud enviada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validareUserRecovery(String userRecovery){

        eteUserRecovery.setError(null);

        if(userRecovery.isEmpty()){
            eteUserRecovery.setError(getString(R.string.error_field));
            return false;
        }

        return true;
    }


    @Override
    protected void onRESTResultado(int code, String result, int accion) {
        HttpCode codigo = HttpCode.forValue(code);
        rlayLoading.setVisibility(View.GONE);

        switch (codigo) {
            case OK:
                try {
                    /*ResponseObject response = new Gson().fromJson(result,ResponseObject.class);

                    if(response.isSuccess()){
                        String tmp = new Gson().toJson(response.getObject());
                        UserEntity user = new Gson().fromJson(tmp, UserEntity.class);
                        loginSuccess(user);
                    }else{
                        loginNotSuccess(response.getMensaje());
                    }*/

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

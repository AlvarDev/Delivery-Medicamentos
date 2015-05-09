package com.alvardev.demos.shopmedical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.entity.UserEntity;
import com.alvardev.demos.shopmedical.entity.response.ResponseObject;
import com.alvardev.demos.shopmedical.http.HttpCode;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RegisterActivity extends BaseActionBarActivity {

    private static final String TAG = "RegisterActivity";
    //@InjectView(R.id.spiDistritos) Spinner spiDistritos;
    @InjectView(R.id.btnSaveRegister) Button btnSaveRegister;
    @InjectView(R.id.llaData) LinearLayout llaData;
    @InjectView(R.id.etedni) EditText etedni;
    @InjectView(R.id.tviName) TextView tviName;
    @InjectView(R.id.tviLastName) TextView tviLastName;

    @InjectView(R.id.eteUsuario) EditText eteUsuario;
    @InjectView(R.id.eteContra) EditText eteContra;
    //@InjectView(R.id.eteDireccion) EditText eteDireccion;
    @InjectView(R.id.eteCorreo) EditText eteCorreo;
    @InjectView(R.id.eteTelefono) EditText eteTelefono;


    //private final int GET_DISTRITOS = 100;
    private final int GET_DNI = 101;
    private final int POST_REGISTER_USER = 102;

    private UserEntity user;
    //private List<DistritoEntity> listaD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        ButterKnife.inject(this);
        setComponents();
        //connectGet(getString(R.string.url_get_distritos),GET_DISTRITOS);
    }

    private void setComponents(){

        etedni.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    String dni = etedni.getText().toString();
                    if(dni.length()==8) {
                        connectGet(getString(R.string.url_get_dni)+dni, GET_DNI);
                    }else{
                        Log.i(TAG,"Ingresa 8 digitos");
                        Toast.makeText(getApplicationContext(), "Ingresa 8 digitos", Toast.LENGTH_SHORT).show();
                    }
                }

                return false;
            }
        });

        btnSaveRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setUsuario(eteUsuario.getText().toString());
                user.setCodDistrito(7);
                user.setDireccion("");
                user.setClave(eteContra.getText().toString());
                user.setCorreo(eteCorreo.getText().toString());
                user.setCelular(eteTelefono.getText().toString());
                user.setLatlong("");
                user.setCodRol(3);

                if(validateRegister(user)){

                    try {
                        connectPost(getString(R.string.url_post_register), new JSONObject(new Gson().toJson(user)),POST_REGISTER_USER);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //private void setListDistritos(List<DistritoEntity> listaD){
        //List<String> distritos = new ArrayList<String>(listaD.size());
        //for(DistritoEntity distrito : listaD){
        //    distritos.add(distrito.getNombre());
        //}

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,distritos);
        //spiDistritos.setAdapter(adapter);
    //}

    private void setName(UserEntity user){
        tviName.setText(user.getNombres());
        tviLastName.setText(user.getApellidoPaterno()+" "+user.getApellidoMaterno());
        etedni.setEnabled(false);

        //int codDistrito = 0;

        //for(DistritoEntity distrito : listaD){
        //    if(user.getCodDistrito() == distrito.getCodDistrito()){
        //        break;
        //    }

        //    codDistrito++;

        //}

        //spiDistritos.setSelection(codDistrito);
        //eteDireccion.setText(user.getDireccion());
    }

    public boolean validateRegister(UserEntity user){

        eteUsuario.setError(null);
        eteContra.setError(null);
        //eteDireccion.setError(null);
        eteCorreo.setError(null);
        eteTelefono.setError(null);


        if(user.getUsuario().isEmpty()){
            eteUsuario.setError(getString(R.string.error_field));
            return false;
        }

        if(user.getClave().isEmpty()){
            eteContra.setError(getString(R.string.error_field));
            return false;
        }

        //if(user.getDireccion().isEmpty()){
        //    eteDireccion.setError(getString(R.string.error_field));
        //    return false;
        //}

        if(user.getCorreo().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user.getCorreo()).matches()){
            eteCorreo.setError(getString(R.string.error_field));
            return false;
        }



        if(user.getCelular().isEmpty()){
            eteTelefono.setError(getString(R.string.error_field));
            return false;
        }


        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onRESTResultado(int code, String result, int accion) {
        HttpCode codigo = HttpCode.forValue(code);

        switch (codigo) {
            case OK:
                ResponseObject response = new Gson().fromJson(result,ResponseObject.class);
                switch (accion){
                    /*case GET_DISTRITOS:
                        if(response.isSuccess()){
                            listaD = response.getListaD();
                            setListDistritos(listaD);
                        }else{
                            Toast.makeText(getApplicationContext(), "Problemas al cargar distritos", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    */
                    case GET_DNI:

                        if(response.isSuccess()){
                            String tmp = new Gson().toJson(response.getObject());
                            user = new Gson().fromJson(tmp, UserEntity.class);
                            setName(user);
                            llaData.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Datos encontrados", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Datos no encontrados", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case POST_REGISTER_USER:
                        if(response.isSuccess()){
                            Toast.makeText(getApplicationContext(), "Sus datos se guardaron satisfactoriamente", Toast.LENGTH_SHORT).show();
                            savePreference("user", new Gson().toJson(user));
                            Intent intent = new Intent(this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), response.getMensaje(), Toast.LENGTH_SHORT).show();
                        }
                        break;
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

package com.alvardev.demos.shopmedical;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.entity.UserEntity;
import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RegisterActivity extends BaseActionBarActivity {

    @InjectView(R.id.spiDistritos) Spinner spiDistritos;
    @InjectView(R.id.btnSaveRegister) Button btnSaveRegister;
    @InjectView(R.id.llaData) LinearLayout llaData;
    @InjectView(R.id.etedni) EditText etedni;
    @InjectView(R.id.tviName) TextView tviName;
    @InjectView(R.id.tviLastName) TextView tviLastName;

    @InjectView(R.id.eteUsuario) EditText eteUsuario;
    @InjectView(R.id.eteContra) EditText eteContra;
    @InjectView(R.id.eteDireccion) EditText eteDireccion;
    @InjectView(R.id.eteCorreo) EditText eteCorreo;
    @InjectView(R.id.eteTelefono) EditText eteTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        ButterKnife.inject(this);
        setComponents();
        connectGet("Contacto",100);
    }

    private void setComponents(){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.distritos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiDistritos.setAdapter(adapter);


        etedni.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    if(etedni.getText().toString().equals("72716164")){
                        Toast.makeText(getApplicationContext(), "Datos cargados correctamente", Toast.LENGTH_SHORT).show();
                        llaData.setVisibility(View.VISIBLE);
                        setName();
                    }else{
                        Toast.makeText(getApplicationContext(), "Datos no encontrados", Toast.LENGTH_SHORT).show();
                        llaData.setVisibility(View.GONE);
                    }
                }

                return false;
            }
        });

        btnSaveRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*UserEntity user = new UserEntity();
                user.setNombre(tviName.getText().toString());
                user.setApellido(tviLastName.getText().toString());
                user.setDni(etedni.getText().toString());

                user.setUsuario(eteUsuario.getText().toString());
                user.setDistritoID(spiDistritos.getSelectedItemPosition());
                user.setDireccion(eteDireccion.getText().toString());
                user.setCorreo(eteCorreo.getText().toString());
                user.setTelefono(eteTelefono.getText().toString());

                if(validateRegister(user)){
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }

    private void setName(){
        tviName.setText("Sandy Melissa");
        tviLastName.setText("Sosa Vargas");
        etedni.setEnabled(false);

        //eteUsuario.setText("sandysv");
        //eteContra.setText("sistemas");
        //spiDistritos.setSelection(4);
        //eteDireccion.setText("Av. Jorge Basadre Nro 595");
        //eteCorreo.setText("sandysv@gmail.com");
        //eteTelefono.setText("986216560");

    }

    public boolean validateRegister(UserEntity user){

        eteUsuario.setError(null);
        eteContra.setError(null);
        eteDireccion.setError(null);
        eteCorreo.setError(null);
        eteTelefono.setError(null);


        if(user.getUsuario().isEmpty()){
            eteUsuario.setError(getString(R.string.error_field));
            return false;
        }

        if(eteContra.getText().toString().isEmpty()){
            eteContra.setError(getString(R.string.error_field));
            return false;
        }

        //if(user.getDistritoID() == 0){
        //    Toast.makeText(getApplicationContext(), "Seleccione un distrito", Toast.LENGTH_SHORT).show();
        //    return false;
        //}

        //if(user.getDireccion().isEmpty()){
        //    eteDireccion.setError(getString(R.string.error_field));
        //    return false;
        //}

        if(user.getCorreo().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user.getCorreo()).matches()){
            eteCorreo.setError(getString(R.string.error_field));
            return false;
        }



        //if(user.getTelefono().isEmpty()){
        //    eteTelefono.setError(getString(R.string.error_field));
        //    return false;
        //}


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

    }

}

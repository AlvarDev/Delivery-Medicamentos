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
                Toast.makeText(getApplicationContext(), "Sus datos se guardaron correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void setName(){
        tviName.setText("Sandy Melissa");
        tviLastName.setText("Sosa Vargas");
        etedni.setEnabled(false);

        eteUsuario.setText("sandysv");
        eteContra.setText("sistemas");
        spiDistritos.setSelection(4);
        eteDireccion.setText("Av. Jorge Basadre Nro 595");
        eteCorreo.setText("sandysv@gmail.com");
        eteTelefono.setText("986216560");

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

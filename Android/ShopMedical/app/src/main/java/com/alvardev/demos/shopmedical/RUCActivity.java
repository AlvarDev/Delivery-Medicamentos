package com.alvardev.demos.shopmedical;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class RUCActivity extends BaseActionBarActivity{

    @InjectView(R.id.eteRUC) EditText eteRUC;
    @InjectView(R.id.btnGetRUC) Button btnGetRUC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruc);
        ButterKnife.inject(this);

        btnGetRUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ruc = eteRUC.getText().toString();
                if(validateRUC(ruc)){
                    savePreference("doneRUC", "done");
                    finish();
                }
            }
        });

    }

    public boolean validateRUC(String ruc){

        eteRUC.setError(null);

        if(ruc.isEmpty()){
            eteRUC.setError(getString(R.string.error_field));
            return false;
        }

        return true;
    }

    @Override
    protected void onRESTResultado(int code, String result, int accion) {

    }


}

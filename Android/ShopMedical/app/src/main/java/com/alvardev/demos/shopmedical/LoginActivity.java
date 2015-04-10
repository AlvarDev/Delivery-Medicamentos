package com.alvardev.demos.shopmedical;

import android.os.Bundle;

import com.alvardev.demos.shopmedical.view.BaseActionBarActivity;


public class LoginActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }



}

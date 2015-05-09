package com.alvardev.demos.shopmedical.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.entity.UserEntity;
import com.alvardev.demos.shopmedical.http.HttpMethod;
import com.alvardev.demos.shopmedical.http.RestJsonService;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ActualizarInformacionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String NAME_PREFERENCE = "com.alvardev.demos.shopmedical.preferences";
    private static final String TAG = "UpdateFragment";

    private String mParam1;
    private String mParam2;

    private UserEntity user;

    @InjectView(R.id.tviDNIUpdate) TextView tviDNIUpdate;
    @InjectView(R.id.tviNameUpdate) TextView tviNameUpdate;
    @InjectView(R.id.tviLastNameUpdate) TextView tviLastNameUpdate;
    @InjectView(R.id.eteUsuarioUpdate) EditText eteUsuarioUpdate;
    @InjectView(R.id.eteContraUpdate) EditText eteContraUpdate;
    @InjectView(R.id.eteCorreoUpdate) EditText eteCorreoUpdate;
    @InjectView(R.id.eteTelefonoUpdate) EditText eteTelefonoUpdate;


    public static ActualizarInformacionFragment newInstance(String param1, String param2) {
        ActualizarInformacionFragment fragment = new ActualizarInformacionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ActualizarInformacionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_actualizar_informacion, container, false);
        ButterKnife.inject(this,view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getActivity().setTitle(getString(R.string.s_update_informacion));
        String userString = getPreference("user");
        user = new Gson().fromJson(userString, UserEntity.class);

        tviDNIUpdate.setText(user.getDni());
        tviNameUpdate.setText(user.getNombres());
        tviLastNameUpdate.setText(user.getApellidoPaterno()+" "+user.getApellidoPaterno()+" "+user.getApellidoMaterno());
        eteUsuarioUpdate.setText(user.getUsuario());
        //eteContraUpdate;
        eteCorreoUpdate.setText(user.getCorreo());
        eteTelefonoUpdate.setText(user.getCelular());
    }

    public String getPreference(String llave) {

        SharedPreferences preferencias = getActivity().getSharedPreferences(NAME_PREFERENCE, Activity.MODE_PRIVATE);
        return preferencias.getString(llave, "");
    }

    public void savePreference(String llave, String valor) {

        SharedPreferences preferencias = getActivity().getSharedPreferences(NAME_PREFERENCE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(llave, valor);
        editor.apply();

    }





}

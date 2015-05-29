package com.alvardev.demos.shopmedical.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alvardev.demos.shopmedical.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SintomasFrecuentesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "sintomas";

    private String mParam1;
    private String mParam2;

    @InjectView(R.id.tempAgotamiento) Button tempAgotamiento;
    @InjectView(R.id.tempCabeza) Button tempCabeza;
    @InjectView(R.id.tempEstomago) Button tempEstomago;
    @InjectView(R.id.tempMuscular) Button tempMuscular;
    @InjectView(R.id.tempResfriado) Button tempResfriado;
    @InjectView(R.id.tempEstrenhimiento) Button tempEstrenhimiento;
    @InjectView(R.id.tempTos) Button tempTos;
    @InjectView(R.id.tempMigranha) Button tempMigranha;

    @InjectView(R.id.vieAgotamiento) View vieAgotamiento;
    @InjectView(R.id.vieCabeza) View vieCabeza;
    @InjectView(R.id.vieEstomago) View vieEstomago;
    @InjectView(R.id.vieMuscular) View vieMuscular;
    @InjectView(R.id.vieResfriado) View vieResfriado;
    @InjectView(R.id.vieEstrenhimiento) View vieEstrenhimiento;
    @InjectView(R.id.vieTos) View vieTos;
    @InjectView(R.id.vieMigranha) View vieMigranha;

    private View currentView;

    public static SintomasFrecuentesFragment newInstance(String param1, String param2) {
        SintomasFrecuentesFragment fragment = new SintomasFrecuentesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SintomasFrecuentesFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sintomas_frecuentes, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setComponents();
    }

    public void setComponents(){
        tempAgotamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentView == null){
                    vieAgotamiento.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieAgotamiento;
                }else if(currentView==vieAgotamiento){
                    vieAgotamiento.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    currentView=null;
                }else{
                    currentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    vieAgotamiento.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieAgotamiento;
                }
            }
        });

        tempCabeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentView == null){
                    vieCabeza.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieCabeza;
                }else if(currentView==vieCabeza){
                    vieCabeza.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    currentView=null;
                }else{
                    currentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    vieCabeza.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieCabeza;
                }
            }
        });

        tempEstomago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentView == null){
                    vieEstomago.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieEstomago;
                }else if(currentView==vieEstomago){
                    vieEstomago.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    currentView=null;
                }else{
                    currentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    vieEstomago.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieEstomago;
                }
            }
        });

        tempMuscular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentView == null){
                    vieMuscular.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieMuscular;
                }else if(currentView==vieMuscular){
                    vieMuscular.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    currentView=null;
                }else{
                    currentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    vieMuscular.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieMuscular;
                }
            }
        });

        tempResfriado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentView == null){
                    vieResfriado.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieResfriado;
                }else if(currentView==vieResfriado){
                    vieResfriado.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    currentView=null;
                }else{
                    currentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    vieResfriado.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieResfriado;
                }
            }
        });

        tempEstrenhimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentView == null){
                    vieEstrenhimiento.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieEstrenhimiento;
                }else if(currentView==vieEstrenhimiento){
                    vieEstrenhimiento.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    currentView=null;
                }else{
                    currentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    vieEstrenhimiento.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieEstrenhimiento;
                }
            }
        });

        tempTos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentView == null){
                    vieTos.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieTos;
                }else if(currentView==vieTos){
                    vieTos.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    currentView=null;
                }else{
                    currentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    vieTos.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieTos;
                }
            }
        });

        tempMigranha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentView == null){
                    vieMigranha.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieMigranha;
                }else if(currentView==vieMigranha){
                    vieMigranha.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    currentView=null;
                }else{
                    currentView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                    vieMigranha.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 250));
                    currentView = vieMigranha;
                }
            }
        });



    }

}

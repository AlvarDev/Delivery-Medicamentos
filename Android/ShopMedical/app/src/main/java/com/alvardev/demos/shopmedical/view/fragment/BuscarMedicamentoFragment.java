package com.alvardev.demos.shopmedical.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.alvardev.demos.shopmedical.AddDirectionActivity;
import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.entity.DireccionEntity;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.interfaces.DashboardInterface;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BuscarMedicamentoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "BuscarMedic";

    private String mParam1;
    private String mParam2;

    private DashboardInterface mListener;
    private ListDirecciones direcciones;


    @InjectView(R.id.butAddDirection) Button butAddDirection;
    @InjectView(R.id.lviDirection) ListView lviDirection;

    public static BuscarMedicamentoFragment newInstance(String param1, String param2) {
        BuscarMedicamentoFragment fragment = new BuscarMedicamentoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BuscarMedicamentoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_buscar_medicamento, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        String direccionesString = getPreference("direcciones");
        if(!direccionesString.isEmpty()){
            direcciones = new Gson().fromJson(direccionesString, ListDirecciones.class);
            showDirecciones(direcciones.getDirecciones());
        }else{
            direcciones = new ListDirecciones();
            Log.i(TAG, "no directions");
        }

        setComponents();

    }

    private void showDirecciones(List<DireccionEntity> direcciones){
        String[] directionName = new String[direcciones.size()];

        for(int i=0; i<direcciones.size();i++){
            directionName[i] = direcciones.get(i).getNombre();
        }
        lviDirection.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.row_direction, directionName));
        lviDirection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i(TAG,"pos: "+position);
            }
        });
    }


    public void setComponents(){

        butAddDirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mListener.goToSearchResult();

                    Intent intent = new Intent(getActivity(), AddDirectionActivity.class);
                    startActivity(intent);

                }
            });


    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DashboardInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DashboardInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public String getPreference(String llave) {
        SharedPreferences preferencias = getActivity().getSharedPreferences(StaticData.NAME_PREFERENCE, Activity.MODE_PRIVATE);
        return preferencias.getString(llave, "");
    }

    public void savePreference(String llave, String valor) {

        SharedPreferences preferencias = getActivity().getSharedPreferences(StaticData.NAME_PREFERENCE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(llave, valor);
        editor.apply();

    }

    public class ListDirecciones implements Serializable{
        private List<DireccionEntity> direcciones;

        public ListDirecciones() {
        }

        public List<DireccionEntity> getDirecciones() {
            return direcciones;
        }

        @Override
        public String toString() {
            return "ListDirecciones{" +
                    "direcciones=" + direcciones +
                    '}';
        }

        public void setDirecciones(List<DireccionEntity> direcciones) {
            this.direcciones = direcciones;
        }
    }


}

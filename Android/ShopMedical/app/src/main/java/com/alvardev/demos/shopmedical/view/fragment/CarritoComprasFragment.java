package com.alvardev.demos.shopmedical.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.adapter.MedicamentosAdapter;
import com.alvardev.demos.shopmedical.entity.CarEntity;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.util.CustomDialog;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.google.gson.Gson;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CarritoComprasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private MedicamentosAdapter adapter;

    @InjectView(R.id.lviItemsAdded) ListView lviItemsAdded;

    public static CarritoComprasFragment newInstance(String param1, String param2) {
        CarritoComprasFragment fragment = new CarritoComprasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CarritoComprasFragment() {
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
        View view = inflater.inflate(R.layout.fragment_carrito_compras, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setMedicines();
    }

    private void setMedicines(){
        String carString =  getPreference("car");
        if(!carString.isEmpty()){
            final CarEntity car = new Gson().fromJson(carString, CarEntity.class);
            final List<MedicamentoEntity> medicamentos = car.getMedicamentos();

            adapter =  new MedicamentosAdapter(getActivity(), medicamentos, StaticData.CARRITO_DE_COMPRAS);
            lviItemsAdded.setAdapter(adapter);
            lviItemsAdded.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                    ImageView addItem = (ImageView) view.findViewById(R.id.addCarrito);
                    addItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            medicamentos.remove(position);
                            if(medicamentos.size()==0){
                                savePreference("car", null);
                            }else{
                                car.setMedicamentos(medicamentos);
                                savePreference("car", new Gson().toJson(car));
                            }

                            adapter.notifyDataSetChanged();
                        }
                    });


                    Dialog dialogOk = new CustomDialog().descriptionDialog(getActivity(), "this is a message");
                    dialogOk.show();
                }
            });
        }
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


}

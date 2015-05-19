package com.alvardev.demos.shopmedical.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private double total;

    @InjectView(R.id.lviItemsAdded) ListView lviItemsAdded;
    @InjectView(R.id.tviTotal) TextView tviTotal;
    @InjectView(R.id.eteEfectivo) EditText eteEfectivo;
    @InjectView(R.id.eteVuelto) TextView eteVuelto;
    @InjectView(R.id.btnEnviarPedido) Button btnEnviarPedido;


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
        total = 0;
        setMedicines();
        setComponents();
    }


    private void setMedicines(){
        String carString =  getPreference("car");
        tviTotal.setText("Precio Total S/.00.00");
        if(!carString.isEmpty()){
            final CarEntity car = new Gson().fromJson(carString, CarEntity.class);
            final List<MedicamentoEntity> medicamentos = car.getMedicamentos();

            setTotal(medicamentos);
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
                            setTotal(medicamentos);
                        }

                    });

                    Dialog dialogOk = new CustomDialog().descriptionDialog(getActivity(),
                            medicamentos.get(position).getDescripcionMedicamento());
                    dialogOk.show();

                }
            });
        }
    }

    private void setTotal(List<MedicamentoEntity> medicamentos){
        total = 0;
        for(MedicamentoEntity med :  medicamentos){
            total+=med.getPrecio()*med.getCantidad();
        }
        tviTotal.setText("Precio Total S/." + total);
    }

    private void setComponents(){

        eteEfectivo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String efec = eteEfectivo.getText().toString();
                if (!efec.isEmpty()) {
                    double efectivo = Double.parseDouble(efec);
                    eteVuelto.setText("" + (efectivo - total));
                }

                return false;
            }
        });

        btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePedido(eteEfectivo.getText().toString())) {
                    Dialog dialogOk = new CustomDialog().selectDocumentDialog(getActivity());
                    dialogOk.show();
                }
            }
        });
    }

    private boolean validatePedido(String efec){

        eteEfectivo.setError(null);

        if(total == 0){
            Toast.makeText(getActivity(), "Ingrese medicamentos" , Toast.LENGTH_SHORT).show();
            return false;
        }

        if(total<25){
            Toast.makeText(getActivity(), "Monto minimo de S/. 25.00" , Toast.LENGTH_LONG).show();
            return false;
        }

        if(efec.isEmpty()){
            eteEfectivo.setError(getString(R.string.error_field));
            return false;
        }

        double efectivo = Double.parseDouble(efec);

        if(efectivo<total){
            eteEfectivo.setError(getString(R.string.s_monto_invalido));
            return false;
        }


        return true;
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

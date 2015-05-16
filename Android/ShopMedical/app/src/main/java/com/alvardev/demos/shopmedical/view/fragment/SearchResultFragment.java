package com.alvardev.demos.shopmedical.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.adapter.MedicamentosAdapter;
import com.alvardev.demos.shopmedical.entity.CarEntity;
import com.alvardev.demos.shopmedical.entity.DireccionEntity;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.util.CustomDialog;
import com.alvardev.demos.shopmedical.util.PedidoDialogFragment;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.interfaces.PedidoInterface;
import com.google.gson.Gson;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchResultFragment extends Fragment implements PedidoInterface{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SearchResultFragment";

    private String mParam1;
    private String mParam2;

    private List<MedicamentoEntity> medicamentos;
    private List<MedicamentoEntity> medicamentosMarca;
    private List<MedicamentoEntity> medicamentosGenerico;
    private MedicamentosAdapter adapter;
    private CarEntity car;

    @InjectView(R.id.lviResult) ListView lviResult;
    @InjectView(R.id.btnMarca) Button btnMarca;
    @InjectView(R.id.viewMarca) View viewMarca;
    @InjectView(R.id.btnGenerico) Button btnGenerico;
    @InjectView(R.id.viewGenerico) View viewGenerico;



    public static SearchResultFragment newInstance(String param1, String param2) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            car = (CarEntity) getArguments().getSerializable("car");
            Log.i(TAG, "car: "+car.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setResultList();
        setComponents();
    }

    private void setResultList(){
        medicamentos = StaticData.getMedicamentos();

        List<MedicamentoEntity> medicines = car.getMedicamentos();

        for(MedicamentoEntity med : medicines){
            for(int i=0; i<medicamentos.size();i++){
                if(medicamentos.get(i).getSuperId().equals(med.getSuperId())){
                    medicamentos.set(i,med);
                    break;
                }
            }
        }

        adapter =  new MedicamentosAdapter(getActivity(), medicamentos);
        lviResult.setAdapter(adapter);
        lviResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //showNoticeDialog(position);
                ImageView addItem = (ImageView) view.findViewById(R.id.addCarrito);
                addItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getCantItem(position);
                    }
                });


                Dialog dialogOk = new CustomDialog().descriptionDialog(getActivity(), "this is a message");
                dialogOk.show();


            }
        });
    }

    public void setComponents(){
        btnMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMarca.setBackgroundColor(getResources().getColor(R.color.white));
                viewGenerico.setBackgroundColor(getResources().getColor(R.color.bg_boton));
            }
        });

        btnGenerico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewGenerico.setBackgroundColor(getResources().getColor(R.color.white));
                viewMarca.setBackgroundColor(getResources().getColor(R.color.bg_boton));
            }
        });
    }


    public void getCantItem(int position) {

        Bundle bundle=new Bundle();
        bundle.putInt("position", position);

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new PedidoDialogFragment();
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(), "PedidoDialogFragment");
    }


    @Override
    public void updateItemAtPosition(int position, int cantidad) {
        this.medicamentos.get(position).setCantidad(cantidad);
        this.medicamentos.get(position).setSelected(cantidad != 0);

        String superId = this.medicamentos.get(position).getSuperId();
        List<MedicamentoEntity> medicamentos = car.getMedicamentos();
        boolean agregar = true;

        for(int i=0; i<medicamentos.size();i++){
            if(superId.equals(medicamentos.get(i).getSuperId())){
                   if(cantidad == 0){
                       medicamentos.remove(i);
                   } else{
                       medicamentos.get(i).setCantidad(cantidad);
                   }
                agregar = false;
                break;
            }
        }

        if(agregar && cantidad!=0){
            medicamentos.add(this.medicamentos.get(position));
        }

        if(medicamentos.size()==0){
            savePreference("car", null);
        }else {
            car.setMedicamentos(medicamentos);
            savePreference("car", new Gson().toJson(car));
        }
        Log.i(TAG,"car: "+car.toString());

        //lMedicamentosSelected.add(medicamentosList.get(position));
        adapter.notifyDataSetChanged();
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

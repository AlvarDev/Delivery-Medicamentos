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
import android.widget.ListView;

import com.alvardev.demos.shopmedical.DetallePedidoActivity;
import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.adapter.PedidosAdapter;
import com.alvardev.demos.shopmedical.entity.PedidoEntity;
import com.alvardev.demos.shopmedical.util.StaticData;
import com.alvardev.demos.shopmedical.view.interfaces.PedidosInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PedidosFragment extends Fragment implements PedidosInterface{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PedidosFragment";

    private String mParam1;
    private String mParam2;


    @InjectView(R.id.lviPedidos) ListView lviPedidos;


    public static PedidosFragment newInstance(String param1, String param2) {
        PedidosFragment fragment = new PedidosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PedidosFragment() {
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
        View view = inflater.inflate(R.layout.fragment_pedidos, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void getPedidosPro(final List<PedidoEntity> pedidos) {

        PedidosAdapter adapter = new PedidosAdapter(getActivity(), pedidos, StaticData.PEDIDOS_EN_PROCESO);
        lviPedidos.setAdapter(adapter);
        lviPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!pedidos.get(i).getCodPedido().equals("En proceso...")) {
                    Intent intent = new Intent(getActivity(), DetallePedidoActivity.class);
                    intent.putExtra("pedido", pedidos.get(i));
                    startActivity(intent);
                } else if(pedidos.get(i).getCodPedido().equals("En proceso...") && getPreference("send").isEmpty()) {
                    Log.i(TAG,"El pedido fue cancelado");
                }
            }
        });

    }

    @Override
    public void getPedidosHis(final List<PedidoEntity> pedidos) {
        PedidosAdapter adapter = new PedidosAdapter(getActivity(), pedidos, StaticData.HISTORIAL_DE_PEDIDO);
        lviPedidos.setAdapter(adapter);
        lviPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetallePedidoActivity.class);
                intent.putExtra("pedido", pedidos.get(i));
                startActivity(intent);
            }
        });
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

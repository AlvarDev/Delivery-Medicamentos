package com.alvardev.demos.shopmedical.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    private int typePedido;

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
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            typePedido = getArguments().getInt("typePedido");
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

        //List<PedidoEntity> pedidos = new ArrayList<PedidoEntity>();
        //switch (typePedido){
        //    case StaticData.PEDIDOS_EN_PROCESO:
                //pedidos = StaticData.getPedidosPendientes();
        //        break;
        //    case StaticData.HISTORIAL_DE_PEDIDO:
                //pedidos = StaticData.getHistorialPedido();
        //        break;
        //}

       // PedidosAdapter adapter = new PedidosAdapter(getActivity(), pedidos);
       // lviPedidos.setAdapter(adapter);

    }

    @Override
    public void getPedidosPro(List<PedidoEntity> pedidos) {

         PedidosAdapter adapter = new PedidosAdapter(getActivity(), pedidos, StaticData.PEDIDOS_EN_PROCESO);
         lviPedidos.setAdapter(adapter);
    }

    @Override
    public void getPedidosHis(List<PedidoEntity> pedidos) {

        if(pedidos==null){
            Log.i(TAG,"it's null");
        }else{
            Log.i(TAG,"it's not null");
        }


        PedidosAdapter adapter = new PedidosAdapter(getActivity(), pedidos, StaticData.HISTORIAL_DE_PEDIDO);
        lviPedidos.setAdapter(adapter);
    }

}

package com.alvardev.demos.shopmedical.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.adapter.SintomasAdapter;
import com.alvardev.demos.shopmedical.entity.MedEntity;
import com.alvardev.demos.shopmedical.view.interfaces.DashboardInterface;
import com.alvardev.demos.shopmedical.view.interfaces.SintomasInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SintomasFrecuentesFragment extends Fragment implements SintomasInterface{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "sintomas";

    private String mParam1;
    private String mParam2;

    private DashboardInterface mListener;
    private View view;

    @InjectView(R.id.lvExpSintomas) ExpandableListView lvExpSintomas;

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
        view = inflater.inflate(R.layout.fragment_sintomas_frecuentes, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.getSintomas();
    }

    @Override
    public void setSintomas(List<MedEntity> list) {

        Toast.makeText(getActivity(),
                "setting sintomas",
                Toast.LENGTH_SHORT).show();

        List<String> sintomas = new ArrayList<String>();

        HashMap<String, List<MedEntity>> map = new HashMap<String, List<MedEntity>>();

        for(MedEntity med : list){
            String temp = med.getSintoma().trim();
            if(map.containsKey(temp)){
                map.get(temp).add(med);
            }else{
                List<MedEntity> listaSub = new ArrayList<MedEntity>();
                listaSub.add(med);
                map.put(temp, listaSub);
                sintomas.add(temp);
            }

        }

        SintomasAdapter listAdapter = new SintomasAdapter(getActivity(), sintomas, map);
        lvExpSintomas.setAdapter(listAdapter);

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


}

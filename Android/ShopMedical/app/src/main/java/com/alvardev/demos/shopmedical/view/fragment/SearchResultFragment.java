package com.alvardev.demos.shopmedical.view.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.adapter.MedicamentosAdapter;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.util.PedidoDialogFragment;
import com.alvardev.demos.shopmedical.util.StaticData;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchResultFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private List<MedicamentoEntity> medicamentos;
    private MedicamentosAdapter adapter;

    @InjectView(R.id.lviResult) ListView lviResult;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setResultList();
    }

    private void setResultList(){
        medicamentos = StaticData.getMedicamentos();
        adapter =  new MedicamentosAdapter(getActivity(), medicamentos);
        lviResult.setAdapter(adapter);
        lviResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showNoticeDialog(position);
            }
        });
    }

    public void showNoticeDialog(int position) {

        Bundle bundle=new Bundle();
        bundle.putInt("position", position);

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new PedidoDialogFragment();
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(), "PedidoDialogFragment");
    }

    private void updateItemAtPosition(int position, int cantidad) {
        medicamentos.get(position).setCantidad(cantidad);
        medicamentos.get(position).setSelected(true);
        //lMedicamentosSelected.add(medicamentosList.get(position));
        adapter.notifyDataSetChanged();
    }

}

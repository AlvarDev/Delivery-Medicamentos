package com.alvardev.demos.shopmedical.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.util.StaticData;

import java.util.List;

public class MedicamentosAdapter extends BaseAdapter {

    private Context context;
    private List<MedicamentoEntity> medicamentos;
    private int state;

    public MedicamentosAdapter(Context context, List<MedicamentoEntity> medicamentos, int state) {
        this.context = context;
        this.medicamentos = medicamentos;
        this.state = state;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView carrito;
        TextView nombre;
        TextView precio;
        TextView cantidad;
        TextView presentacion;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_medicamento, null);
            holder = new ViewHolder();
            holder.carrito = (ImageView) convertView.findViewById(R.id.addCarrito);
            holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.cantidad = (TextView) convertView.findViewById(R.id.cantidad);
            holder.precio = (TextView) convertView.findViewById(R.id.precio);
            holder.presentacion = (TextView) convertView.findViewById(R.id.presentacion);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        MedicamentoEntity medicamento = (MedicamentoEntity) getItem(position);


        holder.nombre.setText(medicamento.getMedicamentoxUnidad());

        if(medicamento.isSelected()){
            holder.nombre.setTextColor(context.getResources().getColor(R.color.verde));
            holder.cantidad.setTextColor(context.getResources().getColor(R.color.verde));
            if(state == StaticData.CARRITO_DE_COMPRAS) {
                holder.carrito.setImageResource(R.drawable.cancel);
            }else{
                holder.carrito.setImageResource(R.drawable.added);
            }

        }else{
            holder.nombre.setTextColor(context.getResources().getColor(R.color.txt_color));
            holder.cantidad.setTextColor(context.getResources().getColor(R.color.txt_color));
            holder.carrito.setImageResource(R.drawable.add);
        }

        /*switch (state){
            case StaticData.SEARCH_RESULT:

                break;
            case StaticData.CARRITO_DE_COMPRAS:

                break;
        }*/


        holder.cantidad.setText(""+medicamento.getCantidad());
        holder.precio.setText("S/. "+medicamento.getPrecio());
        holder.presentacion.setText(medicamento.getMedicamentoxPresentacion()+"");


        return convertView;
    }




    @Override
    public int getCount() {
        return medicamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return medicamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return medicamentos.indexOf(getItem(position));
    }


}
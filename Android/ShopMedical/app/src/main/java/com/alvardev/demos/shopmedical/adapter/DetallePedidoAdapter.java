package com.alvardev.demos.shopmedical.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.entity.DetallePedidoEntity;
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.util.StaticData;

import java.util.List;

public class DetallePedidoAdapter extends BaseAdapter {

    private Context context;
    private List<DetallePedidoEntity> detalles;

    public DetallePedidoAdapter(Context context, List<DetallePedidoEntity> detalles) {
        this.context = context;
        this.detalles = detalles;
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

        DetallePedidoEntity detalle = (DetallePedidoEntity) getItem(position);

        holder.nombre.setTextColor(context.getResources().getColor(R.color.txt_color));
        holder.cantidad.setTextColor(context.getResources().getColor(R.color.txt_color));
        holder.presentacion.setTextColor(context.getResources().getColor(R.color.txt_color));

        holder.nombre.setText(detalle.getMedicamentoxUnidad());
        holder.cantidad.setText(""+detalle.getCantidad());
        holder.precio.setText("S/. "+detalle.getPrecioUnitario());
        holder.presentacion.setText(detalle.getMedicamentoxPresentacion()+"");
        holder.carrito.setVisibility(View.GONE);


        return convertView;
    }




    @Override
    public int getCount() {
        return detalles.size();
    }

    @Override
    public Object getItem(int position) {
        return detalles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return detalles.indexOf(getItem(position));
    }


}
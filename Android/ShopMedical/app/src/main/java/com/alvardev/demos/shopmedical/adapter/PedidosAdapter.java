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
import com.alvardev.demos.shopmedical.entity.MedicamentoEntity;
import com.alvardev.demos.shopmedical.entity.PedidoEntity;
import com.alvardev.demos.shopmedical.util.StaticData;

import java.util.List;

public class PedidosAdapter extends BaseAdapter {

    private Context context;
    private List<PedidoEntity> pedidos;
    private int tipoPedido;

    public PedidosAdapter(Context context, List<PedidoEntity> pedidos, int tipoPedido) {
        this.context = context;
        this.pedidos = pedidos;
        this.tipoPedido = tipoPedido;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imagePedido;
        TextView nro;
        TextView fecha;
        TextView tipoDoc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_pedido, null);
            holder = new ViewHolder();
            holder.imagePedido = (ImageView) convertView.findViewById(R.id.imagePedido);
            holder.nro = (TextView) convertView.findViewById(R.id.nro);
            holder.fecha= (TextView) convertView.findViewById(R.id.fecha);
            holder.tipoDoc = (TextView) convertView.findViewById(R.id.tipoDoc);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        PedidoEntity pedido = (PedidoEntity) getItem(position);


        holder.nro.setText("Nro Pedido "+pedido.getCodPedido());
        holder.fecha.setText(pedido.getFechaPedido()+" "+pedido.getHoraPedido());


        switch (tipoPedido){
            case StaticData.PEDIDOS_EN_PROCESO:
                holder.imagePedido.setImageResource(R.drawable.next);
                holder.tipoDoc.setText("Comprobante de Pago: " + pedido.getTipoComprobante());
                holder.tipoDoc.setTextColor(context.getResources().getColor(R.color.amarillo));
                break;
            case StaticData.HISTORIAL_DE_PEDIDO:
                holder.imagePedido.setImageResource(R.drawable.next);

                if(pedido.getCodEstadoPedido() == StaticData.TERMINADO){
                    holder.tipoDoc.setText("Estado: "+pedido.getDescripcion());
                    holder.tipoDoc.setTextColor(context.getResources().getColor(R.color.amarillo));
                }else{
                    holder.tipoDoc.setText("Estado: "+pedido.getDescripcion());
                    holder.tipoDoc.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                break;
        }


        return convertView;
    }




    @Override
    public int getCount() {
        return pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pedidos.indexOf(getItem(position));
    }


}
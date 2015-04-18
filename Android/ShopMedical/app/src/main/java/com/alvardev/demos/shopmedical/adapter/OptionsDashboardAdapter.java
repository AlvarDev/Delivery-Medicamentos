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
import com.alvardev.demos.shopmedical.entity.OptionEntity;

import java.util.List;

public class OptionsDashboardAdapter extends BaseAdapter{

    Context context;
    List<OptionEntity> options;
    int indicator;

    public OptionsDashboardAdapter(Context context, List<OptionEntity> options) {
        this.context = context;
        this.options = options;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView iviOptionImage;
        TextView tviOptionText;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.row_option, null);
            holder.iviOptionImage = (ImageView) convertView.findViewById(R.id.iviOptionImage);
            holder.tviOptionText = (TextView) convertView.findViewById(R.id.tviOptionText);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        OptionEntity option = (OptionEntity) getItem(position);

        holder.iviOptionImage.setImageResource(option.getImage());
        holder.tviOptionText.setText(option.getText());

        if(option.isSelected()){
            holder.tviOptionText.setTextColor(context.getResources().getColor(R.color.bg_boton));
        }else {
            holder.tviOptionText.setTextColor(context.getResources().getColor(R.color.txt_color));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int position) {
        return options.get(position);
    }

    @Override
    public long getItemId(int position) {
        return options.indexOf(getItem(position));
    }

}



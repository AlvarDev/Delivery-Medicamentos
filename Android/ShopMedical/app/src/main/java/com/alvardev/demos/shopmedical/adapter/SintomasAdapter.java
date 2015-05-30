package com.alvardev.demos.shopmedical.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.alvardev.demos.shopmedical.R;
import com.alvardev.demos.shopmedical.entity.MedEntity;

import java.util.HashMap;
import java.util.List;

public class SintomasAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> sintomas; // header titles
    private HashMap<String, List<MedEntity>> rows;

    public SintomasAdapter(Context context, List<String> sintomas,
                                 HashMap<String, List<MedEntity>> rows) {
        this.context = context;
        this.sintomas = sintomas;
        this.rows= rows;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.rows.get(this.sintomas.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final MedEntity med = (MedEntity) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_row_sintoma, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.tviRowSintoma);

        txtListChild.setText(med.getNombreMedicamento());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.rows.get(this.sintomas.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.sintomas.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.sintomas.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_header_sintoma, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.tviSintoma);
        //lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}

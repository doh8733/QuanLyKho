package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.quannm18.quanlykho.Model.Category;
import com.quannm18.quanlykho.R;

import java.util.List;

public class CatergoryAdapterSpn extends ArrayAdapter<Category> {
    private TextView tvNameItem;
    private ImageView imgIcon;
    private TextView tvItemSelected;
    private ImageView imgDropDown;
    public CatergoryAdapterSpn(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);




        imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        tvItemSelected = (TextView) convertView.findViewById(R.id.tvItemSelected);
        imgDropDown = (ImageView) convertView.findViewById(R.id.imgDropDown);

        Category category = this.getItem(position);
        if (category !=null){
            tvItemSelected.setText(category.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.imtem_category_spn,parent,false);


        tvNameItem = (TextView) convertView.findViewById(R.id.tvNameItem);
        Category category = this.getItem(position);
        if (category !=null){
            tvNameItem.setText(category.getName());
        }
        return convertView;
    }
}

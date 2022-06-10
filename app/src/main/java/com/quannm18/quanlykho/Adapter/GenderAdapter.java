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

import com.quannm18.quanlykho.R;

import java.util.List;

public class GenderAdapter extends ArrayAdapter<String> {
    private TextView tvGender;

    public GenderAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gender,parent,false);
        tvGender = (TextView) convertView.findViewById(R.id.tvGender);
        String gender = this.getItem(position);
        if (gender !=null){
            tvGender.setText(gender);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gender,parent,false);
        tvGender = (TextView) convertView.findViewById(R.id.tvGender);
        String gender = this.getItem(position);
        if (gender !=null){
            tvGender.setText(gender);
        }
        return convertView;
    }
}

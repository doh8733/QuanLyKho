package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.Model.HoaDonXuat;
import com.quannm18.quanlykho.R;

import java.util.List;

public class FragHDX_Adapter extends RecyclerView.Adapter<FragHDX_Adapter.FragHDXHolder> {
    private Context context;
    List<HoaDonXuat> listHDX;

    public FragHDX_Adapter(Context context, List<HoaDonXuat> listHDX) {
        this.context = context;
        this.listHDX = listHDX;
    }

    @NonNull
    @Override
    public FragHDX_Adapter.FragHDXHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_bill_outlet, parent, false);
        return new FragHDXHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragHDX_Adapter.FragHDXHolder holder, int position) {
        HoaDonXuat hoaDonXuat=listHDX.get(position);

    }

    @Override
    public int getItemCount() {
        return listHDX.size();
    }

    public class FragHDXHolder extends RecyclerView.ViewHolder {
        TextView hdx_txt_Time, hdx_txt_product,hdx_txt_quantity,
                hdx_txt_free, hdx_txt_descriptions;

        public FragHDXHolder(@NonNull View itemView) {
            super(itemView);
            hdx_txt_Time = itemView.findViewById(R.id.hdx_txt_Time);
            hdx_txt_product = itemView.findViewById(R.id.hdx_txt_product);
            hdx_txt_quantity = itemView.findViewById(R.id.hdx_txt_quantity);
            hdx_txt_free = itemView.findViewById(R.id.hdx_txt_free);
            hdx_txt_descriptions = itemView.findViewById(R.id.hdx_txt_descriptions);


        }
    }
}

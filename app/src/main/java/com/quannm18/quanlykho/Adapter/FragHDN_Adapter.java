package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.animation.content.Content;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.R;

import java.util.List;

public class FragHDN_Adapter extends RecyclerView.Adapter<FragHDN_Adapter.FragHDNHolder> {
    private Context context;
    List<HoaDonNhap> listHDN;

    public FragHDN_Adapter(Context context, List<HoaDonNhap> listHDN) {
        this.context = context;
        this.listHDN = listHDN;
    }

    @NonNull
    @Override
    public FragHDN_Adapter.FragHDNHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_bill_entry, parent, false);
        return new FragHDNHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragHDN_Adapter.FragHDNHolder holder, int position) {
        HoaDonNhap hoaDonNhap = listHDN.get(position);
        holder.hdn_txt_product.setText("Product: "+hoaDonNhap.getTenSP());
        holder.hdn_txt_quantity.setText("Quatity: "+hoaDonNhap.getSoluong());
        holder.hdn_txt_free.setText("Free: "+hoaDonNhap.getGia());
    }

    @Override
    public int getItemCount() {
        return listHDN == null ? 0 : listHDN.size();
    }

    public class FragHDNHolder extends RecyclerView.ViewHolder {
        TextView hdn_txt_Time, hdn_txt_product, hdn_txt_quantity,
                hdn_txt_free, hdn_txt_descriptions;

        public FragHDNHolder(@NonNull View itemView) {
            super(itemView);
            hdn_txt_Time = itemView.findViewById(R.id.hdn_txt_Time);
            hdn_txt_product = itemView.findViewById(R.id.hdn_txt_product);
            hdn_txt_quantity = itemView.findViewById(R.id.hdn_txt_quantity);
            hdn_txt_free = itemView.findViewById(R.id.hdn_txt_free);
            hdn_txt_descriptions = itemView.findViewById(R.id.hdn_txt_descriptions);

        }
    }
}

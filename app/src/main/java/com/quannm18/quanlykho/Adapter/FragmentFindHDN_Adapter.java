package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.Model.HoaDonXuat;
import com.quannm18.quanlykho.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFindHDN_Adapter extends RecyclerView.Adapter<FragmentFindHDN_Adapter.FragHDNHolder> {
    private Context context;
    List<HoaDonNhap> listHDN;
    HoaDonNhap hoaDonNhap;
    public FragmentFindHDN_Adapter(Context context, List<HoaDonNhap> listHDN) {
        this.context = context;
        this.listHDN = listHDN;
    }

    public void setDataHDN(List<HoaDonNhap> listHDN) {
        this.listHDN = listHDN;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FragmentFindHDN_Adapter.FragHDNHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_bill_entry, parent, false);
        return new FragHDNHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentFindHDN_Adapter.FragHDNHolder holder, int position) {
        hoaDonNhap = listHDN.get(position);
        holder.hdn_txt_maHDN.setText( hoaDonNhap.getMaHoaDonNhap());
        holder.hdn_txt_product.setText( hoaDonNhap.getTenSP());
        holder.hdn_txt_product_type.setText( hoaDonNhap.getLoaiSP());
        holder.hdn_txt_hang.setText( hoaDonNhap.getHang());
        holder.hdn_txt_cot.setText( hoaDonNhap.getCot());
        holder.hdn_txt_vitri.setText(hoaDonNhap.getViTri());
        holder.hdn_txt_quantity.setText( String.valueOf(hoaDonNhap.getSoLuong()));
        holder.hdn_txt_ngayNhap.setText(hoaDonNhap.getNgayNhap());
        holder.hdn_txt_free.setText(String.valueOf(hoaDonNhap.getDonGia()));
        holder.hdn_txt_descriptions.setText(hoaDonNhap.getMoTa());


    }

    @Override
    public int getItemCount() {
        return listHDN == null ? 0 : listHDN.size();
    }

    public class FragHDNHolder extends RecyclerView.ViewHolder {
        TextView hdn_txt_maHDN,hdn_txt_product, hdn_txt_product_type, hdn_txt_hang, hdn_txt_cot, hdn_txt_vitri, hdn_txt_quantity, hdn_txt_ngayNhap,
                hdn_txt_free, hdn_txt_descriptions;
        CardView cv_hdn_CT;

        public FragHDNHolder(@NonNull View itemView) {
            super(itemView);
            hdn_txt_maHDN = itemView.findViewById(R.id.hdn_txt_maHDN);
            hdn_txt_product = itemView.findViewById(R.id.hdn_txt_product);
            hdn_txt_product_type = itemView.findViewById(R.id.hdn_txt_product_type);
            hdn_txt_hang = itemView.findViewById(R.id.hdn_txt_hang);
            hdn_txt_cot = itemView.findViewById(R.id.hdn_txt_cot);
            hdn_txt_vitri = itemView.findViewById(R.id.hdn_txt_vitri);
            hdn_txt_quantity = itemView.findViewById(R.id.hdn_txt_quantity);
            hdn_txt_ngayNhap = itemView.findViewById(R.id.hdn_txt_ngayNhap);
            hdn_txt_free = itemView.findViewById(R.id.hdn_txt_free);
            hdn_txt_descriptions = itemView.findViewById(R.id.hdn_txt_descriptions);
            cv_hdn_CT = itemView.findViewById(R.id.cv_hdn_CT);
        }
    }


}

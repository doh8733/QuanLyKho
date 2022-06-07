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
    public void setDataHDX(List<HoaDonXuat> listHDX) {
        this.listHDX = listHDX;
        notifyDataSetChanged();
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
        holder.hdx_txt_ngayNhap.setText("Ngay nhap: " + hoaDonXuat.getNgayNhap());
        holder.hdx_txt_ngayXuat.setText("Ngay xuat: " + hoaDonXuat.getNgayXuat());
        holder.hdx_txt_thanhtien.setText("Thanh tien: " + hoaDonXuat.getThanhTien());
        holder.hdx_txt_trangthai.setText("Trang thai: " + hoaDonXuat.getTrangThai());
        holder.hdx_txt_descriptions.setText("Mo ta: " + hoaDonXuat.getMoTa());
    }

    @Override
    public int getItemCount() {
        return listHDX.size();
    }

    public class FragHDXHolder extends RecyclerView.ViewHolder {
        TextView hdx_txt_ngayNhap, hdx_txt_ngayXuat,hdx_txt_thanhtien,
                hdx_txt_trangthai, hdx_txt_descriptions;

        public FragHDXHolder(@NonNull View itemView) {
            super(itemView);
            hdx_txt_ngayNhap = itemView.findViewById(R.id.hdx_txt_ngayNhap);
            hdx_txt_ngayXuat = itemView.findViewById(R.id.hdx_txt_ngayXuat);
            hdx_txt_thanhtien = itemView.findViewById(R.id.hdx_txt_thanhtien);
            hdx_txt_trangthai = itemView.findViewById(R.id.hdx_txt_trangthai);
            hdx_txt_descriptions = itemView.findViewById(R.id.hdx_txt_descriptions);


        }
    }
}

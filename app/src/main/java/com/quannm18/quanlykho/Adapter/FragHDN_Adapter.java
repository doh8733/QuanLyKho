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

    public void setDataHDN(List<HoaDonNhap> listHDN) {
        this.listHDN = listHDN;
        notifyDataSetChanged();
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
        holder.hdn_txt_product.setText("Product name: " + hoaDonNhap.getTenSP());
        holder.hdn_txt_product_type.setText("Product type: " + hoaDonNhap.getLoaiSP());
        holder.hdn_txt_hang.setText("Row: " + hoaDonNhap.getHang());
        holder.hdn_txt_cot.setText("Column: " + hoaDonNhap.getCot());
        holder.hdn_txt_vitri.setText("Position: " + hoaDonNhap.getViTri());
        holder.hdn_txt_quantity.setText("Amount: " + hoaDonNhap.getSoLuong());
        holder.hdn_txt_ngayNhap.setText("Ngay nhap: " + hoaDonNhap.getNgayNhap());
        holder.hdn_txt_free.setText("Price: " + hoaDonNhap.getDonGia()+ "$");
        holder.hdn_txt_descriptions.setText("Descriptions: "+hoaDonNhap.getMoTa());
    }

    @Override
    public int getItemCount() {
        return listHDN == null ? 0 : listHDN.size();
    }

    public class FragHDNHolder extends RecyclerView.ViewHolder {
        TextView hdn_txt_product, hdn_txt_product_type, hdn_txt_hang, hdn_txt_cot, hdn_txt_vitri, hdn_txt_quantity, hdn_txt_ngayNhap,
                hdn_txt_free, hdn_txt_descriptions;

        public FragHDNHolder(@NonNull View itemView) {
            super(itemView);
            hdn_txt_product = itemView.findViewById(R.id.hdn_txt_product);
            hdn_txt_product_type = itemView.findViewById(R.id.hdn_txt_product_type);
            hdn_txt_hang = itemView.findViewById(R.id.hdn_txt_hang);
            hdn_txt_cot = itemView.findViewById(R.id.hdn_txt_cot);
            hdn_txt_vitri = itemView.findViewById(R.id.hdn_txt_vitri);
            hdn_txt_quantity = itemView.findViewById(R.id.hdn_txt_quantity);
            hdn_txt_ngayNhap = itemView.findViewById(R.id.hdn_txt_ngayNhap);
            hdn_txt_free = itemView.findViewById(R.id.hdn_txt_free);
            hdn_txt_descriptions = itemView.findViewById(R.id.hdn_txt_descriptions);

        }
    }
}

package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
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
        holder.hdn_txt_product.setText( hoaDonNhap.getTenSP());
        holder.hdn_txt_product_type.setText( hoaDonNhap.getLoaiSP());
        holder.hdn_txt_hang.setText( hoaDonNhap.getHang());
        holder.hdn_txt_cot.setText( hoaDonNhap.getCot());
        holder.hdn_txt_vitri.setText(hoaDonNhap.getViTri());
        holder.hdn_txt_quantity.setText( String.valueOf(hoaDonNhap.getSoLuong()));
        holder.hdn_txt_ngayNhap.setText(hoaDonNhap.getNgayNhap());
        holder.hdn_txt_free.setText(String.valueOf(hoaDonNhap.getDonGia()));
        holder.hdn_txt_descriptions.setText(hoaDonNhap.getMoTa());

        holder.cv_hdn_CT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.custom_bill_entry_chitiet, null);
                builder.setView(view1);
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                String LoaiSP=holder.hdn_txt_product_type.getText().toString();
                String Hang=holder.hdn_txt_hang.getText().toString();
                String Cot=holder.hdn_txt_cot.getText().toString();
                String ViTri=holder.hdn_txt_vitri.getText().toString();
                String TenSP=holder.hdn_txt_product.getText().toString();
                int SoLuong= Integer.parseInt(holder.hdn_txt_quantity.getText().toString());
                String NgayNhap=holder.hdn_txt_ngayNhap.getText().toString();
                int DonGia= Integer.parseInt(holder.hdn_txt_free.getText().toString());
                String moTa=holder.hdn_txt_descriptions.getText().toString();

                TextView hdn_txt_product_CT = view1.findViewById(R.id.hdn_txt_product_CT);
                TextView hdn_txt_product_type_CT = view1.findViewById(R.id.hdn_txt_product_type_CT);
                TextView hdn_txt_hang_CT = view1.findViewById(R.id.hdn_txt_hang_CT);
                TextView hdn_txt_cot_CT = view1.findViewById(R.id.hdn_txt_cot_CT);
                TextView hdn_txt_vitri_CT = view1.findViewById(R.id.hdn_txt_vitri_CT);
                TextView hdn_txt_quantity_CT = view1.findViewById(R.id.hdn_txt_quantity_CT);
                TextView hdn_txt_ngayNhap_CT = view1.findViewById(R.id.hdn_txt_ngayNhap_CT);
                TextView hdn_txt_free_CT = view1.findViewById(R.id.hdn_txt_free_CT);
                TextView hdn_txt_descriptions_CT = view1.findViewById(R.id.hdn_txt_descriptions_CT);

                AppCompatButton hdn_btn_close_CT = view1.findViewById(R.id.hdn_btn_close_CT);

                hdn_txt_product_CT.setText(TenSP);
                hdn_txt_product_type_CT.setText(LoaiSP);
                hdn_txt_hang_CT.setText(Hang);
                hdn_txt_cot_CT.setText(Cot);
                hdn_txt_vitri_CT.setText(ViTri);
                hdn_txt_ngayNhap_CT.setText(NgayNhap);
                hdn_txt_quantity_CT.setText(SoLuong+"");
                hdn_txt_free_CT.setText(DonGia+"");
                hdn_txt_descriptions_CT.setText(moTa);


                hdn_btn_close_CT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



            }
        });
    }

    @Override
    public int getItemCount() {
        return listHDN == null ? 0 : listHDN.size();
    }

    public class FragHDNHolder extends RecyclerView.ViewHolder {
        TextView hdn_txt_product, hdn_txt_product_type, hdn_txt_hang, hdn_txt_cot, hdn_txt_vitri, hdn_txt_quantity, hdn_txt_ngayNhap,
                hdn_txt_free, hdn_txt_descriptions;
        CardView cv_hdn_CT;

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
            cv_hdn_CT = itemView.findViewById(R.id.cv_hdn_CT);

        }
    }
}

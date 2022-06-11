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
import com.quannm18.quanlykho.Model.HoaDonXuat;
import com.quannm18.quanlykho.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFindHDX_Adapter extends RecyclerView.Adapter<FragmentFindHDX_Adapter.FragHDXHolder> {
    private Context context;
    List<HoaDonXuat> listHDX;
    HoaDonXuat hoaDonXuat;

    public FragmentFindHDX_Adapter(Context context, List<HoaDonXuat> listHDX) {
        this.context = context;
        this.listHDX = listHDX;
    }

    public void setDataHDX(List<HoaDonXuat> listHDX) {
        this.listHDX = listHDX;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FragmentFindHDX_Adapter.FragHDXHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_bill_outlet, parent, false);
        return new FragHDXHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentFindHDX_Adapter.FragHDXHolder holder, int position) {
        hoaDonXuat = listHDX.get(position);
        holder.hdx_txt_maHDX.setText(hoaDonXuat.getMaHDX());
        holder.hdx_txt_ngayNhap.setText(hoaDonXuat.getNgayNhap());
        holder.hdx_txt_ngayXuat.setText(hoaDonXuat.getNgayXuat());
        holder.hdx_txt_thanhtien.setText(hoaDonXuat.getThanhTien() + "");
        holder.hdx_txt_trangthai.setText(hoaDonXuat.getTrangThai());
        holder.hdx_txt_descriptions.setText(hoaDonXuat.getMoTa());



    }

    @Override
    public int getItemCount() {
        return listHDX.size();
    }

    public class FragHDXHolder extends RecyclerView.ViewHolder {
        TextView hdx_tv_maHDX_CT, hdx_txt_maHDX, hdx_txt_ngayNhap, hdx_txt_ngayXuat, hdx_txt_thanhtien,
                hdx_txt_trangthai, hdx_txt_descriptions;
        CardView cv_hdx_CT;

        public FragHDXHolder(@NonNull View itemView) {
            super(itemView);
            hdx_tv_maHDX_CT = itemView.findViewById(R.id.hdx_tv_maHDX_CT);
            hdx_txt_maHDX = itemView.findViewById(R.id.hdx_txt_maHDX);
//            hdx_txt_ngayNhap = itemView.findViewById(R.id.hdx_txt_ngayNhap);
            hdx_txt_ngayXuat = itemView.findViewById(R.id.hdx_txt_ngayXuat);
            hdx_txt_thanhtien = itemView.findViewById(R.id.hdx_txt_thanhtien);
            hdx_txt_trangthai = itemView.findViewById(R.id.hdx_txt_trangthai);
            hdx_txt_descriptions = itemView.findViewById(R.id.hdx_txt_descriptions);
            cv_hdx_CT = itemView.findViewById(R.id.cv_hdx_CT);


        }
    }

    private void deleteHDX() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ApiInterface.ApiInterface.deleteHDX(hoaDonXuat.getId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("TAG", "onResponse: "+response);
                        notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

package com.quannm18.quanlykho.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.Model.HoaDonXuat;
import com.quannm18.quanlykho.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragHDN_Adapter extends RecyclerView.Adapter<FragHDN_Adapter.FragHDNHolder> {
    private Context context;
    List<HoaDonNhap> listHDN;
    HoaDonNhap hoaDonNhap;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
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
        hoaDonNhap = listHDN.get(position);

        String datatime=format.format(calendar.getTime());


        holder.hdn_txt_maHDN.setText( hoaDonNhap.getMaHoaDonNhap());
        holder.hdn_txt_product.setText( hoaDonNhap.getTenSP());
        holder.hdn_txt_product_type.setText( hoaDonNhap.getLoaiSP());
        holder.hdn_txt_hang.setText( hoaDonNhap.getHang());
        holder.hdn_txt_cot.setText( hoaDonNhap.getCot());
        holder.hdn_txt_vitri.setText(hoaDonNhap.getViTri());
        holder.hdn_txt_quantity.setText( String.valueOf(hoaDonNhap.getSoLuong()));
        holder.hdn_txt_ngayNhap.setText(String.format(datatime, hoaDonNhap.getNgayNhap()));
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
                String maHDN=holder.hdn_txt_maHDN.getText().toString();
                String LoaiSP=holder.hdn_txt_product_type.getText().toString();
                String Hang=holder.hdn_txt_hang.getText().toString();
                String Cot=holder.hdn_txt_cot.getText().toString();
                String ViTri=holder.hdn_txt_vitri.getText().toString();
                String TenSP=holder.hdn_txt_product.getText().toString();
                int SoLuong= Integer.parseInt(holder.hdn_txt_quantity.getText().toString());
                String NgayNhap=holder.hdn_txt_ngayNhap.getText().toString();
                int DonGia= Integer.parseInt(holder.hdn_txt_free.getText().toString());
                String moTa=holder.hdn_txt_descriptions.getText().toString();

                TextView hdn_txt_maHDN_CT = view1.findViewById(R.id.hdn_txt_maHDN_CT);
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

                hdn_txt_maHDN_CT.setText(maHDN);
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
        holder.cv_hdn_CT.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                deleteHDN();
                return true;
            }
        });
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
    private void deleteHDN() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ApiInterface.ApiInterface.deleteHDN(hoaDonNhap.getId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        listHDN.clear();
                        GetDataHDN();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        listHDN.clear();
                        GetDataHDN();
                        notifyDataSetChanged();
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
    public void GetDataHDN() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<HoaDonNhap>> call= apiInterface.getHDN();
        call.enqueue(new Callback<List<HoaDonNhap>>() {
            @Override
            public void onResponse(Call<List<HoaDonNhap>> call, Response<List<HoaDonNhap>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listHDN.addAll(response.body());
                   notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<HoaDonNhap>> call, Throwable t) {
                Toast.makeText(context, "Loi api HDN getall", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

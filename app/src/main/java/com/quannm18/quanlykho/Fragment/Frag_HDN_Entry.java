package com.quannm18.quanlykho.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.quannm18.quanlykho.Adapter.FragHDN_Adapter;
import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Frag_HDN_Entry extends Fragment {
    TextInputEditText txt_hdn_name_add, txt_hdn_productType_add,txt_hdn_hang_add,txt_hdn_cot_add,
            txt_hdn_vitri_add,txt_hdn_quantity_add,
            txt_hdn_free_add,txt_hdn_ngayNhap_add, txt_hdn_descriptions_add;
    FloatingActionButton fla_HDN_entry;
    RecyclerView rcvHDN;
    FragHDN_Adapter fragHDN_adapter;
    List<HoaDonNhap> listHDN=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hdn_entry, container, false);
        fla_HDN_entry = view.findViewById(R.id.fla_HDN_entry);
        rcvHDN = view.findViewById(R.id.rcvHDN);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvHDN.setLayoutManager(layoutManager);
        fragHDN_adapter = new FragHDN_Adapter(getContext(), listHDN);

        GetDataHDN();

        fla_HDN_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_add_new_entry, null);
                builder.setView(view1);
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                txt_hdn_name_add = view1.findViewById(R.id.txt_hdn_name_add);
                txt_hdn_productType_add = view1.findViewById(R.id.txt_hdn_productType_add);
                txt_hdn_hang_add = view1.findViewById(R.id.txt_hdn_hang_add);
                txt_hdn_cot_add = view1.findViewById(R.id.txt_hdn_cot_add);
                txt_hdn_vitri_add = view1.findViewById(R.id.txt_hdn_vitri_add);
                txt_hdn_quantity_add = view1.findViewById(R.id.txt_hdn_quantity_add);
                txt_hdn_free_add = view1.findViewById(R.id.txt_hdn_free_add);
                txt_hdn_ngayNhap_add = view1.findViewById(R.id.txt_hdn_ngayNhap_add);
//                txt_hdn_descriptions_add = view1.findViewById(R.id.txt_hdn_descriptions_add);
                AppCompatButton hdn_btn_add = view1.findViewById(R.id.hdn_btn_add);
                AppCompatButton hdn_btn_close_add = view1.findViewById(R.id.hdn_btn_close_add);

                hdn_btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InsertDataHDN();
//                        InsertData(txt_hdn_name.getText().toString(),txt_hdn_productType.getText().toString(),Integer.parseInt(txt_hdn_quantity.getText().toString()),Integer.parseInt(txt_hdn_free.getText().toString()));
                        Toast.makeText(getContext(), "Them HDN", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


                hdn_btn_close_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;


    }

    public void InsertDataHDN() {
        HoaDonNhap hoaDonNhap = new HoaDonNhap();
        hoaDonNhap.setTenSP(txt_hdn_name_add.getText().toString());
        hoaDonNhap.setLoaiSP(txt_hdn_productType_add.getText().toString());
        hoaDonNhap.setHang(txt_hdn_hang_add.getText().toString());
        hoaDonNhap.setCot(txt_hdn_cot_add.getText().toString());
        hoaDonNhap.setViTri(txt_hdn_vitri_add.getText().toString());
        hoaDonNhap.setDonGia(Integer.parseInt(txt_hdn_free_add.getText().toString()));
        hoaDonNhap.setSoLuong(Integer.parseInt(txt_hdn_quantity_add.getText().toString()));
        hoaDonNhap.setNgayNhap(txt_hdn_ngayNhap_add.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<HoaDonNhap> call= apiInterface.postHDN(hoaDonNhap);
        call.enqueue(new Callback<HoaDonNhap>() {
            @Override
            public void onResponse(Call<HoaDonNhap> call, Response<HoaDonNhap> response) {
                listHDN.clear();
                GetDataHDN();
                fragHDN_adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HoaDonNhap> call, Throwable t) {
                Toast.makeText(getContext(), "Loi api HDN add", Toast.LENGTH_SHORT).show();

            }
        });
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
//                if (response.isSuccessful() && response.body() != null) {
                    listHDN.addAll(response.body());
                    fragHDN_adapter.setDataHDN(listHDN);
                    rcvHDN.setAdapter(fragHDN_adapter);

//                }
            }

            @Override
            public void onFailure(Call<List<HoaDonNhap>> call, Throwable t) {
                Toast.makeText(getContext(), "Loi api HDN getall", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

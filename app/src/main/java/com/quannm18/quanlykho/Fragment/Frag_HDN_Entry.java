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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.quannm18.quanlykho.Activity_HoaDon;
import com.quannm18.quanlykho.Activity_add_new_entry;
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
    TextInputEditText txt_hdn_name, txt_hdn_productType, txt_hdn_quantity, txt_hdn_free, txt_hdn_expiry, txt_hdn_descriptions;
    FloatingActionButton actionButton;
    RecyclerView rcvHDN;
    FragHDN_Adapter fragHDN_adapter;
    ArrayList<HoaDonNhap> listHDN=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hdn_entry, container, false);
        actionButton = view.findViewById(R.id.fla_HDN_entry);
        rcvHDN = view.findViewById(R.id.rcvHDN);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvHDN.setLayoutManager(layoutManager);
        fragHDN_adapter = new FragHDN_Adapter(getContext(), listHDN);
        rcvHDN.setAdapter(fragHDN_adapter);

        GetData();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_add_new_entry, null);
                builder.setView(view1);
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                txt_hdn_name = view1.findViewById(R.id.txt_hdn_name);
                txt_hdn_productType = view1.findViewById(R.id.txt_hdn_productType);
                txt_hdn_quantity = view1.findViewById(R.id.txt_hdn_quantity);
                txt_hdn_free = view1.findViewById(R.id.txt_hdn_free);
                txt_hdn_expiry = view1.findViewById(R.id.txt_hdn_expiry);
                txt_hdn_descriptions = view1.findViewById(R.id.txt_hdn_descriptions);
                AppCompatButton hdn_btn_add = view1.findViewById(R.id.hdn_btn_add);
                AppCompatButton hdn_btn_close = view1.findViewById(R.id.hdn_btn_close);

                hdn_btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InsertData();
//                        InsertData(txt_hdn_name.getText().toString(),txt_hdn_productType.getText().toString(),Integer.parseInt(txt_hdn_quantity.getText().toString()),Integer.parseInt(txt_hdn_free.getText().toString()));
                        Toast.makeText(getContext(), "Them", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


                hdn_btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;


    }

    public void InsertData() {
        HoaDonNhap hoaDonNhap = new HoaDonNhap();
        hoaDonNhap.setTenSP(txt_hdn_name.getText().toString());
        hoaDonNhap.setLoaiSP(txt_hdn_productType.getText().toString());
        hoaDonNhap.setSoluong(Integer.parseInt(txt_hdn_quantity.getText().toString()));
        hoaDonNhap.setGia(Integer.parseInt(txt_hdn_free.getText().toString()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<HoaDonNhap> call= apiInterface.postReg(hoaDonNhap);
        call.enqueue(new Callback<HoaDonNhap>() {
            @Override
            public void onResponse(Call<HoaDonNhap> call, Response<HoaDonNhap> response) {

            }

            @Override
            public void onFailure(Call<HoaDonNhap> call, Throwable t) {
                Toast.makeText(getContext(), "Loi api", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void GetData() {
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
                    fragHDN_adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<HoaDonNhap>> call, Throwable t) {
                Toast.makeText(getContext(), "Loi api1", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

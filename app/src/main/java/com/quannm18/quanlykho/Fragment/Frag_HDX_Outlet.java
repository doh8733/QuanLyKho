package com.quannm18.quanlykho.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.quannm18.quanlykho.Adapter.FragHDX_Adapter;
import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.Model.HoaDonXuat;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Frag_HDX_Outlet extends Fragment {
    TextInputEditText hdx_ngayNhap_add, hdx_ngayXuat_add, hdx_thanhtien_add, hdx_trangthai_add, hdx_descriptions_add;
    FloatingActionButton fla_HDX_outlet;
    RecyclerView rcvHDX;
    FragHDX_Adapter fragHDX_adapter;
    List<HoaDonXuat> listHDX=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hdx_outlet, container, false);
        fla_HDX_outlet = view.findViewById(R.id.fla_HDX_outlet);
        rcvHDX = view.findViewById(R.id.rcvHDX);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvHDX.setLayoutManager(layoutManager);
        fragHDX_adapter = new FragHDX_Adapter(getContext(), listHDX);

        GetDataHDX();
        fla_HDX_outlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_add_new_outlet, null);
                builder.setView(view1);
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                hdx_ngayNhap_add = view1.findViewById(R.id.hdx_ngayNhap_add);
                hdx_ngayXuat_add = view1.findViewById(R.id.hdx_ngayXuat_add);
                hdx_thanhtien_add = view1.findViewById(R.id.hdx_thanhtien_add);
                hdx_trangthai_add = view1.findViewById(R.id.hdx_trangthai_add);
                hdx_descriptions_add = view1.findViewById(R.id.hdx_descriptions_add);
                AppCompatButton hdx_btn_add = view1.findViewById(R.id.hdx_btn_add);
                AppCompatButton hdx_btn_close = view1.findViewById(R.id.hdx_btn_close);

                hdx_btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InsertDataHDX();
                        Toast.makeText(getContext(), "Them HDX", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                hdx_btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;


    }

    public void InsertDataHDX() {
        HoaDonXuat hoaDonXuat = new HoaDonXuat();
        hoaDonXuat.setNgayNhap(hdx_ngayNhap_add.getText().toString());
        hoaDonXuat.setNgayXuat(hdx_ngayXuat_add.getText().toString());
        hoaDonXuat.setThanhTien(Integer.parseInt(hdx_thanhtien_add.getText().toString()));
        hoaDonXuat.setTrangThai(hdx_trangthai_add.getText().toString());
        hoaDonXuat.setMoTa(hdx_descriptions_add.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<HoaDonXuat> callHDX = apiInterface.postHDX(hoaDonXuat);
        callHDX.enqueue(new Callback<HoaDonXuat>() {
            @Override
            public void onResponse(Call<HoaDonXuat> call, Response<HoaDonXuat> response) {
                listHDX.clear();
                GetDataHDX();
                fragHDX_adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "aaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<HoaDonXuat> call, Throwable t) {
                Toast.makeText(getContext(), "Loi api HDX add", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void GetDataHDX() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<HoaDonXuat>> call = apiInterface.getHDX();
        call.enqueue(new Callback<List<HoaDonXuat>>() {
            @Override
            public void onResponse(Call<List<HoaDonXuat>> call, Response<List<HoaDonXuat>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listHDX.addAll(response.body());
                    fragHDX_adapter.setDataHDX(listHDX);
                    rcvHDX.setAdapter(fragHDX_adapter);

                }
            }

            @Override
            public void onFailure(Call<List<HoaDonXuat>> call, Throwable t) {
                Toast.makeText(getContext(), "Loi api HDX getall", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

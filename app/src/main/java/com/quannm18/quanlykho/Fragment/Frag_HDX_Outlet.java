package com.quannm18.quanlykho.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.quannm18.quanlykho.Model.HoaDonXuat;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;

public class Frag_HDX_Outlet extends Fragment {
    FloatingActionButton actionButton;
    RecyclerView rcvHDX;
    FragHDX_Adapter fragHDX_adapter;
    ArrayList<HoaDonXuat> listHDX;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frag_hdx_outlet,container,false);
        actionButton=view.findViewById(R.id.fla_HDX_outlet);
        rcvHDX=view.findViewById(R.id.rcvHDX);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcvHDX.setLayoutManager(layoutManager);
        listHDX=new ArrayList<>();
        fragHDX_adapter=new FragHDX_Adapter(getContext(),listHDX);
        rcvHDX.setAdapter(fragHDX_adapter);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View view1=LayoutInflater.from(getContext()).inflate(R.layout.activity_add_new_outlet,null);
                builder.setView(view1);
                builder.setCancelable(true);
                AlertDialog dialog=builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                TextInputEditText hdx_name=view1.findViewById(R.id.hdx_name);
                TextInputEditText hdx_productType=view1.findViewById(R.id.hdx_productType);
                TextInputEditText hdx_quantity=view1.findViewById(R.id.hdx_quantity);
                TextInputEditText hdx_free=view1.findViewById(R.id.hdx_free);
                TextInputEditText hdx_expiry=view1.findViewById(R.id.hdx_expiry);
                TextInputEditText hdx_descriptions=view1.findViewById(R.id.hdx_descriptions);
                AppCompatButton hdx_btn_add=view1.findViewById(R.id.hdx_btn_add);
                AppCompatButton hdx_btn_close=view1.findViewById(R.id.hdx_btn_close);

                hdx_btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

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
}

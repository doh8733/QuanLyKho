package com.quannm18.quanlykho;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quannm18.quanlykho.Adapter.KhoAdapter;
import com.quannm18.quanlykho.Model.Kho;

import java.util.ArrayList;
import java.util.List;

public class TongQuatKhoActivity extends AppCompatActivity {
    private RecyclerView rcvTongQuatKho;
    private KhoAdapter khoAdapter;
    private List<Kho> khoList;
    private FloatingActionButton fabAddDepot;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_quat_kho);

        rcvTongQuatKho = (RecyclerView) findViewById(R.id.rcvTongQuatKho);


        fabAddDepot = (FloatingActionButton) findViewById(R.id.fabAddDepot);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TongQuatKhoActivity.this);
        rcvTongQuatKho.setLayoutManager(linearLayoutManager);
        khoList = new ArrayList<>();
        khoAdapter = new KhoAdapter(TongQuatKhoActivity.this,getAllKho());
        rcvTongQuatKho.setAdapter(khoAdapter);

        fabAddDepot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TongQuatKhoActivity.this);
                LayoutInflater inflater = TongQuatKhoActivity.this.getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_add_depot,null);
                builder.setView(view1);
                alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                alertDialog.show();
            }


        });

    }
    private List<Kho> getAllKho(){
        List<Kho> list = new ArrayList<>();
        list.add(new Kho("asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd"));
        list.add(new Kho("asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd"));
        list.add(new Kho("asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd"));
        list.add(new Kho("asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd","asdasd"));

        return list;
    }

}
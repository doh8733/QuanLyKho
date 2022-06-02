package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.quannm18.quanlykho.Adapter.ChooseAdapter;
import com.quannm18.quanlykho.Adapter.SubChooseAdapter;
import com.quannm18.quanlykho.Model.Depot;

import java.util.ArrayList;
import java.util.List;

public class ChooseDepotActivity extends AppCompatActivity {
    private RecyclerView rcvChoose;
    private ChooseAdapter adapter;
    private List<Depot> depotList;
    private List<Integer> floorList;
    private RecyclerView subRCVChoose;
    private SubChooseAdapter subAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_depot);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rcvChoose = (RecyclerView) findViewById(R.id.rcvChoose);
        subRCVChoose = (RecyclerView) findViewById(R.id.subRCVChoose);

        rcvChoose.getBackground().setAlpha(100);
//        subRCVChoose.getBackground().setAlpha(100);

        depotList = new ArrayList<>();
        floorList = new ArrayList<>();
        depotList.add(new Depot("Depot 1",2,10,3,"ajsvjav bạbksv"));
        for (int i = 0; i < depotList.get(0).getFloor(); i++) {
            floorList.add(new Integer(i+1));
        }
        adapter = new ChooseAdapter(depotList);
        subAdapter = new SubChooseAdapter(floorList);

        GridLayoutManager layoutManager = new GridLayoutManager(ChooseDepotActivity.this,depotList.get(0).getRow());
        rcvChoose.setLayoutManager(layoutManager);
        rcvChoose.setAdapter(adapter);

        GridLayoutManager subLayoutManager = new GridLayoutManager(ChooseDepotActivity.this,floorList.size());
        subRCVChoose.setLayoutManager(subLayoutManager);
        subRCVChoose.setAdapter(subAdapter);
    }
}
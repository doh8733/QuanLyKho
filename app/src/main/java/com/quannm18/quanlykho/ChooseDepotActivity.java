package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.quannm18.quanlykho.API.GetListPosition;
import com.quannm18.quanlykho.Adapter.ChooseAdapter;
import com.quannm18.quanlykho.Adapter.SubChooseAdapter;
import com.quannm18.quanlykho.Model.Depot;
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.Model.Position;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseDepotActivity extends AppCompatActivity {
    private RecyclerView rcvChoose;
    private ChooseAdapter adapter;
//    private List<Depot> depotList;
    private List<Integer> floorList;
    private RecyclerView subRCVChoose;
    private SubChooseAdapter subAdapter;
    private ImageView btnBackChooseDepot;
    private List<Position> positionList;
    private int row;
    private int col;
    public static int floor;
    private String id;

    private static ChooseDepotActivity instance;
    List<Position> poss;
    private List<Position> temPositionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_depot);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        instance = this;
        Intent intent = getIntent();
        Bundle depot = intent.getBundleExtra("depot");
        KhoHangModel khoHangModel = (KhoHangModel) depot.getSerializable("depot");
        row = Integer.parseInt(khoHangModel.getRow());
        floor = Integer.parseInt(khoHangModel.getFloors());
        col = Integer.parseInt(khoHangModel.getPosition());
        id = (khoHangModel.get_id());
        rcvChoose = (RecyclerView) findViewById(R.id.rcvChoose);
        subRCVChoose = (RecyclerView) findViewById(R.id.subRCVChoose);
        btnBackChooseDepot = (ImageView) findViewById(R.id.btnBackChooseDepot);
        rcvChoose.getBackground().setAlpha(100);
        positionList = new ArrayList<>();
        adapter = new ChooseAdapter(positionList);
        getListPos(ChooseDepotActivity.this,id, khoHangModel);
        GridLayoutManager layoutManager = new GridLayoutManager(ChooseDepotActivity.this,row);
        rcvChoose.setLayoutManager(layoutManager);
        rcvChoose.setAdapter(adapter);

        floorList = new ArrayList<>();
        for (int i = 1; i < floor+1; i++) {
            floorList.add(i);
        }
        GridLayoutManager subLayoutManager = new GridLayoutManager(ChooseDepotActivity.this,floorList.size());
        subRCVChoose.setLayoutManager(subLayoutManager);
        subAdapter = new SubChooseAdapter(floorList);
        subRCVChoose.setAdapter(subAdapter);
        btnBackChooseDepot = (ImageView) findViewById(R.id.btnBackChooseDepot);
        btnBackChooseDepot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    public void getListPos(Context context, String id, KhoHangModel depot){

        GetListPosition.getListPosition.postGetListPosition(id).enqueue(new Callback<List<Position>>() {
            @Override
            public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {
                poss = response.body();
                if (response.isSuccessful()){
                    if (poss.size()!=0) {
                        try {
                            temPositionList = new ArrayList<>();
//                            int row = Integer.parseInt(depot.getRow());
//                            int col = Integer.parseInt(depot.getPosition());
//                            int floor = Integer.parseInt(depot.getFloors());
                            int a = row * col;
                            int x = floor;
                            if (x>1){
                                x --;
                                for (int i =  a*(x-1); i <= a*x-1; i++) {
                                    temPositionList.add(poss.get(i));
                                    adapter.notifyDataSetChanged();
                                }
                            }else {
                                for (int i =  a*(x-1); i <= a*x-1; i++) {
                                    temPositionList.add(poss.get(i));
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            GridLayoutManager layoutManager = new GridLayoutManager(ChooseDepotActivity.this,row);
                            rcvChoose.setLayoutManager(layoutManager);
                            adapter = new ChooseAdapter(temPositionList);
                            rcvChoose.setAdapter(adapter);
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }else {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Position>> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setPositionList(List<Position> positionList) {
        positionList.clear();
        this.positionList = positionList;
        adapter.notifyDataSetChanged();
    }

    public static ChooseDepotActivity getInstance() {
        return instance;
    }
    public void finishAc(){
        finish();
    }

    public static void setFloor(int floor) {
        ChooseDepotActivity.floor = floor;
    }

    public void setNewList(int floor){
        temPositionList.clear();
        int a = row * col;
        int x = floor;
        for (int i =  a*(x-1); i <= a*x-1; i++) {
            temPositionList.add(poss.get(i));
            adapter.notifyDataSetChanged();
        }
        GridLayoutManager layoutManager = new GridLayoutManager(ChooseDepotActivity.this,row);
        rcvChoose.setLayoutManager(layoutManager);
        rcvChoose.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
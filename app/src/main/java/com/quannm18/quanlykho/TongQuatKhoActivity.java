package com.quannm18.quanlykho;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Adapter.DepotAdapter;
import com.quannm18.quanlykho.Interface.GetDepot;
import com.quannm18.quanlykho.Interface.PostDepot;
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.POST.ResponeDepotPOST;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TongQuatKhoActivity extends AppCompatActivity {
    private RecyclerView rcvTongQuatKho;
    private DepotAdapter depotAdapter;
    private Dialog dialog;
    private FloatingActionButton fabAddDepot;
    private AlertDialog alertDialog;
    private TextInputLayout tilNameWarehouse;
    private TextInputLayout tilRow;
    private TextInputLayout tilFloors;
    private TextInputLayout tilPosition;
    private TextInputLayout tilDescription;
    private MaterialButton btnCreate;
    private MaterialButton btnClose;
    private List<KhoHangModel> depotList;
    private ImageView imVFind;
    private ImageView imvBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_quat_kho);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        imvBack = (ImageView) findViewById(R.id.imv_back);
        rcvTongQuatKho = (RecyclerView) findViewById(R.id.rcvTongQuatKho);
//        fabAddDepot = (FloatingActionButton) findViewById(R.id.fabAddDepot);
//        imVFind = (ImageView) findViewById(R.id.imV_find);
        depotList = new ArrayList<>();
        getAllDaTa();
//        getALlDATADepot();
        depotAdapter = new DepotAdapter(TongQuatKhoActivity.this, depotList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TongQuatKhoActivity.this);
        rcvTongQuatKho.setLayoutManager(linearLayoutManager);
//        rcvTongQuatKho.setAdapter(depotAdapter);

//        fabAddDepot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(TongQuatKhoActivity.this);
//                LayoutInflater inflater = TongQuatKhoActivity.this.getLayoutInflater();
//                View view1 = inflater.inflate(R.layout.dialog_add_depot, null);
//                builder.setView(view1);
//                alertDialog = builder.create();
//                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                tilNameWarehouse = (TextInputLayout) view1.findViewById(R.id.tilNameWarehouse);
//                tilRow = (TextInputLayout) view1.findViewById(R.id.tilRow);
//                tilFloors = (TextInputLayout) view1.findViewById(R.id.tilFloors);
//                tilPosition = (TextInputLayout) view1.findViewById(R.id.tilPosition);
//                tilDescription = (TextInputLayout) view1.findViewById(R.id.tilDescription);
//                btnCreate = (MaterialButton) view1.findViewById(R.id.btnCreate);
//                btnClose = (MaterialButton) view1.findViewById(R.id.btnClose);
//
//
//                btnCreate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String name = tilNameWarehouse.getEditText().getText().toString();
//                        String row = tilRow.getEditText().getText().toString();
//                        String floors = tilFloors.getEditText().getText().toString();
//                        String position = tilPosition.getEditText().getText().toString();
//                        String description = tilDescription.getEditText().getText().toString();
//                        if (name.trim().isEmpty()) {
//                            tilNameWarehouse.setError("Vui lòng không để trắng thông tin!!!");
//                            return;
//                        } else {
//                            tilNameWarehouse.setErrorEnabled(false);
//                        }
//                        if (row.trim().isEmpty()) {
//                            tilRow.setError("Vui lòng không để trắng thông tin!!!");
//                            return;
//                        }
//                        else if (Integer.parseInt(row.trim())>10) {
//                            tilRow.setError("Vui lòng nhập dưới 10!!!");
//                            return;
//                        }else {
//                            tilRow.setErrorEnabled(false);
//                        }
//                        if (floors.trim().isEmpty()) {
//                            tilFloors.setError("Vui lòng không để trắng thông tin!!!");
//                            return;
//                        }else if (Integer.parseInt(floors.trim())>10) {
//                            tilRow.setError("Vui lòng nhập dưới 10!!!");
//                            return;
//                        } else {
//                            tilFloors.setErrorEnabled(false);
//                        }
//                        if (position.trim().isEmpty()) {
//                            tilPosition.setError("Vui lòng không để trắng thông tin!!!");
//                            return;
//                        }else if (Integer.parseInt(position.trim())>10) {
//                            tilRow.setError("Vui lòng nhập dưới 10!!!");
//                            return;
//                        } else {
//                            tilPosition.setErrorEnabled(false);
//                        }
//                        if (description.trim().isEmpty()) {
//                            tilDescription.setError("Vui lòng không để trắng thông tin!!!");
//                            return;
//                        } else {
//                            tilDescription.setErrorEnabled(false);
//                        }
//
//                        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl("https://agile-server-beco.herokuapp.com/khoHang/")
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build();
//                        PostDepot postDepot = retrofit.create(PostDepot.class);
//                        Call<ResponeDepotPOST> call = postDepot.postData(name, row, floors, position, description);
//                        call.enqueue(new Callback<ResponeDepotPOST>() {
//                            @Override
//                            public void onResponse(Call<ResponeDepotPOST> call, Response<ResponeDepotPOST> response) {
//                                ResponeDepotPOST responeDepotPOST = response.body();
//                                Toast.makeText(TongQuatKhoActivity.this, response.message(), Toast.LENGTH_SHORT).show();
//
//                                depotList.clear();
//                                getAllDaTa();
//                                depotAdapter.notifyDataSetChanged();
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponeDepotPOST> call, Throwable t) {
//                                Toast.makeText(TongQuatKhoActivity.this, "Loi", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
//                        alertDialog.dismiss();
//                    }
//                });
//                btnClose.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        alertDialog.dismiss();
//                    }
//                });
//
//                alertDialog.show();
//            }
//
//
//        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void getAllDaTa() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDepot getDepot = retrofit.create(GetDepot.class);
        Call<List<KhoHangModel>> call = getDepot.getKho();
        call.enqueue(new Callback<List<KhoHangModel>>() {
            @Override
            public void onResponse(Call<List<KhoHangModel>> call, Response<List<KhoHangModel>> response) {
                depotList = response.body();
                depotAdapter = new DepotAdapter(TongQuatKhoActivity.this, depotList);
                rcvTongQuatKho.setAdapter(depotAdapter);
            }

            @Override
            public void onFailure(Call<List<KhoHangModel>> call, Throwable t) {
                Toast.makeText(TongQuatKhoActivity.this, "Loi", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

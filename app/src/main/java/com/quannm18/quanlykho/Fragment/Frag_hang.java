package com.quannm18.quanlykho.Fragment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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
import com.quannm18.quanlykho.Adapter.FragHDN_Adapter;
import com.quannm18.quanlykho.Adapter.FragHang_Adapter;
import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.Interface.IloadMore;
import com.quannm18.quanlykho.Model.Hang;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Frag_hang extends Fragment {
    ArrayList<Hang> listHang=new ArrayList<>();
    TextInputEditText txt_LoaiHang, txt_TenHang, txt_SoLuong;
    FloatingActionButton actionButton;
    RecyclerView rcvHDN;
    FragHang_Adapter fragHang_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hang, container, false);
        actionButton = view.findViewById(R.id.fla_Hang_entry);
        rcvHDN = view.findViewById(R.id.rcvHang);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvHDN.setLayoutManager(layoutManager);
        fragHang_adapter = new FragHang_Adapter(getContext(), listHang);

//        fragHang_adapter.setIloadMore(new IloadMore() {
//            @Override
//            public void onLoadMore() {
//                if(listHang.size()<5){
//                    listHang.add(null);
//                    fragHang_adapter.notifyItemInserted(listHang.size()-1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            listHang.remove(listHang.size()-1);
//                            fragHang_adapter.notifyItemRemoved(listHang.size());
//
//                            int index=listHang.size();
//                            int end =index+10;
//                            for (int i=index;i<end;i++){
//                                Hang hang=new Hang();
//                                hang.setLoaiHang(txt_LoaiHang.getText().toString());
//                                hang.setTenHang(txt_TenHang.getText().toString());
//                                hang.setSoLuong(Integer.parseInt(txt_SoLuong.getText().toString()));
//                                listHang.add(hang);
//                            }
//                            fragHang_adapter.notifyDataSetChanged();
//                            fragHang_adapter.setLoaded();
//                        }
//                    },3000);
//                }
//            }
//        });

        GetData();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_add_hang, null);
                builder.setView(view1);
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                txt_LoaiHang = view1.findViewById(R.id.txt_LoaiHang);
                txt_TenHang = view1.findViewById(R.id.txt_TenHang);
                txt_SoLuong = view1.findViewById(R.id.txt_SoLuong);
                AppCompatButton hdn_btn_add_Hang = view1.findViewById(R.id.hdn_btn_add_Hang);
                AppCompatButton hdn_btn_close_Hang = view1.findViewById(R.id.hdn_btn_close_Hang);

                hdn_btn_add_Hang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InsertData();
//                        InsertData(txt_LoaiHang.getText().toString(),txt_TenHang.getText().toString(), Integer.parseInt(txt_SoLuong.getText().toString()));
                        Toast.makeText(getContext(), "Them hang", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });


                hdn_btn_close_Hang.setOnClickListener(new View.OnClickListener() {
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
//        Hang hang=new Hang();
//        hang.setLoaiHang(txt_LoaiHang.getText().toString());
//        hang.setTenHang(txt_TenHang.getText().toString());
//        hang.setSoLuong(Integer.parseInt(txt_SoLuong.getText().toString()));
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://agile-server-beco.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
////        Hang hang=new Hang(loaihang,tenhang,soluong);
//        Call<Hang> call= apiInterface.postHang(hang);
//        call.enqueue(new Callback<Hang>() {
//            @Override
//            public void onResponse(Call<Hang> call, Response<Hang> response) {
//                listHang.clear();
//                GetData();
//                fragHang_adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<Hang> call, Throwable t) {
//                Toast.makeText(getContext(), "Loi api hang", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
    public void GetData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://agile-server-beco.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//        Call<List<Hang>> call= apiInterface.getHang();
//        call.enqueue(new Callback<List<Hang>>() {
//            @Override
//            public void onResponse(Call<List<Hang>> call, Response<List<Hang>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    listHang.addAll(response.body());
//                    fragHang_adapter.setData(listHang);
//                    rcvHDN.setAdapter(fragHang_adapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Hang>> call, Throwable t) {
//                Toast.makeText(getContext(), "Loi api1", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}

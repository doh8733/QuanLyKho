package com.quannm18.quanlykho.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Adapter.FragmentFindHDN_Adapter;
import com.quannm18.quanlykho.Adapter.FragmentFindHDX_Adapter;
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


public class FindHDXFragment extends Fragment {
    List<HoaDonXuat> listHDX = new ArrayList<>();
    List<HoaDonXuat> findHDXList = new ArrayList<>();
    List<HoaDonXuat> findlist = new ArrayList<>();
    private TextInputLayout tilFindingHDX;
    private RecyclerView rcvFindHDX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_h_d_x, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tilFindingHDX = (TextInputLayout) view.findViewById(R.id.tilFindingHDX);
        rcvFindHDX = (RecyclerView) view.findViewById(R.id.rcvFindHDX);
        findHDXList = getAllDaTa(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvFindHDX.setLayoutManager(layoutManager);
        tilFindingHDX.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                String name = tilFindingHDX.getEditText().getText().toString();
                int pos =-1;
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    for (int i = 0; i < findHDXList.size(); i++) {
                        if (name.equalsIgnoreCase(findHDXList.get(i).getMaHDX())||(findHDXList.get(i).getMaHDX()).contains(name)){
                            findlist.clear();
                            findlist.add(findHDXList.get(i));
                            pos = i;
                        }

                    }
                    if (pos !=-1){
                        Toast.makeText(getContext(), "Found " + name, Toast.LENGTH_SHORT).show();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        rcvFindHDX.setLayoutManager(layoutManager);
                        FragmentFindHDX_Adapter depotAdapter = new FragmentFindHDX_Adapter(getContext(),findlist);
                        rcvFindHDX.setAdapter(depotAdapter);
                    }
                    else {

                        findlist.clear();
                        Toast.makeText(getContext(), "Find not found" + name, Toast.LENGTH_SHORT).show();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        rcvFindHDX.setLayoutManager(layoutManager);
                        FragmentFindHDX_Adapter depotAdapter = new FragmentFindHDX_Adapter(getContext(),findlist);
                        rcvFindHDX.setAdapter(depotAdapter);
                    }
                }
                return false;
            }
        });
    }
    public List<HoaDonXuat> getAllDaTa(Context context) {
        List<HoaDonXuat> hoaDonXuats = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<HoaDonXuat>> call= apiInterface.getHDX();
       call.enqueue(new Callback<List<HoaDonXuat>>() {
           @Override
           public void onResponse(Call<List<HoaDonXuat>> call, Response<List<HoaDonXuat>> response) {
               List<HoaDonXuat> hoaDonXuats1 = response.body();
               for (HoaDonXuat hoaDonXuat : hoaDonXuats1) {
                   hoaDonXuats.add(hoaDonXuat);
               }
           }

           @Override
           public void onFailure(Call<List<HoaDonXuat>> call, Throwable t) {

           }
       });
        return hoaDonXuats;

    }
//    public void GetDataHDX() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://agile-server-beco.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//        Call<List<HoaDonXuat>> call = apiInterface.getHDX();
//        call.enqueue(new Callback<List<HoaDonXuat>>() {
//            @Override
//            public void onResponse(Call<List<HoaDonXuat>> call, Response<List<HoaDonXuat>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    listHDX.addAll(response.body());
//                    fragHDX_adapter.setDataHDX(listHDX);
//                    rcvHDX.setAdapter(fragHDX_adapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<HoaDonXuat>> call, Throwable t) {
//                Toast.makeText(getContext(), "Loi api HDX getall", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}
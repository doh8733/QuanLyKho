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
import com.quannm18.quanlykho.Adapter.DepotAdapter;
import com.quannm18.quanlykho.Adapter.FragmentFindHDN_Adapter;
import com.quannm18.quanlykho.FindActivity;
import com.quannm18.quanlykho.Interface.ApiInterface;
import com.quannm18.quanlykho.Interface.GetDepot;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FindHDNFragment extends Fragment {

    private TextInputLayout tilFinding;
    private RecyclerView rcvFind;
    List<HoaDonNhap> listHDN=new ArrayList<>();
    List<HoaDonNhap> findlist=new ArrayList<>();
    List<HoaDonNhap> findlistHDN=new ArrayList<>();
    private FragmentFindHDN_Adapter fragHDN_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_h_d_n, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tilFinding = (TextInputLayout) view.findViewById(R.id.tilFinding);
        rcvFind = (RecyclerView) view.findViewById(R.id.rcvFind);
        fragHDN_adapter = new FragmentFindHDN_Adapter(getContext(),listHDN);
        findlistHDN = getAllDaTa(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvFind.setLayoutManager(layoutManager);
        tilFinding.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                String name = tilFinding.getEditText().getText().toString();
                int pos =-1;
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    for (int i = 0; i < findlistHDN.size(); i++) {
                        if (name.equalsIgnoreCase(findlistHDN.get(i).getTenSP())|| (findlistHDN.get(i).getTenSP()).contains(name)){
                            findlist.clear();
                            findlist.add(findlistHDN.get(i));
                            pos = i;
                        }

                    }
                    if (pos !=-1){
                        Toast.makeText(getContext(), "Found " + name, Toast.LENGTH_SHORT).show();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        rcvFind.setLayoutManager(layoutManager);
                        FragmentFindHDN_Adapter depotAdapter = new FragmentFindHDN_Adapter(getContext(),findlist);
                        rcvFind.setAdapter(depotAdapter);
                    }
                    else {
                        findlist.clear();
                        Toast.makeText(getContext(), "Find not found " + name, Toast.LENGTH_SHORT).show();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        rcvFind.setLayoutManager(layoutManager);
                        FragmentFindHDN_Adapter depotAdapter = new FragmentFindHDN_Adapter(getContext(),findlist);
                        rcvFind.setAdapter(depotAdapter);
                    }
                }
                return false;
            }
        });

    }

    public List<HoaDonNhap> getAllDaTa(Context context) {
        List<HoaDonNhap> hoaDonNhaps = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<HoaDonNhap>> call= apiInterface.getHDN();
        call.enqueue(new Callback<List<HoaDonNhap>>() {
            @Override
            public void onResponse(Call<List<HoaDonNhap>> call, Response<List<HoaDonNhap>> response) {
                List<HoaDonNhap> hoaDonNhapList = response.body();
                for (HoaDonNhap hoaDonNhap : hoaDonNhapList) {
                    hoaDonNhaps.add(hoaDonNhap);
                }
            }

            @Override
            public void onFailure(Call<List<HoaDonNhap>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "Loi", Toast.LENGTH_SHORT).show();
            }
        });
        return hoaDonNhaps;

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


                    if (response.isSuccessful() && response.body() != null) {
                        listHDN.addAll(response.body());
                        fragHDN_adapter.setDataHDN(listHDN);
                        rcvFind.setAdapter(fragHDN_adapter);

                    }


            }

            @Override
            public void onFailure(Call<List<HoaDonNhap>> call, Throwable t) {
                Toast.makeText(getContext(), "Loi api HDN getall", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
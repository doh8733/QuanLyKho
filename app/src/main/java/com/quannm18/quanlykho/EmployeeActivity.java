package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quannm18.quanlykho.Adapter.EmployeeAdapter;
import com.quannm18.quanlykho.Interface.EmployeeList;
import com.quannm18.quanlykho.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageButton btnSearch;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private List<NhanVien> nhanVienList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        nhanVienList = new ArrayList<>();
        nhanVienList = getEmployeeList();
        employeeAdapter = new EmployeeAdapter(EmployeeActivity.this,nhanVienList);
        recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeActivity.this));
        recyclerView.setAdapter(employeeAdapter);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    List<NhanVien> getEmployeeList(){
        List<NhanVien> tempList = new ArrayList<>();
        EmployeeList.employeeList.getNhanVienList().enqueue(new Callback<List<NhanVien>>() {
            @Override
            public void onResponse(Call<List<NhanVien>> call, Response<List<NhanVien>> response) {
                List<NhanVien> list= response.body();
                for (int i = 0; i < list.size(); i++) {
                    tempList.add(list.get(i));
                }
                employeeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<NhanVien>> call, Throwable t) {
                Toast.makeText(EmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: "+t );
            }
        });
        return tempList;
    }
}
package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.quannm18.quanlykho.Interface.EmployeeList;
import com.quannm18.quanlykho.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvname,tvrole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvname = (TextView) findViewById(R.id.tvname);
        tvrole = findViewById(R.id.tvrole);
        SharedPreferences sdf = getSharedPreferences("FILE_MODE",MODE_PRIVATE);
        String a =  sdf.getString("ROLE","");
        String b =sdf.getString("NAME","");
       tvname.setText(b);
       tvrole.setText(a);
        tvrole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

}
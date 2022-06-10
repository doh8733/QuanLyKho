package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private AppCompatButton btnChangePassword;
    private AppCompatButton btnLogout;
    private TextView tvName;
    private TextView tvGender;
    private TextView tvAge;
    private TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnChangePassword = (AppCompatButton) findViewById(R.id.btnChangePassword);
        btnLogout = (AppCompatButton) findViewById(R.id.btnLogout);
        tvName = (TextView) findViewById(R.id.tvName);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvAge = (TextView) findViewById(R.id.tvAge);
        tvAddress = (TextView) findViewById(R.id.tvAddress);

        SharedPreferences sdf = getSharedPreferences("FILE_MODE",MODE_PRIVATE);

        String name =sdf.getString("NAME","");
        String gender =sdf.getString("GENDER","");
        String age =sdf.getString("AGE","");
        String address =sdf.getString("ADDRESS","");

        tvName.setText(name);
        tvGender.setText(gender);
        tvAge.setText(age);
        tvAddress.setText(address);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String role =  sdf.getString("ROLE","");
                if (role.equalsIgnoreCase("admin")){
                    Intent intent = new Intent(ProfileActivity.this,TestAdminActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ProfileActivity.this,testuserActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
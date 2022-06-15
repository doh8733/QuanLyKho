package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.quannm18.quanlykho.Interface.Api;
import com.quannm18.quanlykho.Model.NhanVien;
import com.quannm18.quanlykho.POST.LoginRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextInputEditText edtOldPassword;
    private TextInputEditText edtNewPassword;
    private TextInputEditText edtRetypePassword;
    private AppCompatButton btnChangePassword;
    private AppCompatButton btnClose;
    private TextInputLayout tiloldPassword;
    private TextInputLayout tilnewPassword;
    private TextInputLayout tilRetyPassword;
    private NhanVien nhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        SharedPreferences sdf = getSharedPreferences("FILE_MODE",MODE_PRIVATE);

        String name =sdf.getString("NAME","");
        String gender =sdf.getString("GENDER","");
        String age =sdf.getString("AGE","");
        String address =sdf.getString("ADDRESS","");
        String role =  sdf.getString("ROLE","");
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        edtOldPassword = (TextInputEditText) findViewById(R.id.edtOldPassword);
        edtNewPassword = (TextInputEditText) findViewById(R.id.edtNewPassword);
        edtRetypePassword = (TextInputEditText) findViewById(R.id.edtRetypePassword);
        btnChangePassword = (AppCompatButton) findViewById(R.id.btnChangePassword);
        tiloldPassword = (TextInputLayout) findViewById(R.id.tiloldPassword);
        tilnewPassword = (TextInputLayout) findViewById(R.id.tilnewPassword);
        tilRetyPassword = (TextInputLayout) findViewById(R.id.tilRetyPassword);
        nhanVien = new NhanVien();
        btnClose = (AppCompatButton) findViewById(R.id.btnClose);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sdf = getSharedPreferences("FILE_MODE", MODE_PRIVATE);
                String id = sdf.getString("ID", "");
                String psswd = sdf.getString("PASSWORD", "");
                String oldPass = tiloldPassword.getEditText().getText().toString();
                String newPass = tilnewPassword.getEditText().getText().toString();
                String reNewPass = tilRetyPassword.getEditText().getText().toString();
                if (oldPass.length() == 0){
                    tiloldPassword.setErrorEnabled(true);
                    tiloldPassword.setError("Fill data please");
                    tilnewPassword.setErrorEnabled(false);
                    tilRetyPassword.setErrorEnabled(false);
                    return;
                }  else if(newPass.length() == 0){
                    tilnewPassword.setErrorEnabled(true);
                    tilnewPassword.setError("Fill data please");
                    tiloldPassword.setErrorEnabled(false);
                    tilRetyPassword.setErrorEnabled(false);
                    return;
                }
                else if(reNewPass.length() ==0){
                    tilRetyPassword.setErrorEnabled(true);
                    tilRetyPassword.setError("Fill data please");
                    tiloldPassword.setErrorEnabled(false);
                    tilnewPassword.setErrorEnabled(false);
                    return;
                }
                else if(!oldPass.equals(psswd)){
                    tilRetyPassword.setErrorEnabled(false);
                    tiloldPassword.setErrorEnabled(true);
                    tiloldPassword.setError("Old password is wrong!");
                    tilnewPassword.setErrorEnabled(false);
                    return;
                }
                else if (!newPass.equalsIgnoreCase(reNewPass)){
                    Toast.makeText(ChangePasswordActivity.this, "Khong trung khop", Toast.LENGTH_SHORT).show();
                    return;
                }

                    Api.api.postChangePassword(id, oldPass, newPass).enqueue(new Callback<LoginRespone>() {
                        @Override
                        public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                            response.body();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                            Toast.makeText(ChangePasswordActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<LoginRespone> call, Throwable t) {

                        }
                    });
                }


        });


    }
}
package com.quannm18.quanlykho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.quannm18.quanlykho.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignIn;

    private List<Employee> mListEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        mListEmployee = new ArrayList<>();

        getListEmployee();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        String strUsername = edtUsername.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();

        if (mListEmployee == null || mListEmployee.isEmpty()) {
            return;
        }

        boolean isEmployee = false;
        for (Employee employee : mListEmployee) {
//            if (strUsername.equals())
        }

        if (isEmployee) {
            //Intent + Bundle
        } else {
            Toast.makeText(SignIn.this, "Username or password is not correct", Toast.LENGTH_SHORT).show();
        }
    }

    //Nhớ chèn value
    private void getListEmployee() {
        ApiService.apiService.getListEmployee("value")
                .enqueue(new Callback<List<Employee>>() {
                    @Override
                    public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                        mListEmployee = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<Employee>> call, Throwable t) {
                        Toast.makeText(SignIn.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
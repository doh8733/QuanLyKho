package com.quannm18.quanlykho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.api.ApiInterface;
import com.quannm18.quanlykho.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignIn;

    private List<Employee> mListEmployee;

    String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        mListEmployee = new ArrayList<>();

//        getListEmployee();

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
//
//        if (mListEmployee == null || mListEmployee.isEmpty()) {
//            return;
//        }
//
//        boolean isEmployee = false;
//        for (Employee employee : mListEmployee) {
////            if (strUsername.equals())
//        }
//
//        if (isEmployee) {
//            //Intent + Bundle
//        } else {
//            Toast.makeText(SignIn.this, "Username or password is not correct", Toast.LENGTH_SHORT).show();
//        }

        if (strUsername.isEmpty() || strPassword.isEmpty()) {
            Toast.makeText(SignIn.this, "All fields must not be empty", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

//    //Nhớ chèn value
//    private void getListEmployee() {
//        ApiService.apiService.getListEmployee("628ef6b251fd3cfc4d53440b")
//                .enqueue(new Callback<List<Employee>>() {
//                    @Override
//                    public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
//                        mListEmployee = response.body();
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Employee>> call, Throwable t) {
//                        Toast.makeText(SignIn.this, "Failure", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    void postLogin(String username, String password) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/users/api-profile/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Employee employee = new Employee(username, password);
        Call<Employee> call = apiInterface.postLogin(employee);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                try {
                    Employee employee = response.body();
                    token = employee.getToken();
                    if (token != null) {
                        startActivity(new Intent(SignIn.this, MainActivity.class));
                    } else {
                        Toast.makeText(SignIn.this, "Username or password is not correct", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(SignIn.this, "Username or password is not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SignIn.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
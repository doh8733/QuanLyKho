package com.quannm18.quanlykho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.api.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEmployee extends AppCompatActivity {
    TextInputEditText edName, edAge, edAddress, edStartWorkDate;
    Spinner spGender;
    Button btnChooseImage, btnCreate, btnClose;
    List<String> genders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        edName = findViewById(R.id.edName);
        spGender = findViewById(R.id.spGender);
        edAge = findViewById(R.id.edAge);
        edAddress = findViewById(R.id.edAddress);
        edStartWorkDate = findViewById(R.id.edStartWorkDate);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btnCreate = findViewById(R.id.btnCreate);
        btnClose = findViewById(R.id.btnClose);

        genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, genders);
        spGender.setAdapter(arrayAdapter);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString().trim();
                String gender = spGender.getSelectedItem().toString();
                String age = edAge.getText().toString().trim();
                String address = edAddress.getText().toString().trim();
                String startWorkDate = edStartWorkDate.getText().toString().trim();

                if (name.isEmpty() || age.isEmpty() || address.isEmpty() || startWorkDate.isEmpty()) {
                    Toast.makeText(AddEmployee.this, "All fields must not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    postData(name, gender, age, address, startWorkDate);
                    Toast.makeText(AddEmployee.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddEmployee.this, SignIn.class));
                }
            }
        });
    }

    private void postData(String name, String gender, String age, String address, String startWorkDate) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/users/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Employee employee = new Employee(name, gender, age, address, startWorkDate);
        Call<Employee> call = apiInterface.postReg(employee);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

            }
        });
    }
}
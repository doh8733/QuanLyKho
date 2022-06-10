package com.quannm18.quanlykho.Interface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.Model.NhanVien;
import com.quannm18.quanlykho.Model.Token;
import com.quannm18.quanlykho.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface EmployeeList {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    EmployeeList employeeList = new Retrofit.Builder()
            .baseUrl("https://agile-server-beco.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(EmployeeList.class);

    @GET("users/")
    Call<List<NhanVien>> getNhanVienList();
}

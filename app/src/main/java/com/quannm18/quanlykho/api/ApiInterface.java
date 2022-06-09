package com.quannm18.quanlykho.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://agile-server-beco.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @POST("users/api-reg")
    Call<Employee> postReg(@Query("hoTen") String hoTen,
                           @Query("matKhau") String matKhau);

    @POST("users/api-login")
    Call<Employee>  postLogin(@Body Employee employee);
}

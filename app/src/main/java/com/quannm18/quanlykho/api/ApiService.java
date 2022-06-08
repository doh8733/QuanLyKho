package com.quannm18.quanlykho.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public interface ApiService {

    //Link Api: where?
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    Call<List<Employee>> getListEmployee (@Query("") String key);
}

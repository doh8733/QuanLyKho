package com.quannm18.quanlykho.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //Link Api
    //Sign in: https://agile-server-beco.herokuapp.com/users/api-profile/628ef6b251fd3cfc4d53440b
    //Add Employee: https://agile-server-beco.herokuapp.com/users/api-reg
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://agile-server-beco.herokuapp.com/users/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api-profile")
    Call<List<Employee>> getListEmployee (@Query("") String key);
}

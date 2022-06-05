package com.quannm18.quanlykho.api;

import com.quannm18.quanlykho.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("api-reg")
    Call<Employee> postReg(@Body Employee employee);

    @POST("api-login")
    Call<Employee>  postLogin(@Body Employee employee);
}

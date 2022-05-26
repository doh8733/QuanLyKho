package com.quannm18.quanlykho.Interface;

import com.quannm18.quanlykho.Model.HoaDonNhap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("add")
    Call<HoaDonNhap> postReg(@Body HoaDonNhap HDN);
    @GET("warehouse")
    Call<List<HoaDonNhap>>  getHDN();
}

package com.quannm18.quanlykho.Interface;

import com.quannm18.quanlykho.Model.HoaDonNhap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("add")
    Call<HoaDonNhap> postHDN(@Body HoaDonNhap HDN);
    @GET("warehouse")
    Call<List<HoaDonNhap>>  getHDN();



//    @POST("add")
//    Call<Hang> postHang(@Body Hang hang);
//
//    @GET("warehouse")
//    Call<List<Hang>>  getHang();


}

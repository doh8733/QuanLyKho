package com.quannm18.quanlykho.Interface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.Model.HoaDonNhap;
import com.quannm18.quanlykho.Model.HoaDonXuat;
import com.quannm18.quanlykho.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiInterface ApiInterface = new Retrofit.Builder()
            .baseUrl("https://agile-server-beco.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiInterface.class);

    @POST("add/ware")
    Call<HoaDonNhap> postHDN(@Body HoaDonNhap HDN);
    @GET("warehouse")
    Call<List<HoaDonNhap>>  getHDN();

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/delete/{id}")
    Call<String>  deleteHDN(@Path("id") String _id);



    @POST("HDX/add")
    Call<HoaDonXuat> postHDX(@Body HoaDonXuat HDX);
    @GET("HDX")
    Call<List<HoaDonXuat>>  getHDX();

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("HDX/delete/{id}")
    Call<String>  deleteHDX(@Path("id") String _id);


    @POST("users/api-login")
    Call<NhanVien>  postLogin(@Body NhanVien nhanVien);






}

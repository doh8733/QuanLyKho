package com.quannm18.quanlykho.Interface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.Model.NhanVien;
import com.quannm18.quanlykho.Model.Position;
import com.quannm18.quanlykho.POST.LoginRespone;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    Api api = new Retrofit.Builder()
            .baseUrl("https://agile-server-beco.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api.class);
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("users/api-login")
    Call<LoginRespone> postLogin(
            @Field("userName")String userName,
            @Field("password")String password
    );
    @FormUrlEncoded
    @POST("users/api-edti-password/{id}")
    Call<LoginRespone> postChangePassword(
            @Path(value = "id")String _id,
            @Field("password") String password,
            @Field("newPassword")String newPassword
    );


}

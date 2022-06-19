package com.quannm18.quanlykho.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.Model.Finance;
import com.quannm18.quanlykho.Model.TotalStatistic;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FinanceAPI {
    String url = "https://agile-server-beco.herokuapp.com/quanLy";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    FinanceAPI financeAPI = new Retrofit.Builder()
            .baseUrl("https://agile-server-beco.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FinanceAPI.class);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("quanLy/All")
    Call<TotalStatistic> getStatisticTotal (@Field("gte") Date gte,
                                            @Field("lte") Date lte);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("quanLy/month")
    Call<Finance> getStatisticMonth (@Field("gte") Date gte,
                                            @Field("lte") Date lte);
}

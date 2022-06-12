package com.quannm18.quanlykho.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quannm18.quanlykho.Interface.PostDepot;
import com.quannm18.quanlykho.Model.Position;
import com.quannm18.quanlykho.Model.PostionStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface GetListPosition {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    GetListPosition getListPosition = new Retrofit.Builder()
            .baseUrl("https://a612.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GetListPosition.class);

    String url = "https://a612.herokuapp.com/";
    @POST("add/{id}")
    Call<PostionStatus> postCreatePosition (@Path("id") String id);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("edit/{id}")
    Call<Position> postUpdateStatus (@Path("id") String id,
                                       @Field("status") String status,
                                     @Field("namePosition") String namePosition);


    @GET("{id}")
    Call<List<Position>> postGetListPosition (@Path("id") String id);
}

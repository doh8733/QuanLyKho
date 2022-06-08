package com.quannm18.quanlykho.Interface;

import com.quannm18.quanlykho.Model.KhoHangModel;
import com.quannm18.quanlykho.POST.ResponeDepotUpdate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostDepotUpdate {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("edit/{id}")
    Call<ResponeDepotUpdate> updateKhoHang(

            @Path(value = "id") String _id,
            @Field("name") String name,
            @Field("row") String row,
            @Field("floors") String floors,
            @Field("position") String position,
            @Field("description") String description
    );

}

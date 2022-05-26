package com.quannm18.quanlykho.Interface;

import com.quannm18.quanlykho.POST.ResponeDepotUpdate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostDepotUpdate {
    @FormUrlEncoded
    @POST("edit/{_id}")
    Call<ResponeDepotUpdate> updateKhoHang(
            @Field("_id") String _id,
            @Field("name") String name,
            @Field("row") String row,
            @Field("floors") String floors,
            @Field("position") String position,
            @Field("description") String description
    );
}

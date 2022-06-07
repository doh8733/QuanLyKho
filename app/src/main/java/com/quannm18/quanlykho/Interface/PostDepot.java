package com.quannm18.quanlykho.Interface;

import com.quannm18.quanlykho.POST.ResponeDelete;
import com.quannm18.quanlykho.POST.ResponeDepotPOST;
import com.quannm18.quanlykho.POST.ResponeDepotUpdate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostDepot {

    @FormUrlEncoded
    @POST("add")
    Call<ResponeDepotPOST> postData(
            @Field("name") String name,
            @Field("row") String row,
            @Field("floors") String floors,
            @Field("position") String position,
            @Field("description") String description
    );
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("delete/{id}")
    Call<ResponeDepotUpdate> postDelete(
            @Path(value = "id") String _id,
            @Field("name") String nam,
            @Field("row") String row,
            @Field("floors") String floors,
            @Field("position") String position,
            @Field("description") String description
    );
}

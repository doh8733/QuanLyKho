package com.quannm18.quanlykho.Interface;

import com.quannm18.quanlykho.POST.ResponeDelete;
import com.quannm18.quanlykho.POST.ResponeDepotPOST;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    @FormUrlEncoded
    @POST("delete")
    Call<ResponeDelete> postDelete(
            @Field("_id")String _id
    );
}

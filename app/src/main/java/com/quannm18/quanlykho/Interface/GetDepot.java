package com.quannm18.quanlykho.Interface;

import com.quannm18.quanlykho.Model.KhoHangModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDepot {
    @GET("KhoHang")
    Call<List<KhoHangModel>> getKho();
}

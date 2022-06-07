package com.quannm18.quanlykho.POST;

import android.content.Context;

import com.quannm18.quanlykho.Interface.PostDepot;
import com.quannm18.quanlykho.Interface.PostDepotUpdate;
import com.quannm18.quanlykho.Model.KhoHangModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallApi {

    public void Update_Depot(Context context,KhoHangModel khoHangModel){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/khoHang/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestDepotPOSTUpdate requestDepotPOSTUpdate = new RequestDepotPOSTUpdate();
        requestDepotPOSTUpdate.setKhoHang(khoHangModel);
        PostDepotUpdate postDepotUpdate = retrofit.create(PostDepotUpdate.class);

        Call<ResponeDepotUpdate> call = postDepotUpdate.updateKhoHang(
                khoHangModel.get_id(),
                khoHangModel.getName(),
                khoHangModel.getRow(),
                khoHangModel.getFloors(),
                khoHangModel.getPosition(),
                khoHangModel.getDescription()
        );
        call.enqueue(new Callback<ResponeDepotUpdate>() {
            @Override
            public void onResponse(Call<ResponeDepotUpdate> call, Response<ResponeDepotUpdate> response) {

            }

            @Override
            public void onFailure(Call<ResponeDepotUpdate> call, Throwable t) {

            }
        });
    }

    public void deleteDepot(KhoHangModel khoHangModel){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-server-beco.herokuapp.com/khoHang/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestDelete requestDelete = new RequestDelete();
        requestDelete.setKhohangs(khoHangModel);

        PostDepot postDepot = retrofit.create(PostDepot.class);
        Call<ResponeDepotUpdate> call = postDepot.postDelete(
                khoHangModel.get_id(),
                khoHangModel.getName(),
                khoHangModel.getRow(),
                khoHangModel.getFloors(),
                khoHangModel.getPosition(),
                khoHangModel.getDescription()
        );
        call.enqueue(new Callback<ResponeDepotUpdate>() {
            @Override
            public void onResponse(Call<ResponeDepotUpdate> call, Response<ResponeDepotUpdate> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<ResponeDepotUpdate> call, Throwable t) {

            }
        });
    }


}

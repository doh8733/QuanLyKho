package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

public class TotalStatistic {
    @SerializedName("tongtienNhap")
    private int tongTienNhap;
    @SerializedName("tongtienXuat")
    private int tongTienXuat;

    public TotalStatistic(int tongTienNhap, int tongTienXuat) {
        this.tongTienNhap = tongTienNhap;
        this.tongTienXuat = tongTienXuat;
    }

    public TotalStatistic() {
    }

    public int getTongTienNhap() {
        return tongTienNhap;
    }

    public void setTongTienNhap(int tongTienNhap) {
        this.tongTienNhap = tongTienNhap;
    }

    public int getTongTienXuat() {
        return tongTienXuat;
    }

    public void setTongTienXuat(int tongTienXuat) {
        this.tongTienXuat = tongTienXuat;
    }
}

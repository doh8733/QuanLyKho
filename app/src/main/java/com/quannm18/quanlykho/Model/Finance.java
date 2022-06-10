package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

public class Finance {
    @SerializedName("tongNhap")
    private int tongNhap;
    @SerializedName("tongXuat")
    private int tongXuat;

    public Finance(int tongNhap, int tongXuat) {
        this.tongNhap = tongNhap;
        this.tongXuat = tongXuat;
    }

    public Finance() {
    }

    public int getTongNhap() {
        return tongNhap;
    }

    public void setTongNhap(int tongNhap) {
        this.tongNhap = tongNhap;
    }

    public int getTongXuat() {
        return tongXuat;
    }

    public void setTongXuat(int tongXuat) {
        this.tongXuat = tongXuat;
    }
}

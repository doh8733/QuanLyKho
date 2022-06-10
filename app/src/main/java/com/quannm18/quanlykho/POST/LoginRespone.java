package com.quannm18.quanlykho.POST;

import com.google.gson.annotations.SerializedName;
import com.quannm18.quanlykho.Model.NhanVien;

public class LoginRespone {
    @SerializedName("user")
    private NhanVien nhanVien;

    public LoginRespone(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LoginRespone() {

    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}

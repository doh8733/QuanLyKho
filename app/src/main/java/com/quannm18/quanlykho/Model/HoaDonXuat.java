package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

public class HoaDonXuat {
    @SerializedName("_id")
    private String id;
    @SerializedName("MaHDX")
    private String MaHDX;
    @SerializedName("MaHDN")
    private String MaHDN;
    @SerializedName("NgayNhap")
    private String NgayNhap;
    @SerializedName("NgayXuat")
    private String NgayXuat;
    @SerializedName("ThanhTien")
    private int ThanhTien;
    @SerializedName("TrangThai")
    private String TrangThai;
    @SerializedName("moTa")
    private String moTa;

    public HoaDonXuat() {
    }

    public HoaDonXuat(String id, String maHDX, String maHDN, String ngayNhap, String ngayXuat, int thanhTien, String trangThai, String moTa) {
        this.id = id;
        MaHDX = maHDX;
        MaHDN = maHDN;
        NgayNhap = ngayNhap;
        NgayXuat = ngayXuat;
        ThanhTien = thanhTien;
        TrangThai = trangThai;
        this.moTa = moTa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaHDX() {
        return MaHDX;
    }

    public void setMaHDX(String maHDX) {
        MaHDX = maHDX;
    }

    public String getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        NgayXuat = ngayXuat;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}


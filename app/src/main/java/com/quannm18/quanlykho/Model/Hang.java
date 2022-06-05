package com.quannm18.quanlykho.Model;

public class Hang {
    private String LoaiHang;
    private String TenHang;
    private int SoLuong;

    public Hang() {
    }

    public Hang(String loaiHang, String tenHang, int soLuong) {
        LoaiHang = loaiHang;
        TenHang = tenHang;
        SoLuong = soLuong;
    }

    public String getLoaiHang() {
        return LoaiHang;
    }

    public void setLoaiHang(String loaiHang) {
        LoaiHang = loaiHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String tenHang) {
        TenHang = tenHang;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}

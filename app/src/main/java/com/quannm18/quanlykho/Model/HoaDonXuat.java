package com.quannm18.quanlykho.Model;

public class HoaDonXuat {
    private String maHDX;
    private String maHDN;
    private String ngayNhap;
    private String ngayXuat;
    private String thanhTien;
    private String trangThai;

    public HoaDonXuat() {
    }

    public HoaDonXuat(String maHDX, String maHDN, String ngayNhap, String ngayXuat, String thanhTien, String trangThai) {
        this.maHDX = maHDX;
        this.maHDN = maHDN;
        this.ngayNhap = ngayNhap;
        this.ngayXuat = ngayXuat;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public String getMaHDX() {
        return maHDX;
    }

    public void setMaHDX(String maHDX) {
        this.maHDX = maHDX;
    }

    public String getMaHDN() {
        return maHDN;
    }

    public void setMaHDN(String maHDN) {
        this.maHDN = maHDN;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}


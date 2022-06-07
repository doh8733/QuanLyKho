package com.quannm18.quanlykho.Model;

public class HoaDonXuat {
    private String MaHDX;
    private String MaHDN;
    private String NgayNhap;
    private String NgayXuat;
    private int ThanhTien;
    private String TrangThai;
    private String moTa;

    public HoaDonXuat() {
    }

    public HoaDonXuat(String maHDX, String maHDN, String ngayNhap, String ngayXuat, int thanhTien, String trangThai, String moTa) {
        MaHDX = maHDX;
        MaHDN = maHDN;
        NgayNhap = ngayNhap;
        NgayXuat = ngayXuat;
        ThanhTien = thanhTien;
        TrangThai = trangThai;
        this.moTa = moTa;
    }

    public String getMaHDX() {
        return MaHDX;
    }

    public void setMaHDX(String maHDX) {
        MaHDX = maHDX;
    }

    public String getMaHDN() {
        return MaHDN;
    }

    public void setMaHDN(String maHDN) {
        MaHDN = maHDN;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
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


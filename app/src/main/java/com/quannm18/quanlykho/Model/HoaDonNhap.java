package com.quannm18.quanlykho.Model;

public class HoaDonNhap {
    private String MaHoaDonNhap;
    private String LoaiSP;
    private String Hang;
    private String Cot;
    private String ViTri;
    private String TenSP;
    private int SoLuong;
    private String NgayNhap;
    private int DonGia;

    public HoaDonNhap() {
    }

    public HoaDonNhap(String maHoaDonNhap, String loaiSP, String hang, String cot, String viTri, String tenSP, int soLuong, String ngayNhap, int donGia) {
        MaHoaDonNhap = maHoaDonNhap;
        LoaiSP = loaiSP;
        Hang = hang;
        Cot = cot;
        ViTri = viTri;
        TenSP = tenSP;
        SoLuong = soLuong;
        NgayNhap = ngayNhap;
        DonGia = donGia;
    }

    public String getMaHoaDonNhap() {
        return MaHoaDonNhap;
    }

    public void setMaHoaDonNhap(String maHoaDonNhap) {
        MaHoaDonNhap = maHoaDonNhap;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        LoaiSP = loaiSP;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String hang) {
        Hang = hang;
    }

    public String getCot() {
        return Cot;
    }

    public void setCot(String cot) {
        Cot = cot;
    }

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String viTri) {
        ViTri = viTri;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }
}

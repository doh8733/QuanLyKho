package com.quannm18.quanlykho.Model;

import com.google.gson.annotations.SerializedName;

public class HoaDonNhap {
    @SerializedName("_id")
    private String id;
    @SerializedName("MaHoaDonNhap")
    private String MaHoaDonNhap;
    @SerializedName("LoaiSP")
    private String LoaiSP;
    @SerializedName("Hang")
    private String Hang;
    @SerializedName("Cot")
    private String Cot;
    @SerializedName("ViTri")
    private String ViTri;
    @SerializedName("TenSP")
    private String TenSP;
    @SerializedName("SoLuong")
    private int SoLuong;
    @SerializedName("NgayNhap")
    private String NgayNhap;
    @SerializedName("DonGia")
    private int DonGia;
    @SerializedName("moTa")
    private String moTa;

    public HoaDonNhap() {
    }

    public HoaDonNhap(String id, String maHoaDonNhap, String loaiSP, String hang, String cot, String viTri, String tenSP, int soLuong, String ngayNhap, int donGia, String moTa) {
        this.id = id;
        MaHoaDonNhap = maHoaDonNhap;
        LoaiSP = loaiSP;
        Hang = hang;
        Cot = cot;
        ViTri = viTri;
        TenSP = tenSP;
        SoLuong = soLuong;
        NgayNhap = ngayNhap;
        DonGia = donGia;
        this.moTa = moTa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}

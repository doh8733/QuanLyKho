package com.quannm18.quanlykho.Model;

public class HoaDonNhap {
    private String maHDN;
    private String loaiSP;
    private String hang;
    private String cot;
    private String vitri;
    private String tenSP;
    private int soluong;
    private String ngayNhap;
    private int gia;

//    public HoaDonNhap(String loaiSP, String tenSP, int soluong, int gia) {
//        this.loaiSP = loaiSP;
//        this.tenSP = tenSP;
//        this.soluong = soluong;
//        this.gia = gia;
//    }


    public HoaDonNhap() {
    }

    public HoaDonNhap(String maHDN, String loaiSP, String hang, String cot, String vitri, String tenSP, int soluong, String ngayNhap, int gia) {
        this.maHDN = maHDN;
        this.loaiSP = loaiSP;
        this.hang = hang;
        this.cot = cot;
        this.vitri = vitri;
        this.tenSP = tenSP;
        this.soluong = soluong;
        this.ngayNhap = ngayNhap;
        this.gia = gia;
    }

    public String getMaHDN() {
        return maHDN;
    }

    public void setMaHDN(String maHDN) {
        this.maHDN = maHDN;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getCot() {
        return cot;
    }

    public void setCot(String cot) {
        this.cot = cot;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}

package com.example.model;

public class TranHisDetails {
    private int MaDH;
    private int MaSP;
    private int SoLuong;
    private double Dongia;
    private double TongTien;
    private String TenSP;


    public TranHisDetails(int maDH, int maSP, int soLuong, double dongia, double tongTien, String tenSP) {
        MaDH = maDH;
        MaSP = maSP;
        SoLuong = soLuong;
        Dongia = dongia;
        TongTien = tongTien;
        TenSP = tenSP;
    }

    public int getMaDH() {
        return MaDH;
    }

    public void setMaDH(int maDH) {
        MaDH = maDH;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public double getDongia() {
        return Dongia;
    }

    public void setDongia(double dongia) {
        Dongia = dongia;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double tongTien) {
        TongTien = tongTien;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }
}

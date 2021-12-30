package com.example.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int MaSP;
    private String TenSP;
    private Double Gia;
    private String Mota;
    private String ChiTiet;
    private String Anh;
    private int Soluongton;
    private int MaDM;
    private int Status;

    public SanPham(int maSP, String tenSP, Double gia, String mota, String chiTiet, String anh, int soluongton, int maDM, int status) {
        MaSP = maSP;
        TenSP = tenSP;
        Gia = gia;
        Mota = mota;
        ChiTiet = chiTiet;
        Anh = anh;
        Soluongton = soluongton;
        MaDM = maDM;
        Status = status;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double gia) {
        Gia = gia;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getChiTiet() {
        return ChiTiet;
    }

    public void setChiTiet(String chiTiet) {
        ChiTiet = chiTiet;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getSoluongton() {
        return Soluongton;
    }

    public void setSoluongton(int soluongton) {
        Soluongton = soluongton;
    }

    public int getMaDM() {
        return MaDM;
    }

    public void setMaDM(int maDM) {
        MaDM = maDM;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}

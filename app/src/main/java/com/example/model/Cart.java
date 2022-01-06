package com.example.model;

public class Cart {
    private int ID;
    private String tenSp;
    private int SoLuong;
    private int Soluongton;
    private double tien;
    private String Hinh;

    public Cart(int ID, String tenSp, int soLuong, int soluongton, double tien, String hinh) {
        this.ID = ID;
        this.tenSp = tenSp;
        SoLuong = soLuong;
        Soluongton = soluongton;
        this.tien = tien;
        Hinh = hinh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getSoluongton() {
        return Soluongton;
    }

    public void setSoluongton(int soluongton) {
        Soluongton = soluongton;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}

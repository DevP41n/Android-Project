package com.example.model;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Time;
import java.util.Date;

public class DonDatHang {
    int MaDonHang;
    Boolean Dathanhtoan;
    Boolean Tinhtranggiaohang;
    Date Ngaydat;
    Time Giodat;
    int MaKH;
    String TenKH;
    String SDTKH;
    String DiachiKH;
    String Email;

    public DonDatHang(int maDonHang, Boolean dathanhtoan, Boolean tinhtranggiaohang, Date ngaydat, Time giodat, int maKH, String tenKH, String SDTKH, String diachiKH, String email) {
        MaDonHang = maDonHang;
        Dathanhtoan = dathanhtoan;
        Tinhtranggiaohang = tinhtranggiaohang;
        Ngaydat = ngaydat;
        Giodat = giodat;
        MaKH = maKH;
        TenKH = tenKH;
        this.SDTKH = SDTKH;
        DiachiKH = diachiKH;
        Email = email;
    }

    public int getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        MaDonHang = maDonHang;
    }

    public Boolean getDathanhtoan() {
        return Dathanhtoan;
    }

    public void setDathanhtoan(Boolean dathanhtoan) {
        Dathanhtoan = dathanhtoan;
    }

    public Boolean getTinhtranggiaohang() {
        return Tinhtranggiaohang;
    }

    public void setTinhtranggiaohang(Boolean tinhtranggiaohang) {
        Tinhtranggiaohang = tinhtranggiaohang;
    }

    public Date getNgaydat() {
        return Ngaydat;
    }

    public void setNgaydat(Date ngaydat) {
        Ngaydat = ngaydat;
    }

    public Time getGiodat() {
        return Giodat;
    }

    public void setGiodat(Time giodat) {
        Giodat = giodat;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getSDTKH() {
        return SDTKH;
    }

    public void setSDTKH(String SDTKH) {
        this.SDTKH = SDTKH;
    }

    public String getDiachiKH() {
        return DiachiKH;
    }

    public void setDiachiKH(String diachiKH) {
        DiachiKH = diachiKH;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

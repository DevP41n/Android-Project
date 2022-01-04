package com.example.model;

public class KhachHang {
    private int MAKH;
    private String HoTen;
    private String TaiKhoan;
    private String MatKhau;
    private String Email;
    private String DiaChiKH;
    private String DienThoaiKH;

    public KhachHang(int MAKH, String hoTen, String taiKhoan, String matKhau, String email, String diaChiKH, String dienThoaiKH) {
        this.MAKH = MAKH;
        HoTen = hoTen;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        Email = email;
        DiaChiKH = diaChiKH;
        DienThoaiKH = dienThoaiKH;
    }

    public int getMAKH() {
        return MAKH;
    }

    public void setMAKH(int MAKH) {
        this.MAKH = MAKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDiaChiKH() {
        return DiaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        DiaChiKH = diaChiKH;
    }

    public String getDienThoaiKH() {
        return DienThoaiKH;
    }

    public void setDienThoaiKH(String dienThoaiKH) {
        DienThoaiKH = dienThoaiKH;
    }
}

package com.example.model;

public class LoaiSanPham {
    private int MaDM;
    private String TenDM;
    private int Status;

    public LoaiSanPham(int maDM, String tenDM, int status) {
        MaDM = maDM;
        TenDM = tenDM;
        Status = status;
    }

    public int getMaDM() {
        return MaDM;
    }

    public void setMaDM(int maDM) {
        MaDM = maDM;
    }

    public String getTenDM() {
        return TenDM;
    }

    public void setTenDM(String tenDM) {
        TenDM = tenDM;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}

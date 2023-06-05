package com.example.duan1.SQLite.Model;

public class LoaiThucAn {
    private int maLTA;
    private String tenLTA;

    public LoaiThucAn(int maLTA, String tenLTA) {
        this.maLTA = maLTA;
        this.tenLTA = tenLTA;
    }

    public LoaiThucAn() {
    }

    public int getMaLTA() {
        return maLTA;
    }

    public void setMaLTA(int maLTA) {
        this.maLTA = maLTA;
    }

    public String getTenLTA() {
        return tenLTA;
    }

    public void setTenLTA(String tenLTA) {
        this.tenLTA = tenLTA;
    }
}

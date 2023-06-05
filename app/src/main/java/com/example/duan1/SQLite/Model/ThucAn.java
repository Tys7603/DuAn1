package com.example.duan1.SQLite.Model;

public class ThucAn {
    private int maTA, maLTA, giaTien, hinh;
    private String tenTA;


    public ThucAn(int maTA, int maLTA, int gia, String tenTA) {
        this.maTA = maTA;
        this.maLTA = maLTA;
        this.giaTien = gia;
        this.tenTA = tenTA;
    }

    public ThucAn() {
    }

    public int getGiaTien() {
        return giaTien;
    }

    public ThucAn(int hinh) {
        this.hinh = hinh;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public int getMaTA() {
        return maTA;
    }

    public void setMaTA(int maTA) {
        this.maTA = maTA;
    }

    public int getMaLTA() {
        return maLTA;
    }

    public void setMaLTA(int maLTA) {
        this.maLTA = maLTA;
    }

    public String getTenTA() {
        return tenTA;
    }

    public void setTenTA(String tenTA) {
        this.tenTA = tenTA;
    }
}

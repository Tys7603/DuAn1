package com.example.duan1.SQLite.Model;

import java.util.Date;

public class PhieuMua {
    private int maPM, maND, maTA, soLuong, giaMua;
    private String tenPM;
    private Date ngayMua;

    public PhieuMua(int maPM, int maND, int maTA, int soLuong, int giaMua, String tenPM, Date ngayMua) {
        this.maPM = maPM;
        this.maND = maND;
        this.maTA = maTA;
        this.soLuong = soLuong;
        this.giaMua = giaMua;
        this.tenPM = tenPM;
        this.ngayMua = ngayMua;
    }

    public PhieuMua() {
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public int getMaTA() {
        return maTA;
    }

    public void setMaTA(int maTA) {
        this.maTA = maTA;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(int giaMua) {
        this.giaMua = giaMua;
    }

    public String getTenPM() {
        return tenPM;
    }

    public void setTenPM(String tenPM) {
        this.tenPM = tenPM;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }
}

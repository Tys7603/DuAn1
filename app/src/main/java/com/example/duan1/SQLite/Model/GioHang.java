package com.example.duan1.SQLite.Model;

import java.io.Serializable;

public class GioHang implements Serializable {
    private String tenGH;
    private int gia, soLuong, maTA, maGH, hinh;

    public GioHang(String tenGH, int gia, int soLuong, int maTA, int maGH) {
        this.tenGH = tenGH;
        this.gia = gia;
        this.soLuong = soLuong;
        this.maTA = maTA;
        this.maGH = maGH;
    }

    public GioHang(int hinh) {
        this.hinh = hinh;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public GioHang() {
    }

    public String getTenGH() {
        return tenGH;
    }

    public void setTenGH(String tenGH) {
        this.tenGH = tenGH;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaTA() {
        return maTA;
    }

    public void setMaTA(int maTA) {
        this.maTA = maTA;
    }

    public int getMaGH() {
        return maGH;
    }

    public void setMaGH(int maGH) {
        this.maGH = maGH;
    }
}

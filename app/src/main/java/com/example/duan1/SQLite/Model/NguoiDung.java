package com.example.duan1.SQLite.Model;

import android.net.Uri;

public class NguoiDung {
    private String userName, passWord, tenND, diaChi;
    private int tien;
    private Uri hinh;

    public NguoiDung(String userName, String passWord, String tenND, String diaChi, int tien) {
        this.userName = userName;
        this.passWord = passWord;
        this.tenND = tenND;
        this.diaChi = diaChi;
        this.tien = tien;
    }

    public NguoiDung(Uri hinh) {
        this.hinh = hinh;
    }

    public NguoiDung() {
    }

    public int getTien() {
        return tien;
    }

    public Uri getHinh() {
        return hinh;
    }

    public void setHinh(Uri hinh) {
        this.hinh = hinh;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}

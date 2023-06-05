package com.example.duan1.SQLite.Model;

public class Admin {
    private String userName, passWord, tenAdmin;
    public Admin( String userName, String passWord, String tenAdmin) {
        this.userName = userName;
        this.passWord = passWord;
        this.tenAdmin = tenAdmin;
    }

    public Admin() {
    }

    public String getTenAdmin() {
        return tenAdmin;
    }

    public void setTenAdmin(String tenAdmin) {
        this.tenAdmin = tenAdmin;
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

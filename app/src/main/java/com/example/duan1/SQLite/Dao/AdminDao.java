package com.example.duan1.SQLite.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.SQLite.DbHelper.DbHelper;
import com.example.duan1.SQLite.Model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    Context context;
    SQLiteDatabase db;
    public AdminDao(Context context) {
        this.context = context;
        this.db = new DbHelper(context).getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Admin> getData(String sql , String ... selections){
        List<Admin> list = new ArrayList<>();


        Cursor cursor = db.rawQuery(sql, selections);

        while (cursor.moveToNext()){
            Admin admin = new Admin();
            admin.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            admin.setPassWord(cursor.getString(cursor.getColumnIndex("passWord")));
            admin.setTenAdmin(cursor.getString(cursor.getColumnIndex("tenAdmin")));

            list.add(admin);
        }


        return list;

    }

    // check login
    public boolean checkLogin(String user , String pass){

        String sql = "SELECT * FROM Admin WHERE userName = ? AND passWord = ? ";

        List<Admin> list = getData(sql , user, pass);

        if(list.size() == 0){
            return false;
        }
        return true;
    }


}

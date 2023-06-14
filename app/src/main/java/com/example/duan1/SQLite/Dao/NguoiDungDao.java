package com.example.duan1.SQLite.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.SQLite.DbHelper.DbHelper;
import com.example.duan1.SQLite.Model.Admin;
import com.example.duan1.SQLite.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDao {
    Context context;
    SQLiteDatabase db;

    public NguoiDungDao(Context context) {
        this.context = context;
        this.db = new DbHelper(context).getWritableDatabase();
    }

    public long insert(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("tenND", nguoiDung.getTenND());
        values.put("userName", nguoiDung.getUserName());
        values.put("passWord", nguoiDung.getPassWord());
        values.put("tien", 0);
        values.put("diaChi" , "");
        return db.insert("NguoiDung", null, values);
    }

    public int updates(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("passWord", nguoiDung.getPassWord());

        return db.update("NguoiDung",values,"tenND = ?" , new String[]{String.valueOf(nguoiDung.getTenND())});
    }

    public int updatesDC(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("diaChi", nguoiDung.getDiaChi());

        return db.update("NguoiDung",values,"userName = ?" , new String[]{String.valueOf(nguoiDung.getUserName())});
    }

    public int updatesTien(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("tien", nguoiDung.getTien());

        return db.update("NguoiDung",values,"userName = ?" , new String[]{String.valueOf(nguoiDung.getUserName())});
    }

    public int updatesTen(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("tenND", nguoiDung.getTenND());

        return db.update("NguoiDung",values,"userName = ?" , new String[]{String.valueOf(nguoiDung.getUserName())});
    }

    @SuppressLint("Range")
    public List<NguoiDung> getData(String sql , String ... selections){
        List<NguoiDung> list = new ArrayList<>();


        Cursor cursor = db.rawQuery(sql, selections);

        while (cursor.moveToNext()){
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            nguoiDung.setPassWord(cursor.getString(cursor.getColumnIndex("passWord")));
            nguoiDung.setTenND(cursor.getString(cursor.getColumnIndex("tenND")));
            nguoiDung.setDiaChi(cursor.getString(cursor.getColumnIndex("diaChi")));
            nguoiDung.setTien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tien"))));
            list.add(nguoiDung);
        }


        return list;

    }


    public List<NguoiDung> getDSND (){
        String sql = "SELECT * FROM NguoiDung";
        return getData(sql);
    }


    // check login
    public boolean checkLogin(String user , String pass){

        String sql = "SELECT * FROM NguoiDung WHERE userName = ? AND passWord = ? ";

        List<NguoiDung> list = getData(sql , user, pass);

        if(list.size() == 0){
            return false;
        }
        return true;
    }

    public NguoiDung getId(String id){

        String sql = " SELECT * FROM NguoiDung WHERE userName = ? ";
        List<NguoiDung> list = getData(sql ,id);
        return list.get(0);

    }

}

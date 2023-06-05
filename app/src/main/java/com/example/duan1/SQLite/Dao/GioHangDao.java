package com.example.duan1.SQLite.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.SQLite.DbHelper.DbHelper;
import com.example.duan1.SQLite.Model.Admin;
import com.example.duan1.SQLite.Model.GioHang;
import com.example.duan1.SQLite.Model.NguoiDung;
import com.example.duan1.SQLite.Model.ThucAn;

import java.util.ArrayList;
import java.util.List;

public class GioHangDao {
    Context context;
    SQLiteDatabase db;

    public GioHangDao(Context context) {
        this.context = context;
        this.db = new DbHelper(context).getWritableDatabase();
    }


    public long insert(GioHang gioHang){
        ContentValues values = new ContentValues();
        values.put("tenGH", gioHang.getTenGH());
        values.put("giaTien", gioHang.getGia());
        values.put("soLuong", gioHang.getSoLuong());
        values.put("maTA", gioHang.getMaTA());

        return db.insert("GioHang", null, values);
    }

    public int updates(GioHang gioHang){
        ContentValues values = new ContentValues();
        values.put("soLuong", gioHang.getSoLuong());
        return db.update("GioHang",values,"maGH = ?" , new String[]{String.valueOf(gioHang.getMaGH())});
    }

    public int delete(GioHang gioHang){
        return db.delete("GioHang", " maGH = ? ",new String[]{String.valueOf(gioHang.getMaGH())});
    }

    @SuppressLint("Range")
    public List<GioHang> getData(String sql , String ... selections){
        List<GioHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selections);

        while (cursor.moveToNext()){
            GioHang gioHang = new GioHang();
            gioHang.setMaGH(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maGH"))));
            gioHang.setMaTA(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTA"))));
            gioHang.setTenGH(cursor.getString(cursor.getColumnIndex("tenGH")));
            gioHang.setGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaTien"))));
            gioHang.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));

            list.add(gioHang);
        }


        return list;

    }

    public List<GioHang> getDSGH(){
        String sql = "SELECT * FROM GioHang";
        List<GioHang> list = getData(sql);
        return list;
    }




}

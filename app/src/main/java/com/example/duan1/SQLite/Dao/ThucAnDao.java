package com.example.duan1.SQLite.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.SQLite.DbHelper.DbHelper;
import com.example.duan1.SQLite.Model.Admin;
import com.example.duan1.SQLite.Model.NguoiDung;
import com.example.duan1.SQLite.Model.ThucAn;

import java.util.ArrayList;
import java.util.List;

public class ThucAnDao {
    Context context;
    SQLiteDatabase db;

    public ThucAnDao(Context context) {
        this.context = context;
        this.db = new DbHelper(context).getWritableDatabase();
    }

    public long insert(ThucAn thucAn){
        ContentValues values = new ContentValues();
        values.put("tenTA", thucAn.getTenTA());
        values.put("giaTien", thucAn.getGiaTien());
        values.put("maLTA", thucAn.getMaLTA());

        return db.insert("ThucAn", null, values);
    }

    public int updates(ThucAn thucAn){
        ContentValues values = new ContentValues();
        values.put("tenTA", thucAn.getTenTA());
        values.put("giaTien", thucAn.getGiaTien());
        values.put("maLTA", thucAn.getMaLTA());

        return db.update("ThucAn",values,"maTA = ?" , new String[]{String.valueOf(thucAn.getMaTA())});
    }

    public int delete(String maTA){
        return db.delete("ThucAn", "maTA = ?", new String[]{String.valueOf(maTA)});
    }

    @SuppressLint("Range")
    public List<ThucAn> getData(String sql , String ... selections){
        List<ThucAn> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selections);

        while (cursor.moveToNext()){
            ThucAn thucAn = new ThucAn();
            thucAn.setMaTA(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTA"))));
            thucAn.setTenTA(cursor.getString(cursor.getColumnIndex("tenTA")));
            thucAn.setGiaTien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaTien"))));
            thucAn.setMaLTA(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLTA"))));
            list.add(thucAn);
        }

        return list;
    }

    public ThucAn getId(String id){
        String sql = "SELECT * FROM ThucAn WHERE maTA = ? ";
        List<ThucAn> list = getData(sql,id);
        return list.get(0);
    }

    public List<ThucAn> getDSTA(String id){
        String sql = "SELECT * FROM ThucAn WHERE maLTA = ? ";
        List<ThucAn> list = getData(sql,id);
        return list;
    }

    public List<ThucAn> getTop10(){
        List<ThucAn> list = new ArrayList<>();
        String sql = "SELECT maTA, soLuong FROM PhieuMua ORDER BY soLuong DESC LIMIT 5";
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            ThucAn thucAnItem = new ThucAn();
            @SuppressLint("Range")
            ThucAn thucAn = getId(cursor.getString(cursor.getColumnIndex("maTA")));
            thucAnItem.setTenTA(thucAn.getTenTA());
            thucAnItem.setGiaTien(thucAn.getGiaTien());
            thucAnItem.setMaTA(thucAn.getMaTA());
            thucAnItem.setMaLTA(thucAn.getMaLTA());
            list.add(thucAnItem);
        }
        return list;
    }
}

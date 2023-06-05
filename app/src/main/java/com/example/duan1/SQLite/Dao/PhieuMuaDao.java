package com.example.duan1.SQLite.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.SQLite.DbHelper.DbHelper;
import com.example.duan1.SQLite.Model.PhieuMua;
import com.example.duan1.SQLite.Model.ThucAn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuaDao {
    Context context;
    SQLiteDatabase db;
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    public PhieuMuaDao(Context context) {
        this.context = context;
        this.db = new DbHelper(context).getWritableDatabase();
    }

    public long insert(PhieuMua phieuMua){
        ContentValues values = new ContentValues();

        values.put("maND", phieuMua.getMaND());
        values.put("maTA", phieuMua.getMaTA());
        values.put("tenPM", phieuMua.getTenPM());
        values.put("soLuong", phieuMua.getSoLuong());
        values.put("ngayMua", format.format(phieuMua.getNgayMua()));
        values.put("giaMua", phieuMua.getGiaMua());

        return db.insert("PhieuMua", null, values);
    }

    public int updates(PhieuMua phieuMua){
        ContentValues values = new ContentValues();

        values.put("maND", phieuMua.getMaND());
        values.put("maTA", phieuMua.getMaTA());
        values.put("tenPM", phieuMua.getTenPM());
        values.put("soLuong", phieuMua.getSoLuong());
        values.put("ngayMua",format.format(phieuMua.getNgayMua()));
        values.put("giaMua", phieuMua.getGiaMua());

        return db.update("PhieuMua",values,"maPM = ?" , new String[]{String.valueOf(phieuMua.getMaPM())});
    }

    public int updatesSoLuong(PhieuMua phieuMua){
        ContentValues values = new ContentValues();
        values.put("soLuong", phieuMua.getSoLuong());
        return db.update("PhieuMua",values,"maPM = ?" , new String[]{String.valueOf(phieuMua.getMaPM())});
    }

    @SuppressLint("Range")
    public List<PhieuMua> getData(String sql , String ... selections){
        List<PhieuMua> list = new ArrayList<>();


        Cursor cursor = db.rawQuery(sql, selections);

        while (cursor.moveToNext()){
            PhieuMua phieuMua = new PhieuMua();
            phieuMua.setMaPM(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPM"))));
            phieuMua.setMaND(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maND"))));
            phieuMua.setMaTA(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTA"))));
            phieuMua.setTenPM((cursor.getString(cursor.getColumnIndex("tenPM"))));
            phieuMua.setGiaMua(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaMua"))));
            phieuMua.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));

            try {
                phieuMua.setNgayMua(format.parse(cursor.getString(cursor.getColumnIndex("ngayMua"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            list.add(phieuMua);
        }


        return list;

    }

    public List<PhieuMua> getDSPM (){
        String sql = "SELECT * FROM PhieuMua";
        return getData(sql);
    }

    @SuppressLint("Range")
    public List<PhieuMua> getTop5(){
        List<PhieuMua> list = new ArrayList<>();

        String sql = "SELECT * FROM PhieuMua ORDER BY soLuong DESC LIMIT 5";
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            PhieuMua phieuMua = new PhieuMua();
            phieuMua.setTenPM(cursor.getString(cursor.getColumnIndex("tenPM")));
            phieuMua.setGiaMua(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaMua"))));
            phieuMua.setMaTA(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTA"))));
            list.add(phieuMua);
        }

        return list;
    }
}

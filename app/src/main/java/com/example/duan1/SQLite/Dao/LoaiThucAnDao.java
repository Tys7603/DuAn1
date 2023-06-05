package com.example.duan1.SQLite.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.SQLite.DbHelper.DbHelper;
import com.example.duan1.SQLite.Model.LoaiThucAn;

import java.util.ArrayList;
import java.util.List;

public class LoaiThucAnDao {
    Context context;
    SQLiteDatabase db;

    public LoaiThucAnDao(Context context) {
        this.context = context;
        this.db = new DbHelper(context).getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<LoaiThucAn> getData(String sql , String ... selections){
        List<LoaiThucAn> list = new ArrayList<>();


        Cursor cursor = db.rawQuery(sql, selections);

        while (cursor.moveToNext()){
            LoaiThucAn loaiThucAn = new LoaiThucAn();
            loaiThucAn.setMaLTA(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLTA"))));
            loaiThucAn.setTenLTA(cursor.getString(cursor.getColumnIndex("tenLTA")));

            list.add(loaiThucAn);
        }

        return list;

    }

    public List<LoaiThucAn> getDSLTA (){
        String sql = "SELECT * FROM LoaiThucAn";
        return getData(sql);
    }

    public LoaiThucAn getID(String id){
        String sql = "SELECT * FROM LoaiThucAn WHERE maLTA = ? ";
        List<LoaiThucAn> list = getData(sql,id);
        return list.get(0);
    }
}

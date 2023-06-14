package com.example.duan1.SQLite.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "Order Food", null, 1);
    }

    public final String inToAdmin = "INSERT INTO Admin VALUES" +
            "('admin' , '123' , 'XuanLong' )";

    public final String inToNguoiDung = "INSERT INTO NguoiDung VALUES" +
            "( 'tys' , '123' , '144 Nguyễn Luong Bằng', '1000' , 'Tys' ), " +
            "(  'long' , '123' , '144 Nguyễn Luong Bằng', '1000' , 'Long' ) ";

    public final String inToLoaiThucAn = "INSERT INTO LoaiThucAn VALUES" +
            "( '1' , 'Pizza' ), " +
            "( '2' , 'Hamburger' ), " +
            "( '3' , 'Bánh Mỳ' ), " +
            "( '4' , 'Nước' ), " +
            "( '5' , 'Bánh Ngọt' )," +
            "( '6' , 'Xôi' ) ";

    public final String inToThucAn = "INSERT INTO ThucAn VALUES" +
            "( '1' , 'Pizza Hawaii' , '50' , '1' ), " +
            "( '2' , 'Pizza Bắp Phô Mai ' , '40' , '1' )," +
            "( '3' , 'BURGER Bò Phô Mai ' , '50' , '2' ), " +
            "( '4' , 'BURGER Gà KFC' , '40' , '2' ), " +
            "( '5' , 'Bánh Mỳ Trứng' , '10' , '3' ), " +
            "( '6' , 'Bánh Mỳ Thập Cẩm' , '10' , '3' ), " +
            "( '7' , 'Coca-Cola' , '5' , '4' ), " +
            "( '8' , 'Pepsi' , '5' , '4' ), " +
            "( '9' , 'Bánh Táo Mỹ' , '20' , '5' ), " +
            "( '10' , 'Bánh Macaron Pháp' , '25' , '5' ), " +
            "( '11' , 'Xôi Xéo' , '7' , '6' ), " +
            "( '12' , 'Xôi Gà' , '7' , '6' )";



    public final String inToPhieuMua = "INSERT INTO PhieuMua VALUES" +
            "( '1' , '1' , '1' , 'Pizza Hawaii', '5', '2022/11/25', '252' ) ," +
            "( '2' , '1' , '3' , 'BURGER Bò Phô Mai', '4', '2022/11/25', '202' ) ," +
            "( '3' , '1' , '5' , 'Bánh Mỳ Trứng', '3', '2022/11/25', '32' ) ," +
            "( '4' , '1' , '7' , 'Coca-Cola', '2', '2022/11/25', '10' ) ," +
            "( '5' , '1' , '9' , 'Bánh Táo Mỹ', '1', '2022/11/25', '22' ) ," +
            "( '6' , '1' , '12' , 'Xôi Gà', '1', '2022/11/25', '9' ) " ;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String admin = "CREATE TABLE Admin(" +
                "userName text primary key, " +
                "passWord text, " +
                "tenAdmin text " +
                ")";
        sqLiteDatabase.execSQL(admin);
        sqLiteDatabase.execSQL(inToAdmin);

        String user = "CREATE TABLE NguoiDung (" +
                "userName text primary key," +
                "passWord text, " +
                "diaChi text, " +
                "tien integer, " +
                "tenND text " +
                ")";
        sqLiteDatabase.execSQL(user);
        sqLiteDatabase.execSQL(inToNguoiDung);

        String LoaiThucAn = "CREATE TABLE LoaiThucAn (" +
                "maLTA integer primary key autoincrement," +
                "tenLTA text" +
                ")";
        sqLiteDatabase.execSQL(LoaiThucAn);
        sqLiteDatabase.execSQL(inToLoaiThucAn);

        String ThucAn = "CREATE TABLE ThucAn (" +
                "maTA integer primary key autoincrement, " +
                "tenTA text," +
                "giaTien integer," +
                "maLTA integer,  " +
                "FOREIGN KEY (maLTA) REFERENCES LoaiThucAn (maLTA)" +
                ")";
        sqLiteDatabase.execSQL(ThucAn);
        sqLiteDatabase.execSQL(inToThucAn);

        String GioHang =  "CREATE TABLE GioHang (" +
                "maGH integer primary key autoincrement, " +
                "tenGH text," +
                "giaTien integer," +
                "soLuong integer," +
                "maTA integer,  " +
                "FOREIGN KEY (maTA) REFERENCES ThucAn (maTA)" +
                ")";
        sqLiteDatabase.execSQL(GioHang);


        String PhieuMua = "CREATE TABLE PhieuMua (" +
                "maPM integer primary key autoincrement," +
                "maND integer, " +
                "maTA integer, " +
                "tenPM text, " +
                "soLuong integer, " +
                "ngayMua date, " +
                "giaMua integer," +
                "Foreign key (maND) references NguoiDung (maND), " +
                "Foreign key (maTA) references ThucAn (maTA) " +
                ")";
        sqLiteDatabase.execSQL(PhieuMua);
        sqLiteDatabase.execSQL(inToPhieuMua);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS Admin");
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS NguoiDung");
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS LoaiThucAn");
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS ThucAn");
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS PhieuMua");

        onCreate(sqLiteDatabase);
    }
}

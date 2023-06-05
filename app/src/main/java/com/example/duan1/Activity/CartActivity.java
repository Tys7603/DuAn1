package com.example.duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.Adapter.RecyclerViewGh;
import com.example.duan1.Interface.IClickItemListenner;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.GioHangDao;
import com.example.duan1.SQLite.Dao.NguoiDungDao;
import com.example.duan1.SQLite.Dao.PhieuMuaDao;
import com.example.duan1.SQLite.Model.GioHang;
import com.example.duan1.SQLite.Model.NguoiDung;
import com.example.duan1.SQLite.Model.PhieuMua;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ImageView btnHome, btnToi, btnDH, btnBell;
    FloatingActionButton fb;
    RecyclerView rv;
    GioHangDao gioHangDao;
    List<GioHang> list;
    TextView tvTong, tvToan, tvTax;
    RecyclerViewGh recyclerViewGh;
    CardView btnAdd;
    PhieuMuaDao phieuMuaDao;
    NguoiDungDao nguoiDungDao;
    int tongMuc;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        // anh xa
        tvTong = findViewById(R.id.tvTong);
        tvTax = findViewById(R.id.tvTax);
        tvToan = findViewById(R.id.tvToanBo);
        rv = findViewById(R.id.rvGH);
        btnAdd = findViewById(R.id.btnAdd_Gh);

        init();

        // tao doi tuong
        nguoiDungDao = new NguoiDungDao(this);
        gioHangDao = new GioHangDao(this);
        phieuMuaDao = new PhieuMuaDao(this);
        list = gioHangDao.getDSGH();
        recyclerViewGh = new RecyclerViewGh(list, this, new IClickItemListenner() {
            @Override
            public void OnClickItemTong(int tong) {
                tongMuc = tong;
                tvTong.setText("$ " + tongMuc);
                if (recyclerViewGh.getItemCount() >= 1) {
                    tvTax.setText("2");
                }else {
                    tvTax.setText("0");
                }
                int tax = Integer.parseInt(tvTax.getText().toString());
                int toan = tongMuc + tax;
                tvToan.setText( "$ " + toan );
            }
        });


        // xet gia
        tongMuc = recyclerViewGh.tongGia();
        tvTong.setText("$ " + tongMuc);
        if (recyclerViewGh.getItemCount() >= 1) {
            tvTax.setText("2");
        }else {
            tvTax.setText("0");
        }
        int tax = Integer.parseInt(tvTax.getText().toString());
        int toan = tongMuc + tax;
        tvToan.setText( "$ " + toan );



        // xoa gio hàng
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        GioHang gioHang = list.get(viewHolder.getAdapterPosition());
                        if(gioHangDao.delete(gioHang) > 0){
                            Toast.makeText(getApplicationContext(), "Thức ăn đã được xóa", Toast.LENGTH_SHORT).show();

                            list = gioHangDao.getDSGH();
                            recyclerViewGh = new RecyclerViewGh(list,getApplication());

                            // xet gia
                            int tongMuc = recyclerViewGh.tongGia();
                            tvTong.setText("$ " + tongMuc);

                           if (recyclerViewGh.getItemCount() >= 1){
                               tvTax.setText("2");
                           }else {
                               tvTax.setText("0");
                           }
                            int tax = Integer.parseInt(tvTax.getText().toString());
                            int toan = tongMuc + tax;
                            tvToan.setText( "$ " + toan );

                            loadData();
                        }else {
                            Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
        itemTouchHelper.attachToRecyclerView(rv);

        // them vao phieumua

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = false;
                SharedPreferences sharedPreferences = getSharedPreferences("remember",MODE_PRIVATE);
                String user = sharedPreferences.getString("user","");
                NguoiDung nguoiDung = nguoiDungDao.getId(user);
                int tien = nguoiDung.getTien();
                int tienCl =  tien - toan ;
                if(tienCl < 0 ){
                    Toast.makeText(CartActivity.this, "Số tiền trong tài khoản không đủ", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i = 0 ; i<list.size() ; i++){
                        PhieuMua phieuMua = new PhieuMua();
                        phieuMua.setTenPM(list.get(i).getTenGH());
                        phieuMua.setSoLuong(list.get(i).getSoLuong());
                        phieuMua.setMaTA(list.get(i).getMaTA());
                        phieuMua.setGiaMua(list.get(i).getGia());
                        phieuMua.setNgayMua(new Date());

                        if (phieuMuaDao.insert(phieuMua) > 0 ){
                            gioHangDao.delete(list.get(i));
                            check = true;
                        }
                    }

                    if (check){
                        list = gioHangDao.getDSGH();
                        recyclerViewGh = new RecyclerViewGh(list,getApplication());
                        Toast.makeText(CartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        tvTax.setText("0");
                        tvToan.setText("$ 0");
                        tvTong.setText("$ 0");
                        loadData();

                        // update tiền
                        NguoiDung nd = new NguoiDung();
                        nd.setUserName(user);
                        nd.setTien(tienCl);
                        nguoiDungDao.updatesTien(nd);

                    }else {
                        Toast.makeText(CartActivity.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        loadData();
    }

    public void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(recyclerViewGh);
    }

    public void init(){
        btnHome = findViewById(R.id.ivHomeCart);
        btnDH = findViewById(R.id.ivDHCart);
        btnToi = findViewById(R.id.ivProfileCart);
        btnBell = findViewById(R.id.ivBellCart);
        fb = findViewById(R.id.fbCart);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });

        btnDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, DonHangActivity.class));
            }
        });
        btnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, ThongBaoActivity.class));
            }
        });
        btnToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, ProfileActivity.class));
            }
        });
    }
}
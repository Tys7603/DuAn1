package com.example.duan1.Activity.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1.Activity.AdapterAdmin.ViewPeger2AdapterAD;
import com.example.duan1.Activity.CartActivity;
import com.example.duan1.Activity.DonHangActivity;
import com.example.duan1.Activity.ProfileActivity;
import com.example.duan1.Activity.ThongBaoActivity;
import com.example.duan1.Adapter.RycyclerViewHot;
import com.example.duan1.Adapter.ViewPeger2Adapter;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.PhieuMuaDao;
import com.example.duan1.SQLite.Dao.ThucAnDao;
import com.example.duan1.SQLite.Model.PhieuMua;
import com.example.duan1.SQLite.Model.ThucAn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    RecyclerView rv;
    ViewPager2 viewPager2;
    ThucAnDao thucAnDao;
    PhieuMuaDao phieuMuaDao;
    ImageView btnProfile, btnHome, btnDonhang, btnBell;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);

        btnProfile = findViewById(R.id.ivProfile);
        btnHome = findViewById(R.id.ivHome);
        btnDonhang = findViewById(R.id.ivDonHang);
        btnBell = findViewById(R.id.ivBell);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ProfileActivity.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AdminActivity.class));
            }
        });

        btnDonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, DonHangActivity.class));
            }
        });
        btnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ThongBaoActivity.class));
            }
        });


        viewPager2 = findViewById(R.id.viewPager2);
        ViewPeger2AdapterAD adapter = new ViewPeger2AdapterAD(this);
        viewPager2.setAdapter(adapter);

        thucAnDao = new ThucAnDao(this);
        phieuMuaDao = new PhieuMuaDao(this);
        loadData();

    }

    public void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv.setLayoutManager(layoutManager);
        List<PhieuMua> list = phieuMuaDao.getTop5();
        RycyclerViewHot adapter = new RycyclerViewHot(this,list);
        rv.setAdapter(adapter);
    }
}
package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.duan1.Adapter.RecyclerViewAdapterDH;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.PhieuMuaDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DonHangActivity extends AppCompatActivity {
    RecyclerView rv;
    PhieuMuaDao phieuMuaDao;
    ImageView btnHome, btnToi, btnDH, btnBell;
    FloatingActionButton fb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);

        rv = findViewById(R.id.rvDH);
        phieuMuaDao = new PhieuMuaDao(this);

        btnHome = findViewById(R.id.ivHomeDH);
        btnDH = findViewById(R.id.ivDonHangDH);
        btnToi = findViewById(R.id.ivProfileDH);
        btnBell = findViewById(R.id.ivBellDH);
        fb = findViewById(R.id.fbDH);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonHangActivity.this, CartActivity.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonHangActivity.this, MainActivity.class));
            }
        });

        btnDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonHangActivity.this, DonHangActivity.class));
            }
        });
        btnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonHangActivity.this, ThongBaoActivity.class));
            }
        });
        btnToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonHangActivity.this, ProfileActivity.class));
            }
        });

        loatData();
    }

    private void loatData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerViewAdapterDH adapterDH = new RecyclerViewAdapterDH(this,phieuMuaDao.getDSPM());

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapterDH);
    }
}
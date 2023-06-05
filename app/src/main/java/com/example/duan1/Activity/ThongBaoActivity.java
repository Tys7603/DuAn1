package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ThongBaoActivity extends AppCompatActivity {

    ImageView btnHome, btnToi, btnDH, btnBell;
    FloatingActionButton fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        btnHome = findViewById(R.id.ivHomeBell);
        btnDH = findViewById(R.id.ivDHBell);
        btnToi = findViewById(R.id.ivProfileBell);
        btnBell = findViewById(R.id.ivBellBell);
        fb = findViewById(R.id.fbBell);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongBaoActivity.this, CartActivity.class));
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongBaoActivity.this, MainActivity.class));
            }
        });

        btnDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongBaoActivity.this, DonHangActivity.class));
            }
        });
        btnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongBaoActivity.this, ThongBaoActivity.class));
            }
        });
        btnToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongBaoActivity.this, ProfileActivity.class));
            }
        });
    }
}
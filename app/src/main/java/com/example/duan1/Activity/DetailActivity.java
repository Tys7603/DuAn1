package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.GioHangDao;
import com.example.duan1.SQLite.Dao.LoaiThucAnDao;
import com.example.duan1.SQLite.Dao.ThucAnDao;
import com.example.duan1.SQLite.Model.GioHang;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView tvTile, tvGia, tvSoLuong, btnAdd, btnDel;
    ImageView ivDetail;
    CardView btnAddCart;
    GioHangDao gioHangDao;
    ThucAnDao thucAnDao;
    LoaiThucAnDao loaiThucAnDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        gioHangDao = new GioHangDao(this);
        thucAnDao = new ThucAnDao(this);
        loaiThucAnDao = new LoaiThucAnDao(this);

        tvTile = findViewById(R.id.tv_tileDetail);
        tvGia = findViewById(R.id.tv_giaDetail);
        tvSoLuong = findViewById(R.id.tv_soluongDetail);
        btnAddCart = findViewById(R.id.btnAdd_Detail);
        btnAdd = findViewById(R.id.tv_addDetail);
        btnDel = findViewById(R.id.tv_delDetail);
        ivDetail = findViewById(R.id.iv_detail);
        Intent intent = getIntent();

        tvTile.setText(intent.getStringExtra("tile"));
        tvGia.setText( "$ " + intent.getIntExtra("gia",0));

        // add vao gio hang
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate() > 0){
                    String tile =  intent.getStringExtra("tile");
                    int gia = intent.getIntExtra("gia",0);
                    int soLuong = Integer.parseInt(tvSoLuong.getText().toString());
                    int maTA = intent.getIntExtra("maTA",0);
                    int id = intent.getIntExtra("id",0);


                    GioHang gioHang = new GioHang();
                    gioHang.setTenGH(tile);
                    gioHang.setGia(gia);
                    gioHang.setSoLuong(soLuong);
                    gioHang.setMaTA(maTA);

                    if(gioHangDao.insert(gioHang) > 0){
                        Toast.makeText(DetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        if(id == 0){
                            startActivity(new Intent(DetailActivity.this, MainActivity.class));
                        }else {
                            Intent intentSend = new Intent(DetailActivity.this, DSTAActivity.class);
                            intentSend.putExtra("id",intent.getStringExtra("maLTA"));
                            startActivity(intentSend);
                            finish();
                        }
                    }else {
                        Toast.makeText(DetailActivity.this, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });
        // tang so luong
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl = Integer.parseInt(tvSoLuong.getText().toString());

                if (sl >= 10){
                    btnAdd.setEnabled(false);
                    Toast.makeText(DetailActivity.this, "Không được quá 10", Toast.LENGTH_SHORT).show();
                }else {
                    tvSoLuong.setText(String.valueOf(sl + 1));
                }
            }
        });
        // giam so luong
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl = Integer.parseInt(tvSoLuong.getText().toString());
               if (sl <= 1 ){
                   btnDel.setEnabled(false);
                   Toast.makeText(DetailActivity.this, "không được quá 1 ", Toast.LENGTH_SHORT).show();
               }else {
                   tvSoLuong.setText(String.valueOf(sl - 1));
               }
            }
        });

        loadImages();

    }

    public void loadImages(){
        Intent intent = getIntent();
        int maTA = intent.getIntExtra("maTA",0);
        int maLTA = thucAnDao.getId(String.valueOf(maTA)).getMaLTA();

        if (maLTA == 1){
            ivDetail.setImageResource(R.drawable.pop_1);
        }else if(maLTA == 2){
            ivDetail.setImageResource(R.drawable.pop_2);
        }else if(maLTA == 3){
            ivDetail.setImageResource(R.drawable.banhmy);
        }else if(maLTA == 4){
            ivDetail.setImageResource(R.drawable.nuoc);
        }else if(maLTA == 5){
            ivDetail.setImageResource(R.drawable.banhngot);
        }else if(maLTA == 6){
            ivDetail.setImageResource(R.drawable.xoi);
        }
    }

    public int validate(){
        int check = 1;
        Intent intent = getIntent();

        int maTA = intent.getIntExtra("maTA",0);

        List<GioHang> list = gioHangDao.getDSGH();

        for(int i = 0 ; i < list.size() ; i++) {
            if (maTA == list.get(i).getMaTA()) {
                Toast.makeText(DetailActivity.this, "Đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                check = 0;
                break;
            }
        }

        return check;
    }
}
package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.NguoiDungDao;
import com.example.duan1.SQLite.Model.NguoiDung;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CreateActivity extends AppCompatActivity {
    TextInputEditText etUser, etPass, etND;
    CardView btnSingUp;
    NguoiDungDao nguoiDungDao;
    TextView rePass, backLogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etUser = findViewById(R.id.etCreUser);
        etPass = findViewById(R.id.etCrePass);
        etND = findViewById(R.id.etTenND);
        nguoiDungDao = new NguoiDungDao(this);
        btnSingUp = findViewById(R.id.btnCreSingUp);
        rePass = findViewById(R.id.rePass);
        backLogin = findViewById(R.id.backLogin);
        // Sing up
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(validate() > 0){
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setUserName(etUser.getText().toString());
                    nguoiDung.setPassWord(etPass.getText().toString());
                    nguoiDung.setTenND(etND.getText().toString());
                    if(nguoiDungDao.insert(nguoiDung) > 0){
                        Toast.makeText(CreateActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                        etUser.setText("");
                        etPass.setText("");
                       etND.setText("");
                    }else {
                        Toast.makeText(CreateActivity.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        // chuyển qua change pass
        rePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this, ChangePassActivity.class));
            }
        });
        // trở lại login
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateActivity.this, LoginActivity.class));
            }
        });
    }

    public int validate(){
        List<NguoiDung> list = nguoiDungDao.getDSND();
        int check = 1;
        if(etUser.getText().toString().isEmpty() || etPass.getText().toString().isEmpty() || etND.getText().toString().isEmpty()){
            Toast.makeText(this, "Bạn cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = 0;
        }else {

            for (int i = 0 ; i < list.size() ; i++){
                if (etUser.getText().toString().equals(list.get(i).getUserName())){
                    etUser.setError("Tài khoản đã tồn tại");
                    Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    check = 0;
                }else {
                    if (etND.getText().toString().equals(list.get(i).getTenND())){
                        etND.setError("Tên đã tồn tại");
                        Toast.makeText(this, "Tên đã tồn tại", Toast.LENGTH_SHORT).show();
                        check = 0;
                    }
                }
            }

        }

        return check;
    }
}
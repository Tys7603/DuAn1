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

public class ChangePassActivity extends AppCompatActivity {
    TextInputEditText etTenND, etPass;
    NguoiDungDao nguoiDungDao;
    CardView btnChangePass;
    TextView backLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        etTenND = findViewById(R.id.cp_etTenND);
        etPass = findViewById(R.id.cp_etPass);
        nguoiDungDao = new NguoiDungDao(this);
        btnChangePass = findViewById(R.id.btnChagePass);
        backLogin = findViewById(R.id.cBackLogin);

        // đổi mật khẩu
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate() > 0){
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setTenND(etTenND.getText().toString());
                    nguoiDung.setPassWord(etPass.getText().toString());
                    if (nguoiDungDao.updates(nguoiDung) > 0){
                        Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        etTenND.setText("");
                        etPass.setText("");
                    }else {
                        Toast.makeText(getApplicationContext(), "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        // back login
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangePassActivity.this, LoginActivity.class));
            }
        });

    }

    public int validate(){
        int check = 1;
        if(etTenND.getText().toString().isEmpty() || etPass.getText().toString().isEmpty()){
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = 0;
        }else {
            List<NguoiDung> list = nguoiDungDao.getDSND();
            for(int i = 0; i < list.size() ; i ++){
                if(!etTenND.getText().toString().equals(list.get(i).getTenND())){
                    Toast.makeText(this, "Tên người dùng không tôn tại", Toast.LENGTH_SHORT).show();
                    etTenND.setError("Không Tồn Tại");
                    check = 0;
                } else {
                    if(etPass.getText().toString().equals(list.get(i).getPassWord())){
                        Toast.makeText(this, "Mật khẩu không được trùng mật khẩu cũ", Toast.LENGTH_SHORT).show();
                        etPass.setError("Tồn tại");
                        check = 0;
                    }
                }

            }
        }
        return check;
    }
}
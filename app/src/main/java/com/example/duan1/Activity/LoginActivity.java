package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.Activity.Admin.AdminActivity;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.NguoiDungDao;
import com.example.duan1.SQLite.Dao.AdminDao;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText etUser, etPass;
    TextView btnSingUp, btnChangePass;
    CheckBox checkBox;
    CardView btnLogin;

    NguoiDungDao nguoiDungDao;
    AdminDao adminDao;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSingUp = findViewById(R.id.btnSingUp);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.cardView);
        checkBox = findViewById(R.id.checkBox);
        btnChangePass = findViewById(R.id.lg_ChangePass);

        // chuyen qua singup
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CreateActivity.class));
            }
        });
        // chuyen change pass
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ChangePassActivity.class));
            }
        });

        // check login
        SharedPreferences sharedPreferences = getSharedPreferences("remember",MODE_PRIVATE);
        String user = sharedPreferences.getString("user","");
        String pass = sharedPreferences.getString("pass","");
        boolean status = sharedPreferences.getBoolean("status",false);

        etUser.setText(user);
        etPass.setText(pass);
        checkBox.setChecked(status);
        // login
        nguoiDungDao = new NguoiDungDao(this);
        adminDao = new AdminDao(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()>0){
                    String user = etUser.getText().toString();
                    String pass = etPass.getText().toString();

                    if (user.equals("admin")){
                        if (adminDao.checkLogin(user,pass)){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            remember(user,pass,checkBox.isChecked());

                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                            intent.putExtra("user",user);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if(nguoiDungDao.checkLogin(user, pass)){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            remember(user,pass,checkBox.isChecked());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("user",user);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });


    }

    public void remember(String u, String p, boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(status){
            editor.putString("user",u);
            editor.putString("pass",p);
            editor.putBoolean("status",status);

        }else {
            editor.clear();
        }
        editor.commit();
    }
    public int validate(){
        if(etUser.getText().toString().isEmpty() || etPass.getText().toString().isEmpty()){
            Toast.makeText(this, "Bạn cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return 0;
        }

        return 1;
    }
}
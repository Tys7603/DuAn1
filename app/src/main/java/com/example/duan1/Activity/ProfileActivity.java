package com.example.duan1.Activity;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.Activity.Toi.DiaChiActivity;
import com.example.duan1.Activity.Toi.ViActivity;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.NguoiDungDao;
import com.example.duan1.SQLite.Model.NguoiDung;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    TextView btnDC, btnVi, btnDoiAnh, btnDoiTen, tvName;
    ImageView btnHome, btnToi, btnDH, btnBell, ivAnh;
    FloatingActionButton fb;
    Button btnDX;
    NguoiDungDao nguoiDungDao;
    String user;
    Bitmap bitmap;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        init();
        name();
        setName();
        setImages();

        SharedPreferences sharedPreferences = getSharedPreferences("images",MODE_PRIVATE);
        String uri = sharedPreferences.getString("anh","");

        if(!uri.equals("")){
            ivAnh.setImageURI(Uri.parse(uri));
        }

    }

    public void init(){
        btnHome = findViewById(R.id.ivHomeToi);
        btnDH = findViewById(R.id.ivDHToi);
        btnToi = findViewById(R.id.ivProfileToi);
        btnBell = findViewById(R.id.ivBellToi);
        fb = findViewById(R.id.fbToi);
        ivAnh = findViewById(R.id.ivAnh);
        tvName = findViewById(R.id.tvName);
        btnDoiAnh = findViewById(R.id.btnDoiAnh);
        btnDoiTen = findViewById(R.id.btnDoiTen);
        btnDC = findViewById(R.id.btnDiaChi);
        btnVi = findViewById(R.id.btnVi);
        btnDX = findViewById(R.id.btnDX);
        nguoiDungDao = new NguoiDungDao(this);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CartActivity.class));
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        btnDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, DonHangActivity.class));
            }
        });
        btnBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ThongBaoActivity.class));
            }
        });
        btnToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            }
        });
        btnDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, DiaChiActivity.class));
            }
        });
        btnVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ViActivity.class));
            }
        });
        btnDX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            }
        });

    }

    public void name(){
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        user = sharedPreferences.getString("user","");
        NguoiDung nguoiDung = nguoiDungDao.getId(user);
        String name = nguoiDung.getTenND();
        tvName.setText(name);
    }

    public void setName(){
        btnDoiTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etName;
                Button btnYes,btnCancel;

                Dialog dialog = new Dialog(ProfileActivity.this);
                dialog.setContentView(R.layout.item_dialog_doi_ten);
                dialog.setTitle("Đổi tên");

                etName = dialog.findViewById(R.id.etName);
                btnYes = dialog.findViewById(R.id.btn_Yes);
                btnCancel = dialog.findViewById(R.id.btn_Cancel);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = etName.getText().toString();
                        if(name.isEmpty()){
                            Toast.makeText(ProfileActivity.this, "Cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else {
                            NguoiDung nguoiDung = new NguoiDung();
                            nguoiDung.setUserName(user);
                            nguoiDung.setTenND(name);
                            if (nguoiDungDao.updatesTen(nguoiDung) > 0 ){
                                Toast.makeText(ProfileActivity.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                name();
                            }else {
                                Toast.makeText(ProfileActivity.this, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    public void setImages(){
        btnDoiAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              requestPermission();
            }
        });

    }



    private void requestPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImages();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(ProfileActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };


        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void openImages() {
        btnDoiAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();

                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT); // truy cap vao bo nho dien thoai lay nd và type là image

                chooseImage.launch(i);
            }
        });
    }



    ActivityResultLauncher<Intent> chooseImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri selectedImageUri = data.getData();
                        if (null != selectedImageUri) {

                            ivAnh.setImageURI(selectedImageUri);

                            SharedPreferences sharedPreferences = getSharedPreferences("images",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("anh",String.valueOf(selectedImageUri));
                            editor.commit();
                        }
                    }
                }
            });

}
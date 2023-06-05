package com.example.duan1.Activity.Toi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.NguoiDungDao;
import com.example.duan1.SQLite.Model.NguoiDung;

public class ViActivity extends AppCompatActivity {
    TextView tvTien;
    Button btnNap;
    NguoiDungDao nguoiDungDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi);

        tvTien = findViewById(R.id.viTien);
        btnNap = findViewById(R.id.btnNap);
        nguoiDungDao = new NguoiDungDao(this);

        // do du lieu
        SharedPreferences sharedPreferences = getSharedPreferences("remember",MODE_PRIVATE);
        String user = sharedPreferences.getString("user","");
        NguoiDung  nguoiDung = nguoiDungDao.getId(user);
        tvTien.setText("$ " + nguoiDung.getTien());

        btnNap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etTien;
                Button btnYes, btnCancel;

                Dialog dialog = new Dialog(ViActivity.this);
                dialog.setContentView(R.layout.item_tien);

                etTien = dialog.findViewById(R.id.etTien);
                btnYes = dialog.findViewById(R.id.btnYes);
                btnCancel = dialog.findViewById(R.id.btnCancel);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (validate() > 0){
                            int tien = Integer.parseInt(etTien.getText().toString());
                            int tienHT  = nguoiDung.getTien();
                            NguoiDung nd = new NguoiDung();
                            nd.setUserName(user);
                            nd.setTien(tien + tienHT);
                            if(nguoiDungDao.updatesTien(nd) > 0){
                                tvTien.setText("$ " +( tien + tienHT));
                                Toast.makeText(ViActivity.this, "Nạp tiền thành công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ViActivity.this, "Nạp tiền thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialog.cancel();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                dialog.show();

            }
        });



    }

    public int validate(){
        int check = 1;

        if (tvTien.getText().toString().isEmpty()){
            check = 0;
            Toast.makeText(this, "Bạn chưa nhập số tiền", Toast.LENGTH_SHORT).show();
        }

        return check;
    }
}
package com.example.duan1.Activity.Toi;

import androidx.appcompat.app.AppCompatActivity;

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

public class DiaChiActivity extends AppCompatActivity {
    TextView tvDiaChi;
    Button btnSua;
    NguoiDungDao nguoiDungDao;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi);

        tvDiaChi = findViewById(R.id.tvDC);
        btnSua = findViewById(R.id.btnSuaDiaChi);

        nguoiDungDao = new NguoiDungDao(this);

        SharedPreferences sharedPreferences = getSharedPreferences("remember",MODE_PRIVATE);
        String user = sharedPreferences.getString("user","");
        NguoiDung nguoiDung = nguoiDungDao.getId(user);

        tvDiaChi.setText("     " + nguoiDung.getDiaChi());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btnOk, btnHuy;
                EditText etDC;

                dialog = new Dialog(DiaChiActivity.this);
                dialog.setContentView(R.layout.item_diachi);
                dialog.setTitle("Thay đổi địa chỉ hiện tại");

                btnOk = dialog.findViewById(R.id.btnOk);
                btnHuy = dialog.findViewById(R.id.btnHuy);
                etDC = dialog.findViewById(R.id.etDC);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String dc = etDC.getText().toString();
                        NguoiDung nd = new NguoiDung();
                        nd.setDiaChi(dc);
                        nd.setUserName(user);
                       if( nguoiDungDao.updatesDC(nd) > 0){
                           Toast.makeText(DiaChiActivity.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                           tvDiaChi.setText(dc);
                           dialog.dismiss();
                       }else {
                           Toast.makeText(DiaChiActivity.this, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                       }
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
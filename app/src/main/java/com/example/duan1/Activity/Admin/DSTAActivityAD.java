package com.example.duan1.Activity.Admin;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Activity.AdapterAdmin.AdminAdapterDSTA;
import com.example.duan1.Adapter.RececlerViewAdapterDSTA;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.ThucAnDao;
import com.example.duan1.SQLite.Model.ThucAn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DSTAActivityAD extends AppCompatActivity {

    RecyclerView rv;
    ThucAnDao thucAnDao;
    Intent intent;
    Toolbar toolbar;
    AdminAdapterDSTA DSTAActivity;
    FloatingActionButton btnAdd;
    String id;
    final String regex = "^[1-9][0-9]*$";
    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsta_am);

        // anh xa
        rv = findViewById(R.id.rcDSTAAM);
        toolbar = findViewById(R.id.toolbar);
        btnAdd = findViewById(R.id.fbAddDSTA);

        thucAnDao = new ThucAnDao(this);

        // xu li toolbar
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(R.color.white);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(R.color.purple_200);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // lay du lieu
        intent = getIntent();
        loadData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etTen, etTien;
                Button btnOk, btnCancel;

                Dialog dialog = new Dialog(DSTAActivityAD.this);
                dialog.setContentView(R.layout.item_add_ta);
                dialog.setTitle("Thêm thúc ăn");

                etTen = dialog.findViewById(R.id.etTenTA);
                etTien = dialog.findViewById(R.id.etGia);
                btnOk = dialog.findViewById(R.id.btnYesAd);
                btnCancel = dialog.findViewById(R.id.btnCancelAd);



                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ten = etTen.getText().toString();
                        String tien = etTien.getText().toString();
                        if (validate(ten, tien)) {
                            ThucAn thucAn = new ThucAn();
                            thucAn.setTenTA(ten);
                            thucAn.setGiaTien(Integer.parseInt(tien));
                            thucAn.setMaLTA(Integer.parseInt(id));
                            if (thucAnDao.insert(thucAn) > 0) {
                                Toast.makeText(DSTAActivityAD.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                loadData();
                            } else {
                                Toast.makeText(DSTAActivityAD.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();
            }
        });


    }

    private boolean validate(String ten , String tien){
        if (ten.isEmpty() || tien.isEmpty()){
            Toast.makeText(DSTAActivityAD.this, "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Pattern pattern =Pattern.compile(regex);
            Matcher matcher = pattern.matcher(tien);
            if (!matcher.matches()){
                Toast.makeText(this, "Bạn cần nhập số", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public void loadData(){
        id = intent.getStringExtra("id");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        DSTAActivity = new AdminAdapterDSTA(this,thucAnDao.getDSTA(id), id, this);
        rv.setAdapter(DSTAActivity);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // truy cap den item search
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        //truy cap dịch vụ tìm kiếm
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Nhận thông tin về một hoạt động có thể tìm kiếm
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // hint
        searchView.setQueryHint(" Nhập từ khóa ... ");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DSTAActivity.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                DSTAActivity.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
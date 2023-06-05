package com.example.duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.duan1.Adapter.RececlerViewAdapterDSTA;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.ThucAnDao;

public class DSTAActivity extends AppCompatActivity {

    RecyclerView rv;
    ThucAnDao thucAnDao;
    Intent intent;
    Toolbar toolbar;
    RececlerViewAdapterDSTA adapterDSTA;


    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsta);

        // anh xa
        rv = findViewById(R.id.rcDSTA);
        toolbar = findViewById(R.id.toolbar);

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
        String id = intent.getStringExtra("id");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapterDSTA = new RececlerViewAdapterDSTA(this,thucAnDao.getDSTA(id), id);
        rv.setAdapter(adapterDSTA);
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
                adapterDSTA.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterDSTA.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
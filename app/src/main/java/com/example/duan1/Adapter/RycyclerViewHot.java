package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Activity.DetailActivity;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.LoaiThucAnDao;
import com.example.duan1.SQLite.Dao.ThucAnDao;
import com.example.duan1.SQLite.Model.LoaiThucAn;
import com.example.duan1.SQLite.Model.PhieuMua;
import com.example.duan1.SQLite.Model.ThucAn;

import java.util.ArrayList;
import java.util.List;

public class RycyclerViewHot extends RecyclerView.Adapter<RycyclerViewHot.viewHolder> {
    Context context;
    List<PhieuMua> list;
    List<ThucAn> listHA;
    LoaiThucAnDao loaiThucAnDao;
    ThucAnDao thucAnDao;
    public RycyclerViewHot(Context context, List<PhieuMua> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rc_hot,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        loaiThucAnDao = new LoaiThucAnDao(context);
        thucAnDao = new ThucAnDao(context);
        int maTA = list.get(position).getMaTA();
        int gia = thucAnDao.getId(String.valueOf(maTA)).getGiaTien();
        int ma = thucAnDao.getId(String.valueOf(maTA)).getMaLTA();

        if(list != null){
            holder.tvTile.setText(list.get(position).getTenPM());
            holder.tvGia.setText("$ " + gia);
            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("tile",holder.tvTile.getText().toString());
                    intent.putExtra("gia",gia);
                    intent.putExtra("maTA",list.get(holder.getAdapterPosition()).getMaTA());
                    intent.putExtra("id", 0); // main
                    context.startActivity(intent);
                }
            });

            // load anh

            loadImages();


            if (ma == 1){
                holder.ivHot.setImageResource(listHA.get(0).getHinh());
            }else if(ma == 2){
                holder.ivHot.setImageResource(listHA.get(1).getHinh());
            }else if(ma == 3){
                holder.ivHot.setImageResource(listHA.get(2).getHinh());
            }else if(ma == 4){
                holder.ivHot.setImageResource(listHA.get(3).getHinh());
            }else if(ma == 5){
                holder.ivHot.setImageResource(listHA.get(4).getHinh());
            }else if(ma == 6){
                holder.ivHot.setImageResource(listHA.get(5).getHinh());
            }
        }

    }

    public void loadImages(){
        for (int i = 0 ; i< list.size() ; i++){
            listHA = new ArrayList<>();
            listHA.add(new ThucAn(R.drawable.pop_1));
            listHA.add(new ThucAn(R.drawable.pop_2));
            listHA.add(new ThucAn(R.drawable.banhmy));
            listHA.add(new ThucAn(R.drawable.nuoc));
            listHA.add(new ThucAn(R.drawable.banhngot));
            listHA.add(new ThucAn(R.drawable.xoi));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tvTile, tvGia, btnAdd;
        ImageView ivHot;
        public viewHolder(@NonNull View view) {
            super(view);

            tvTile = view.findViewById(R.id.tv_tile);
            tvGia = view.findViewById(R.id.tv_gia);
            btnAdd = view.findViewById(R.id.tv_add);
            ivHot =view.findViewById(R.id.iv_hot);
        }
    }
}

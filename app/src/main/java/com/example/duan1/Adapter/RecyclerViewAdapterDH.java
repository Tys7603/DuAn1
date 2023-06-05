package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.LoaiThucAnDao;
import com.example.duan1.SQLite.Dao.PhieuMuaDao;
import com.example.duan1.SQLite.Dao.ThucAnDao;
import com.example.duan1.SQLite.Model.LoaiThucAn;
import com.example.duan1.SQLite.Model.PhieuMua;
import com.example.duan1.SQLite.Model.ThucAn;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterDH extends RecyclerView.Adapter<RecyclerViewAdapterDH.viewHolder> {

    Context context;
    List<PhieuMua> list;
    PhieuMuaDao phieuMuaDao;
    ThucAnDao thucAnDao;
    LoaiThucAnDao loaiThucAnDao;
    List<ThucAn> listHA;
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    public RecyclerViewAdapterDH(Context context, List<PhieuMua> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_don_hang,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        phieuMuaDao = new PhieuMuaDao(context);
        thucAnDao = new ThucAnDao(context);
        loaiThucAnDao = new LoaiThucAnDao(context);
        if (list != null){
            int ma = thucAnDao.getId(String.valueOf(list.get(position).getMaTA())).getMaLTA();
            LoaiThucAn loaiThucAn = loaiThucAnDao.getID(String.valueOf(ma));
            holder.tvLTA.setText(String.valueOf(loaiThucAn.getTenLTA()));
            holder.tvGia.setText("$ " + list.get(position).getGiaMua());
            holder.tvTile.setText(list.get(position).getTenPM());
            holder.tvSoLuong.setText(String.valueOf(list.get(position).getSoLuong()));
            holder.tvTimes.setText(format.format(list.get(position).getNgayMua()));
        }

        // load ảnh
        loadImages();
        int ma = list.get(position).getMaTA();
        ThucAn thucAn = thucAnDao.getId(String.valueOf(ma));
        LoaiThucAn loaiThucAn = loaiThucAnDao.getID(String.valueOf(thucAn.getMaLTA()));

        if (loaiThucAn.getMaLTA() == 1){
            holder.ivDH.setImageResource(listHA.get(0).getHinh());
        }else if(loaiThucAn.getMaLTA() == 2){
            holder.ivDH.setImageResource(listHA.get(1).getHinh());
        }else if(loaiThucAn.getMaLTA() == 3){
            holder.ivDH.setImageResource(listHA.get(2).getHinh());
        }else if(loaiThucAn.getMaLTA() == 4){
            holder.ivDH.setImageResource(listHA.get(3).getHinh());
        }else if(loaiThucAn.getMaLTA() == 5){
            holder.ivDH.setImageResource(listHA.get(4).getHinh());
        }else if(loaiThucAn.getMaLTA() == 6){
            holder.ivDH.setImageResource(listHA.get(5).getHinh());
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
        }    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tvLTA, tvTile, tvGia, tvSoLuong, tvTimes;
        ImageView ivDH;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvLTA = itemView.findViewById(R.id.tvLTA);
            tvGia = itemView.findViewById(R.id.tvGiaDH);
            tvSoLuong = itemView.findViewById(R.id.tvSLDH);
            tvTile = itemView.findViewById(R.id.tvTileDH);
            tvTimes = itemView.findViewById(R.id.tvTimes);
            ivDH = itemView.findViewById(R.id.ivDH);
        }
    }
}

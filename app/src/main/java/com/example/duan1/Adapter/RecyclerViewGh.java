package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Activity.CartActivity;
import com.example.duan1.Interface.IClickItemListenner;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.GioHangDao;
import com.example.duan1.SQLite.Dao.LoaiThucAnDao;
import com.example.duan1.SQLite.Dao.NguoiDungDao;
import com.example.duan1.SQLite.Dao.ThucAnDao;
import com.example.duan1.SQLite.Model.GioHang;
import com.example.duan1.SQLite.Model.LoaiThucAn;
import com.example.duan1.SQLite.Model.NguoiDung;
import com.example.duan1.SQLite.Model.ThucAn;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewGh extends RecyclerView.Adapter<RecyclerViewGh.viewHolder> {

    List<GioHang> list;
    Context context;
    NguoiDungDao nguoiDungDao;
    List<ThucAn> listHA;
    LoaiThucAnDao loaiThucAnDao;
    ThucAnDao thucAnDao;
    GioHangDao gioHangDao;
    private IClickItemListenner listenner;

    public RecyclerViewGh(List<GioHang> list, Context context, IClickItemListenner listenner) {
        this.list = list;
        this.context = context;
        this.listenner = listenner;
    }
    public RecyclerViewGh(List<GioHang> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pm, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        nguoiDungDao = new NguoiDungDao(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("remember",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user","");
        NguoiDung nguoiDung = nguoiDungDao.getId(user);
        loaiThucAnDao = new LoaiThucAnDao(context);
        thucAnDao = new ThucAnDao(context);
        gioHangDao = new GioHangDao(context);


        if (list != null){

            holder.tvTile.setText(list.get(position).getTenGH());
            holder.tv1Gia.setText("$ " + list.get(position).getGia());
            holder.tvSoluong.setText(String.valueOf(list.get(position).getSoLuong()));

            holder.tvTongGia.setText(String.valueOf(list.get(position).getSoLuong() * list.get(position).getGia()));

            holder.tvDiaChi.setText(nguoiDung.getDiaChi());

            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int sl = Integer.parseInt(holder.tvSoluong.getText().toString());

                    if (sl >= 10){
                        holder.btnAdd.setEnabled(false);
                        Toast.makeText(context.getApplicationContext(), "Không được quá 10", Toast.LENGTH_SHORT).show();
                    }else {
                        holder.tvSoluong.setText(String.valueOf(sl + 1));
                        sl = Integer.parseInt(holder.tvSoluong.getText().toString());
                        holder.tvTongGia.setText(String.valueOf(sl * list.get(holder.getAdapterPosition()).getGia()));

                        // update so luong
                        GioHang gioHang = new GioHang();
                        gioHang.setMaGH(list.get(holder.getAdapterPosition()).getMaGH());
                        gioHang.setSoLuong(sl);
                        gioHangDao.updates(gioHang);
                        list = gioHangDao.getDSGH();
                        listenner.OnClickItemTong(tongGia());

                    }
                }
            });

            holder.btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int sl = Integer.parseInt(holder.tvSoluong.getText().toString());
                    if (sl <= 1 ){
                        holder.btnDel.setEnabled(false);
                        Toast.makeText(context.getApplicationContext(), "không được quá 1 ", Toast.LENGTH_SHORT).show();
                    }else {
                        holder.tvSoluong.setText(String.valueOf(sl - 1));
                        int tong = Integer.parseInt(holder.tvTongGia.getText().toString());
                        sl = Integer.parseInt(holder.tvSoluong.getText().toString());
                        holder.tvTongGia.setText(String.valueOf(tong - list.get(holder.getAdapterPosition()).getGia()));

                        // update so luong
                        GioHang gioHang = new GioHang();
                        gioHang.setMaGH(list.get(holder.getAdapterPosition()).getMaGH());
                        gioHang.setSoLuong(sl);
                        gioHangDao.updates(gioHang);
                        list = gioHangDao.getDSGH();
                        listenner.OnClickItemTong(tongGia());
                    }
                }
            });

            // load ảnh
            loadImages();
            int ma = list.get(position).getMaTA();
            ThucAn thucAn = thucAnDao.getId(String.valueOf(ma));
            LoaiThucAn loaiThucAn = loaiThucAnDao.getID(String.valueOf(thucAn.getMaLTA()));

            if (loaiThucAn.getMaLTA() == 1){
                holder.ivGH.setImageResource(listHA.get(0).getHinh());
            }else if(loaiThucAn.getMaLTA() == 2){
                holder.ivGH.setImageResource(listHA.get(1).getHinh());
            }else if(loaiThucAn.getMaLTA() == 3){
                holder.ivGH.setImageResource(listHA.get(2).getHinh());
            }else if(loaiThucAn.getMaLTA() == 4){
                holder.ivGH.setImageResource(listHA.get(3).getHinh());
            }else if(loaiThucAn.getMaLTA() == 5){
                holder.ivGH.setImageResource(listHA.get(4).getHinh());
            }else if(loaiThucAn.getMaLTA() == 6){
                holder.ivGH.setImageResource(listHA.get(5).getHinh());
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
        }    }

    public int tongGia(){
        int tong = 0;
        for (int i = 0 ; i < list.size() ; i++){
            tong += list.get(i).getSoLuong() * list.get(i).getGia() ;

        }
        return tong;
    }

    public GioHang getItem(int position){
        if(list == null  || position >= list.size()){
            return null;
        }
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tvTile, tv1Gia, tvSoluong, tvTongGia, tvDiaChi;
        CardView btnAdd, btnDel;
        RecyclerView rv;
        ImageView ivGH;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvTile = itemView.findViewById(R.id.tvTilePM);
            tv1Gia = itemView.findViewById(R.id.tv1GiaPM);
            tvSoluong = itemView.findViewById(R.id.tvSoluongPM);
            tvTongGia = itemView.findViewById(R.id.tvTongGiaPM);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);

            btnAdd =itemView.findViewById(R.id.tvAddPM);
            btnDel = itemView.findViewById(R.id.tvdelPM);

            rv = itemView.findViewById(R.id.rvGH);
            ivGH = itemView.findViewById(R.id.ivGH);
        }
    }
}

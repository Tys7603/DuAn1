package com.example.duan1.Activity.AdapterAdmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Activity.Admin.DSTAActivityAD;
import com.example.duan1.Activity.DetailActivity;
import com.example.duan1.R;
import com.example.duan1.SQLite.Dao.ThucAnDao;
import com.example.duan1.SQLite.Model.ThucAn;

import java.util.ArrayList;
import java.util.List;

public class AdminAdapterDSTA extends RecyclerView.Adapter<AdminAdapterDSTA.viewHolder> implements Filterable {
    Context context;
    List<ThucAn> list;
    List<ThucAn> listText;
    String maLTA;
    ThucAnDao thucAnDao;
    DSTAActivityAD dstaActivityAD;
    public AdminAdapterDSTA(Context context, List<ThucAn> list, String maLTA, DSTAActivityAD dstaActivityAD) {
        this.context = context;
        this.list = list;
        this.maLTA = maLTA;
        this.listText = list;
        this.dstaActivityAD = dstaActivityAD;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thuc_an_am,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        thucAnDao = new ThucAnDao(context);
        if (list != null){
            holder.tvTile.setText(list.get(position).getTenTA());
            holder.tvGia.setText("$" + list.get(position).getGiaTien());
            holder.btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (thucAnDao.delete(String.valueOf(list.get(holder.getAdapterPosition()).getMaTA())) > 0){
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        dstaActivityAD.loadData();
                    }else {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            if (maLTA.equals("1")){
                holder.imageView.setImageResource(R.drawable.pop_1);
            }else if(maLTA.equals("2")){
                holder.imageView.setImageResource(R.drawable.pop_2);
            }
            else if(maLTA.equals("3")){
                holder.imageView.setImageResource(R.drawable.banhmy);
            }
            else if(maLTA.equals("4")){
                holder.imageView.setImageResource(R.drawable.nuoc);
            }
            else if(maLTA.equals("5")){
                holder.imageView.setImageResource(R.drawable.banhngot);
            }else if(maLTA.equals("6")){
                holder.imageView.setImageResource(R.drawable.xoi);
            }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();

                if (search.isEmpty()){
                    list = listText;
                }else {
                    List<ThucAn> listTA = new ArrayList<>();

                    for (ThucAn thucAn : listText ) {
                        if(thucAn.getTenTA().toLowerCase().contains(search.toLowerCase())){
                            listTA.add(thucAn);
                        }

                    }

                    list = listTA;
                }
                FilterResults  filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (List<ThucAn>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tvTile , tvGia;
        CardView btnXoa;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            btnXoa = itemView.findViewById(R.id.btn_addDSTAAM);
            tvTile = itemView.findViewById(R.id.tv_tileTAAM);
            tvGia = itemView.findViewById(R.id.tv_giaTAAM);
            imageView = itemView.findViewById(R.id.iv_TAAM);
        }
    }
}

package com.example.duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Activity.DetailActivity;
import com.example.duan1.R;
import com.example.duan1.SQLite.Model.ThucAn;

import java.util.ArrayList;
import java.util.List;

public class RececlerViewAdapterDSTA extends RecyclerView.Adapter<RececlerViewAdapterDSTA.viewHolder> implements Filterable {
    Context context;
    List<ThucAn> list;
    List<ThucAn> listText;
    String maLTA;
    public RececlerViewAdapterDSTA(Context context, List<ThucAn> list, String maLTA) {
        this.context = context;
        this.list = list;
        this.maLTA = maLTA;
        this.listText = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thuc_an,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        if (list != null){
            holder.tvTile.setText(list.get(position).getTenTA());
            holder.tvGia.setText("$" + list.get(position).getGiaTien());
            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("tile",holder.tvTile.getText().toString());
                    intent.putExtra("gia",list.get(holder.getAdapterPosition()).getGiaTien());
                    intent.putExtra("maTA",list.get(holder.getAdapterPosition()).getMaTA());
                    intent.putExtra("id", 1); // dsta
                    intent.putExtra("maLTA",String.valueOf(list.get(holder.getAdapterPosition()).getMaLTA()));
                    context.startActivity(intent);
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
            // loc du lieu theo dk
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

            // lay ket qua loc
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (List<ThucAn>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tvTile , tvGia;
        CardView btnAdd;
        ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            btnAdd = itemView.findViewById(R.id.btn_addDSTA);
            tvTile = itemView.findViewById(R.id.tv_tileTA);
            tvGia = itemView.findViewById(R.id.tv_giaTA);
            imageView = itemView.findViewById(R.id.iv_TA);
        }
    }
}

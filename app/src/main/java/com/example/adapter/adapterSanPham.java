package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterSanPham extends RecyclerView.Adapter<adapterSanPham.ViewHolder>{
    Context context;
    ArrayList<SanPham> sanPhams;

    public adapterSanPham(Context context, ArrayList<SanPham> sanPhams) {
            this.context = context;
            this.sanPhams = sanPhams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View customView=inflater.inflate(R.layout.list_item,parent,false);

        return new ViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(sanPhams.get(position).getAnh()).placeholder(R.drawable.ic_baseline_cloud_download_24)
                .error(R.drawable.ic_baseline_image_not_supported_24).into(holder.invThumb);
        holder.txtinfor.setText(sanPhams.get(position).getTenSP()+"_"+sanPhams.get(position).getGia());
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView invThumb;
        TextView txtinfor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invThumb =itemView.findViewById(R.id.invThumble);
            txtinfor=itemView.findViewById(R.id.txtinfo);
        }
    }
}

package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.MainActivity;
import com.example.androidproject.MainChiTietSanPham;
import com.example.androidproject.R;
import com.example.androidproject.RecyclerViewClickInterFace;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class adapterSanPham extends RecyclerView.Adapter<adapterSanPham.ViewHolder>{
    Context context;
    ArrayList<SanPham> sanPhams;
    private RecyclerViewClickInterFace recyclerViewClickInterFace;

    public adapterSanPham(Context context, ArrayList<SanPham> sanPhams, RecyclerViewClickInterFace recyclerViewClickInterFace) {
            this.context = context;
            this.sanPhams = sanPhams;
            this.recyclerViewClickInterFace = recyclerViewClickInterFace;
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
        holder.txtinfor.setText(sanPhams.get(position).getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(sanPhams.get(position).getGia())+ " VNĐ");

    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView invThumb;
        TextView txtinfor, txtPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invThumb =itemView.findViewById(R.id.invThumble);
            txtinfor=itemView.findViewById(R.id.txtinfo);
            txtPrice =itemView.findViewById(R.id.txtPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterFace.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

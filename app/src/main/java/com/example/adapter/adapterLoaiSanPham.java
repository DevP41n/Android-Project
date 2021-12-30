package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.model.LoaiSanPham;

import java.util.ArrayList;
import java.util.List;

public class adapterLoaiSanPham extends BaseAdapter {
    Activity context;
    int item_layout;
    List<LoaiSanPham> loaiSanPham;

    public adapterLoaiSanPham(Activity context, int item_layout, List<LoaiSanPham> loaiSanPham) {
        this.context = context;
        this.item_layout = item_layout;
        this.loaiSanPham = loaiSanPham;
    }

    @Override
    public int getCount() {
        return loaiSanPham.size();
    }

    @Override
    public Object getItem(int i) {
        return loaiSanPham.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            view = inflater.inflate(item_layout, null);

            holder.txtCategory = view.findViewById(R.id.txtCategory);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        //Binding data
        LoaiSanPham b = loaiSanPham.get(i);

        holder.txtCategory.setText(b.getTenDM());



        return view;
    }

    private static class ViewHolder{
        TextView txtCategory;
    }
}

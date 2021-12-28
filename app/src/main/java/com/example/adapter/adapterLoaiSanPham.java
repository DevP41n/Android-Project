package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.model.LoaiSanPham;

import java.util.ArrayList;

public class adapterLoaiSanPham extends BaseAdapter {
    ArrayList<LoaiSanPham> arrayListLoaiSanPham;
    Context context;

    public adapterLoaiSanPham(ArrayList<LoaiSanPham> arrayListLoaiSanPham, Context context) {
        this.arrayListLoaiSanPham = arrayListLoaiSanPham;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaiSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListLoaiSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txtCategory;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_toolbar,null);
            viewHolder.txtCategory = view.findViewById(R.id.txtCategory);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
            LoaiSanPham loaiSanPham = (LoaiSanPham) getItem(position);
            viewHolder.txtCategory.setText(loaiSanPham.getTenDM());

        }
        return view;
    }
}

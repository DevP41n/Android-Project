package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.model.DonDatHang;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class adapterLichSuDonDatHang extends BaseAdapter {
    Context context;
    ArrayList<DonDatHang> donDatHang;

    public adapterLichSuDonDatHang(Context context, ArrayList<DonDatHang> donDatHang) {
        this.context = context;
        this.donDatHang = donDatHang;
    }

    @Override
    public int getCount() {
        return donDatHang.size();
    }

    @Override
    public Object getItem(int position) {
        return donDatHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txtDonDathang;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        adapterLichSuDonDatHang.ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_lsgd,null);
            viewHolder.txtDonDathang = view.findViewById(R.id.txtDonDathang);

            view.setTag(viewHolder);
        }else {
            viewHolder = (adapterLichSuDonDatHang.ViewHolder) view.getTag();
        }

        DonDatHang donDatHang = (DonDatHang) getItem(position);

        viewHolder.txtDonDathang.setText("Thời gian đặt: " + donDatHang.getNgaydat() + " - " + donDatHang.getGiodat());

        return view;
    }
}

package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class adapterTimKiemSanPham extends BaseAdapter {

    Context context;
    ArrayList<SanPham> arraySanPham;

    public adapterTimKiemSanPham(Context context, int activity_product_by_category, ArrayList<SanPham> arraySanPham) {
        this.context = context;
        this.arraySanPham = arraySanPham;
    }

    @Override
    public int getCount() {
        return arraySanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return arraySanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        ImageView imvAnhSp;
        TextView txtThongTinSp, txtPrice;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_by_category,null);
            viewHolder.imvAnhSp = view.findViewById(R.id.imvAnhSp);
            viewHolder.txtThongTinSp = view.findViewById(R.id.txtThongTinSp);
            viewHolder.txtPrice = view.findViewById(R.id.txtPrice);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        SanPham sanPham = (SanPham) getItem(position);
        viewHolder.txtThongTinSp.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText(decimalFormat.format(sanPham.getGia()) + " VNĐ");
        Picasso.get().load(sanPham.getAnh()).placeholder(R.drawable.ic_baseline_cloud_download_24)
                        .error(R.drawable.ic_baseline_image_not_supported_24).into(viewHolder.imvAnhSp);
        return view;
    }
}

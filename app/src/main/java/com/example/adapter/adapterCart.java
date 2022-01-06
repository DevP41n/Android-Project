package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.SharedPreferences.SessionCart;
import com.example.androidproject.GioHang;
import com.example.androidproject.R;
import com.example.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class adapterCart extends BaseAdapter {
    GioHang context;
    ArrayList<Cart> carts;

    public adapterCart(GioHang context, int activity_gio_hang, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int position) {
        return carts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtNameSP, txtPrice;
        public ImageView imvcart, imvDelete;
        public ElegantNumberButton enbSLCart;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_cart,null);
            viewHolder.txtNameSP = view.findViewById(R.id.txtNameSP);
            viewHolder.txtPrice = view.findViewById(R.id.txtPrice);
            viewHolder.imvcart = view.findViewById(R.id.imvcart);
            viewHolder.enbSLCart = view.findViewById(R.id.enbSLCart);
            viewHolder.imvDelete = view.findViewById(R.id.imvDelete);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Cart cart = (Cart) getItem(position);
        viewHolder.txtNameSP.setText(cart.getTenSp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText(decimalFormat.format(cart.getTien())+ " VNĐ");
        Picasso.get().load(cart.getHinh()).placeholder(R.drawable.ic_baseline_cloud_download_24)
                .error(R.drawable.ic_baseline_image_not_supported_24).into(viewHolder.imvcart);

        viewHolder.enbSLCart.setNumber(String.valueOf(cart.getSoLuong()), false);
        viewHolder.enbSLCart.setRange(cart.getSoLuong(),cart.getSoluongton());

        viewHolder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.OpenDeleteDialog(position);
            }
        });


        return view;
    }
}

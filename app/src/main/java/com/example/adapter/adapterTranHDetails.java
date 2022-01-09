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
import com.example.model.TranHisDetails;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class adapterTranHDetails extends BaseAdapter {
    Context context;
    ArrayList<TranHisDetails> tranHisDetails;

    @Override
    public int getCount() {
        return tranHisDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return tranHisDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        adapterTranHDetails.ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new adapterTranHDetails.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_tran_his_details,null);
            viewHolder.txtInfo = view.findViewById(R.id.txtInfo);
            view.setTag(viewHolder);
        }else {
            viewHolder = (adapterTranHDetails.ViewHolder) view.getTag();
        }
        TranHisDetails tranHisDetails = (TranHisDetails) getItem(position);
        viewHolder.txtInfo.setText(tranHisDetails.getTenSP() + "\n" + tranHisDetails.getDongia() + " X " + tranHisDetails.getSoLuong());
        return view;
    }

    public class ViewHolder{
        TextView txtInfo;
    }
}

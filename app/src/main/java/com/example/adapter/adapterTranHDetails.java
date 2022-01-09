package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidproject.R;
import com.example.model.TranHisDetailsModel;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class adapterTranHDetails extends BaseAdapter {
    Context context;
    ArrayList<TranHisDetailsModel> tranHisDetails;

    public adapterTranHDetails(Context context, int activity_tran_his_details, ArrayList<TranHisDetailsModel> tranHisDetails) {
        this.context = context;
        this.tranHisDetails = tranHisDetails;
    }

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
            view = inflater.inflate(R.layout.tran_h_details_cus,null);
            viewHolder.txtDetails = view.findViewById(R.id.txtDetails);
            viewHolder.txtPrice = view.findViewById(R.id.txtPrice);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TranHisDetailsModel tranHisDetailsModel = (TranHisDetailsModel) getItem(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String dongia = decimalFormat.format(tranHisDetailsModel.getDongia());

        viewHolder.txtDetails.setText(tranHisDetailsModel.getTenSP() );
        viewHolder.txtPrice.setText(dongia + "VNĐ X " + tranHisDetailsModel.getSoLuong());
        return view;
    }

    public class ViewHolder{
        TextView txtDetails, txtPrice;
    }
}

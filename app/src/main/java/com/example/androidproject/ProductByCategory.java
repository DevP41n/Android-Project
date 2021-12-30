package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.adapter.adapterLoaiSanPham;
import com.example.adapter.adapterSanPham;
import com.example.adapter.adapterSpTheoDanhMuc;
import com.example.model.LoaiSanPham;
import com.example.model.SanPham;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductByCategory extends AppCompatActivity {

    ListView lsvByCategory;
    ArrayList<SanPham> sp;
    adapterSpTheoDanhMuc adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_category);

        linkViews();
        getData();
    }

    private void getData() {
        sp = new ArrayList<>();
        int MaDM = 0;
        MaDM = getIntent().getIntExtra("ProductByCategory",-1);

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            Connection connect = connectionHelper.connectionClass();
            if(connect!= null)
            {
                String query ="select * from SanPham where MaDM = " + MaDM;
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);

                while (rs.next()){
                    sp.add(new SanPham(rs.getInt(1) , rs.getString(2), rs.getDouble(3)
                            , rs.getString(4), rs.getString(5), rs.getString(6)
                            ,rs.getInt(7),rs.getInt(8), rs.getInt(9)));
                }
            }
            adapter = new adapterSpTheoDanhMuc(ProductByCategory.this, R.layout.activity_product_by_category, sp);
            lsvByCategory.setAdapter(adapter);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void linkViews() {
        lsvByCategory = findViewById(R.id.lsvByCategory);

    }
}
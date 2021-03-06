package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.function.ToLongBiFunction;

public class ProductByCategory extends AppCompatActivity {

    ListView lsvByCategory;
    ArrayList<SanPham> sp;
    adapterSpTheoDanhMuc adapter;

    TextView txtCategory, txtNoti;
    ImageView imvCart, imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_category);

        linkViews();
        getData();
        addEvents();
    }

    private void addEvents() {
        lsvByCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainChiTietSanPham.class);
                intent.putExtra("ProductDetails", sp.get(position));
                startActivity(intent);
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHang.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        sp = new ArrayList<>();
        int MaDM = 0;
        LoaiSanPham loaiSanPham = (LoaiSanPham) getIntent().getSerializableExtra("ProductByCategory");
        MaDM = loaiSanPham.getMaDM();
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

            if(sp.size() != 0)
            {
                txtNoti.setVisibility(View.INVISIBLE);
            }

            adapter = new adapterSpTheoDanhMuc(ProductByCategory.this, R.layout.activity_product_by_category, sp);
            lsvByCategory.setAdapter(adapter);

            txtCategory.setText(loaiSanPham.getTenDM());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void linkViews() {
        lsvByCategory = findViewById(R.id.lsvByCategory);

        txtCategory = findViewById(R.id.txtCategory);
        imvBack = findViewById(R.id.imvBack);
        imvCart = findViewById(R.id.imvCart);
        txtNoti = findViewById(R.id.txtNoti);
    }
}
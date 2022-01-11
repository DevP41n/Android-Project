package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.adapterTimKiemSanPham;
import com.example.model.LoaiSanPham;
import com.example.model.SanPham;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchProduct extends AppCompatActivity {

    TextView txtResult, txtNoti;
    ListView lsvSearch;

    ImageView imvBack, imvCart;

    adapterTimKiemSanPham adapterTimKiemSanPham;
    ArrayList<SanPham> arraylist;

    Connection connect;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        linkViews();
        loadListSearch();
        addEvents();
    }

    private void addEvents() {
        lsvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainChiTietSanPham.class);
                intent.putExtra("ProductDetails", arraylist.get(position));
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

    private void loadListSearch() {
        arraylist = new ArrayList<>();
        String key = getIntent().getStringExtra("KeySearch");
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect!= null)
            {
                String query ="select * from SanPham where TenSP LIKE '%" + key + "%'" ;
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);

                while (rs.next()){
//                    textt.setText(rs.getString(2));
                    arraylist.add(new SanPham(rs.getInt(1) , rs.getString(2), rs.getDouble(3)
                            , rs.getString(4), rs.getString(5), rs.getString(6)
                            ,rs.getInt(7),rs.getInt(8), rs.getInt(9)));
                }
            }
            adapterTimKiemSanPham = new adapterTimKiemSanPham(SearchProduct.this, R.layout.activity_product_by_category, arraylist);
            lsvSearch.setAdapter(adapterTimKiemSanPham);
            if(arraylist.size() != 0)
            {
                txtNoti.setVisibility(View.INVISIBLE);
            }

            txtResult.setText("Kết tìm kiếm với từ khóa: '" + key + "'");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void linkViews() {
        txtResult = findViewById(R.id.txtResult);
        lsvSearch = findViewById(R.id.lsvSearch);
        imvCart = findViewById(R.id.imvCart);
        imvBack = findViewById(R.id.imvBack);
        txtNoti = findViewById(R.id.txtNoti);
    }
}
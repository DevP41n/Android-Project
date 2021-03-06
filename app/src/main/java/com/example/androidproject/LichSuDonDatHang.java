package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.SharedPreferences.SessionManager;
import com.example.adapter.adapterLichSuDonDatHang;
import com.example.model.DonDatHang;
import com.example.model.TranHisDetailsModel;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LichSuDonDatHang extends AppCompatActivity {

    ListView lsvDonDatHang;
    ArrayList<DonDatHang> arrayList;
    adapterLichSuDonDatHang adapterLichSuDonDatHang;

    ImageView imvBack;

    //Connect to sql server
    Connection connect;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_dat_hang);

        linkViews();
        LoadData();
        addEvents();
    }

    private void addEvents() {
        lsvDonDatHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TranHisDetails.class);
                intent.putExtra("MADH", arrayList.get(position));
                startActivity(intent);
            }
        });
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void LoadData() {
        arrayList = new ArrayList<>();
        SessionManager sessionManager = new SessionManager(LichSuDonDatHang.this);
        int userID = sessionManager.getSession();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect!= null)
            {
                String query ="select * from DonDatHang where MaKH = " + userID;
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);

                while (rs.next()){
//                    textt.setText(rs.getString(2));
                    arrayList.add(new DonDatHang(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getDate(4),rs.getTime(4), rs.getInt(6), rs.getString(7), rs.getString(8),rs.getString(9), rs.getString(10)));
                }
            }
            adapterLichSuDonDatHang = new adapterLichSuDonDatHang(LichSuDonDatHang.this, arrayList);
            lsvDonDatHang.setAdapter(adapterLichSuDonDatHang);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void linkViews() {
        lsvDonDatHang = findViewById(R.id.lsvDonDatHang);
        imvBack = findViewById(R.id.imvBack);
    }
}
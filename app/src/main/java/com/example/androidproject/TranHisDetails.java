package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TranHisDetails extends AppCompatActivity {

    TextView txtTen, txtSDT, txtDiaChi, txtEmail, txtTongTien;

    ListView lvTranHDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran_his_details);

        linkViews();
        loadData();
    }

    private void loadData() {
    }

    private void linkViews() {
        txtTen = findViewById(R.id.txtTen);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtEmail = findViewById(R.id.txtEmail);
        txtSDT = findViewById(R.id.txtSDT);
        txtTongTien = findViewById(R.id.txtTongTien);

        lvTranHDetails = findViewById(R.id.lvTranHDetails);
    }
}
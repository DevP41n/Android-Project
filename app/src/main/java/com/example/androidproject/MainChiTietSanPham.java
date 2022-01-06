package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class MainChiTietSanPham extends AppCompatActivity {

    ImageView imvchitietSP;
    TextView txttenSP, txtgiaSP, txtchitietSP;
    Button btnAddCart;

    ElegantNumberButton enbSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_san_pham);
        linkViews();
        getData();

        addEvents();
    }

    private void addEvents() {
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = "";

                test = enbSoLuong.getNumber();
                txtgiaSP.setText(test);
            }
        });
    }

    private void getData() {
        String tenSP = "";
        String hinhanh ="";
        double gia = 0;
        String chitiet = "";
        int id = 0;
        int soluong =0;

        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("ProductDetails");
        id = sanPham.getMaSP();
        tenSP = sanPham.getTenSP();
        hinhanh = sanPham.getAnh();
        gia = sanPham.getGia();
        chitiet = sanPham.getChiTiet();
        soluong = sanPham.getSoluongton();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgiaSP.setText("Giá: " + decimalFormat.format(gia)+ " VNĐ");
        txttenSP.setText(tenSP);
        txtchitietSP.setText(chitiet);
        Picasso.get().load(hinhanh).into(imvchitietSP);
        enbSoLuong.setRange(1,soluong);

    }

    private void linkViews() {
        imvchitietSP = findViewById(R.id.imvchitietSP);
        txttenSP = findViewById(R.id.txttenSP);
        txtgiaSP = findViewById(R.id.txtgiaSP);
        txtchitietSP = findViewById(R.id.txtchitietSP);
        btnAddCart = findViewById(R.id.btnAddCart);

        enbSoLuong = findViewById(R.id.enbSoLuong);
    }

}
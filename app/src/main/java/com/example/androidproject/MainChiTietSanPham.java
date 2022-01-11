package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.SharedPreferences.SessionCart;
import com.example.model.Cart;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainChiTietSanPham extends AppCompatActivity {

    ImageView imvchitietSP, imvBack, imvCart;
    TextView txttenSP, txtgiaSP, txtchitietSP;
    Button btnAddCart;


    ElegantNumberButton enbSoLuong;

    String tenSP = "";
    String hinhanh ="";
    double gia = 0;
    String chitiet = "";
    int id = 0;
    int soluong =0;
    int soluongmua;
    double tongtien;

    Cart cart;

    private List<Cart> cartList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_san_pham);
        linkViews();
        getData();

        addEvents();
    }

    private void addEvents() {
        cartList = SessionCart.readListFromPref(this);
        if(cartList == null)
            cartList = new ArrayList<>();

        String check = btnAddCart.getText().toString();
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check.equals("HẾT HÀNG")) {
                    soluongmua = Integer.parseInt((enbSoLuong.getNumber()));
                    boolean check = false;
                    for (int i = 0; i < cartList.size(); i++) {
                        if (cartList.get(i).getID() == id) {
                            check = true;
                            cartList.get(i).setSoLuong(cartList.get(i).getSoLuong() + soluongmua);
                            SessionCart.WriteListInPref(getApplicationContext(), cartList);
                            Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (check == false) {
//                tongtien = soluongmua * gia;
                        cart = new Cart(id, tenSP, soluongmua, soluong, gia, hinhanh);
                        cartList.add(cart);
                        SessionCart.WriteListInPref(getApplicationContext(), cartList);
                        Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Sản phẩm đã hết hàng!",Toast.LENGTH_SHORT).show();
                }
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

        if(soluong == 0)
        {
            enbSoLuong.setVisibility(View.INVISIBLE);
            btnAddCart.setText("HẾT HÀNG");
        }

    }

    private void linkViews() {
        imvchitietSP = findViewById(R.id.imvchitietSP);
        txttenSP = findViewById(R.id.txttenSP);
        txtgiaSP = findViewById(R.id.txtgiaSP);
        txtchitietSP = findViewById(R.id.txtchitietSP);
        btnAddCart = findViewById(R.id.btnAddCart);

        imvCart = findViewById(R.id.imvCart);
        imvBack = findViewById(R.id.imvBack);

        enbSoLuong = findViewById(R.id.enbSoLuong);
    }

}
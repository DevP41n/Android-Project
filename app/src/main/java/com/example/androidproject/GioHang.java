package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import com.example.SharedPreferences.SessionCart;
import com.example.SharedPreferences.SessionManager;
import com.example.adapter.adapterCart;
import com.example.model.Cart;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GioHang extends AppCompatActivity {

    ListView lvCart;
    TextView txtTotalPrice, txtNotifi;

    Button btnContinue, btnPayment;

    adapterCart adapterCart;
    private List<Cart> cartList;

    Connection connect;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        linkViews();
        loadData();
        addEvents();
    }

    private void addEvents() {
        SessionManager sessionManager = new SessionManager(GioHang.this);
        int userID = sessionManager.getSession();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        //update product
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(userID != -1) {
                    intent = new Intent(getApplicationContext(), Checkout.class);
                }
                else {
                    intent = new Intent(getApplicationContext(), MainDangNhap.class);
                    Toast.makeText(getApplicationContext(), "Bạn chưa đăng nhập!", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });
    }

    public void Minus(int i)
    {
        cartList.get(i).setSoLuong(cartList.get(i).getSoLuong() - 1);
        SessionCart.WriteListInPref(getApplicationContext(), cartList);
        loadData();
    }

    public void Plus(int i)
    {
        cartList.get(i).setSoLuong(cartList.get(i).getSoLuong() + 1);
        SessionCart.WriteListInPref(getApplicationContext(), cartList);
        loadData();
    }

    public void OpenDeleteDialog(int i)
    {
        Button btnOK, btnCancel;
        Dialog dialog = new Dialog(GioHang.this);
        dialog.setContentView(R.layout.custom_delete_dialog);

        btnOK = dialog.findViewById(R.id.btnOK);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.remove(i);
                SessionCart.WriteListInPref(getApplicationContext(), cartList);
                dialog.dismiss();
                loadData();
                Toast.makeText(getApplicationContext(),"Đã xóa khỏi giỏ hàng!",Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void loadData() {
        double Tongtien = 0;
        cartList = SessionCart.readListFromPref(this);
        //check cartList : Nếu null thì k cho load ở trong if (lỗi)
        if (cartList != null)
        {
            adapterCart = new adapterCart(GioHang.this, R.layout.activity_gio_hang, (ArrayList<Cart>) cartList);
            if (cartList.size() > 0) {
                txtNotifi.setText("");
            } else {
                txtNotifi.setText("Giỏ hàng trống");
            }
            lvCart.setAdapter(adapterCart);

            for (int i = 0; i < cartList.size(); i++) {
                Tongtien += (cartList.get(i).getTien() * cartList.get(i).getSoLuong());
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            txtTotalPrice.setText(decimalFormat.format(Tongtien) + " VNĐ");
        }
    }

    private void linkViews() {
        lvCart = findViewById(R.id.lvCart);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtNotifi = findViewById(R.id.txtNotifi);
        btnContinue = findViewById(R.id.btnContinue);
        btnPayment = findViewById(R.id.btnPayment);
    }
}
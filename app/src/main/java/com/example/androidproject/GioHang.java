package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import com.example.SharedPreferences.SessionCart;
import com.example.adapter.adapterCart;
import com.example.model.Cart;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHang extends AppCompatActivity {

    ListView lvCart;
    TextView txtTotalPrice, txtNotifi;

    adapterCart adapterCart;
    private List<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        linkViews();
        loadData();
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
            txtTotalPrice.setText(decimalFormat.format(Tongtien));
        }
    }

    private void linkViews() {
        lvCart = findViewById(R.id.lvCart);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtNotifi = findViewById(R.id.txtNotifi);
    }
}
package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.SharedPreferences.SessionCart;
import com.example.SharedPreferences.SessionManager;
import com.example.model.Cart;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Checkout extends AppCompatActivity {

    EditText edtName, edtEmail, edtAdress, edtPhone;

    Button btnCheckout, btnBack;
    ImageView imvBack;

    Connection connect;
    String connectionResult = "";

    private List<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        cartList = SessionCart.readListFromPref(this);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.getDefault());
                String currentDateAndTime = dateFormat.format(new Date());

                SessionManager sessionManager = new SessionManager(Checkout.this);
                int userID = sessionManager.getSession();
                String TenKH = edtName.getText().toString();
                String DiachiKH = edtAdress.getText().toString();
                String Email = edtEmail.getText().toString();
                String SDT = edtPhone.getText().toString();

                int MaDH = 0;
                int MaSP = 0;
                int Soluongmua = 0;
                double Dongiamua = 0;
                double tongtien = 0;
                int updateQuantity = 0;

                try{
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionClass();
                    if(connect!= null) {
                        String query = "INSERT INTO DonDatHang (Dathanhtoan, Tinhtranggiaohang, Ngaydat, MaKH, TenKH, SDTKH, DiachiKH, Email) " +
                                " VALUES (0, 0, '" + currentDateAndTime + "'," + userID+  " , N'" + TenKH +"', '" + SDT + "', N'" + DiachiKH + "', '"
                                + Email +"')";
                        Statement st = connect.createStatement();
                        st.executeUpdate(query);

                        query = "select * from DonDatHang where Ngaydat = '" + currentDateAndTime + "'"
                                + "and MaKH =" + userID;

                        ResultSet rsc = st.executeQuery(query);
                        if(rsc.next())
                        {
                            MaDH = rsc.getInt(1);
                        }

                        String updateProduct = "";

                        if(cartList !=null) {
                            for (int i = 0; i < cartList.size(); i++) {
                                MaSP = cartList.get(i).getID();
                                Soluongmua = cartList.get(i).getSoLuong();
                                Dongiamua = cartList.get(i).getTien();
                                tongtien = Soluongmua * Dongiamua;
                                updateQuantity = cartList.get(i).getSoluongton() - Soluongmua;
                                query = "INSERT INTO ChiTietDonHang (MaDonHang, MaSP, Soluong, Dongia, TongTien) " +
                                        " VALUES (" + MaDH + ", " + MaSP + ", " + Soluongmua + ", " + Dongiamua + ", " + tongtien + ")";
                                st = connect.createStatement();
                                st.executeUpdate(query);

                                updateProduct = "UPDATE SanPham SET Soluongton = " + updateQuantity + " WHERE MaSP = " + MaSP;
                                st.executeUpdate(updateProduct);

                            }
                            cartList.removeAll(cartList);
                            SessionCart.WriteListInPref(getApplicationContext(), cartList);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Đặt Hàng Thành công!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Lỗi!", Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void linkViews() {
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtAdress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);

        btnBack = findViewById(R.id.btnBack);
        btnCheckout = findViewById(R.id.btnCheckout);
        imvBack = findViewById(R.id.imvBack);
    }
}
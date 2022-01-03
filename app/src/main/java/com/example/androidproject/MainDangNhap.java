package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SharedPreferences.SessionManager;
import com.example.adapter.adapterLoaiSanPham;
import com.example.model.LoaiSanPham;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDangNhap extends AppCompatActivity {

    EditText txtUserName, txtPassword;
    TextView txtSignUp;
    ImageView imvSignIn;

    //Connect to sql server
    Connection connect;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        linkViews();
        addEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        checkSession();
    }

    private void checkSession() {
        SessionManager sessionManager = new SessionManager(MainDangNhap.this);
        int userID = sessionManager.getSession();
        if(userID != -1)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Đã Đăng nhập!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addEvents() {
        imvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUserName.getText().toString();
                String Password = txtPassword.getText().toString();
                SessionManager sessionManager = new SessionManager(MainDangNhap.this);
                try{
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionClass();
                    if(connect!= null)
                    {
                        String query ="select * from KhachHang where Taikhoan = '" + userName + "'"
                                                                 + "and Matkhau ='" + Password +"'";
                        Statement st= connect.createStatement();
                        ResultSet rs= st.executeQuery(query);
                        if(rs.next())
                        {
                            //lưu session khi login thành công
                            sessionManager.saveSession(rs.getInt(1));
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(MainDangNhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }

                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            }
        });


        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainDangKy.class);
                startActivity(intent);
            }
        });
    }


    private void linkViews() {
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        imvSignIn = findViewById(R.id.imvSignIn);
        txtSignUp = findViewById(R.id.txtSignUp);
    }
}
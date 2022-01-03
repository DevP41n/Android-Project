package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDangKy extends AppCompatActivity {

    EditText edtUserName, edtPassword, edtEmail, edtHoTen, edtPhone;

    Button btnSignUp;


    Connection connect;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        linkViews();

        addEvents();
    }

    private void addEvents() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String hoten = edtHoTen.getText().toString();
               String username = edtUserName.getText().toString();
               String password = edtPassword.getText().toString();
               String email = edtEmail.getText().toString();
               String Phone = edtPhone.getText().toString();

                try{
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionClass();
                    if(connect!= null) {
                        String query = "select * from KhachHang where Taikhoan = '" + username + "'"
                                + "or Email ='" + email + "'";
                        Statement st = connect.createStatement();
                        ResultSet rsc = st.executeQuery(query);
                        if(rsc.next())
                        {
                            Toast.makeText(getApplicationContext(),"Toàn khoản hoặc email đã tồn tại!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                           String querry = "INSERT INTO KhachHang (HoTen, Taikhoan, Matkhau, Email, DienthoaiKH) " +
                                    " VALUES ('" + hoten + "', '" + username +"', '" + password + "', '" + email + "', '"
                            + Phone +"')";
                            st = connect.createStatement();
                            st.executeUpdate(querry);
                            Intent intent = new Intent(getApplicationContext(), MainDangNhap.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();


                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void linkViews() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtUserName = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}
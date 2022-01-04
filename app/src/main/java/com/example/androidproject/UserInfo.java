package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.SharedPreferences.SessionManager;
import com.example.model.KhachHang;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class UserInfo extends AppCompatActivity {

    TextView txtHoTen, txtTenTaiKhoan, txtSoDienThoai, txtEmail;

    ArrayList<KhachHang> listKH;

    //Connect to sql server
    Connection connect;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        linkViews();
        loadDatas();
    }

    private void loadDatas() {
        listKH = new ArrayList<>();
        SessionManager sessionManager = new SessionManager(UserInfo.this);
        int userID = sessionManager.getSession();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect!= null)
            {
                String query ="select * from KhachHang WHERE MaKH =" +userID;
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);
                if (rs.next()){
                    txtHoTen.setText(rs.getString(2));
                    txtEmail.setText(rs.getString(5));
                    txtTenTaiKhoan.setText(rs.getString(3));
                    txtSoDienThoai.setText(rs.getString(7));
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void linkViews() {
        txtHoTen = findViewById(R.id.txtHoTen);
        txtTenTaiKhoan = findViewById(R.id.txtTenTaiKhoan);
        txtEmail = findViewById(R.id.txtEmail);
        txtSoDienThoai = findViewById(R.id.txtSoDienThoai);
    }
}
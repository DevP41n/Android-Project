package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.SharedPreferences.SessionManager;
import com.example.model.KhachHang;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        addEvents();
        loadDatas();
    }

    private void addEvents() {
        txtHoTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditUserDialog(1);
            }
        });
        txtSoDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditUserDialog(2);
            }
        });
        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditUserDialog(3);
            }
        });
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

    private void openEditUserDialog(int id) {
        TextView txtTitleDialog;
        EditText edtName;
        Button btnOK, btnCancel;

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_edit_dialog);
        edtName = dialog.findViewById(R.id.edtName);
        txtTitleDialog = dialog.findViewById(R.id.txtTitleDialog);



        SessionManager sessionManager = new SessionManager(UserInfo.this);
        int userID = sessionManager.getSession();
        if( id == 1) {
            txtTitleDialog.setText("Thay đổi tên");
            edtName.setText(txtHoTen.getText().toString());
        }
        if( id == 2) {
            txtTitleDialog.setText("Thay đổi số điện thoại");
            edtName.setText(txtSoDienThoai.getText().toString());
        }
        if( id == 3) {
            txtTitleDialog.setText("Thay đổi Email");
            edtName.setText(txtEmail.getText().toString());
        }
        btnOK = dialog.findViewById(R.id.btnOk);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    try{
                        ConnectionHelper connectionHelper = new ConnectionHelper();
                        connect = connectionHelper.connectionClass();
                        if(connect!= null) {
                            String name = edtName.getText().toString();
                            if (!name.equals("")) {
                                if(id == 1) {
                                    String query = "UPDATE KhachHang SET HoTen = N'" + name + "' WHERE MaKH =" + userID;
                                    Statement st = connect.createStatement();
                                    st.executeUpdate(query);
                                }
                                if(id == 2) {
                                    String query = "UPDATE KhachHang SET DienthoaiKH = N'" + name + "' WHERE MaKH =" + userID;
                                    Statement st = connect.createStatement();
                                    st.executeUpdate(query);
                                }
                                if(id == 3) {
                                    String query = "UPDATE KhachHang SET Email = N'" + name + "' WHERE MaKH =" + userID;
                                    Statement st = connect.createStatement();
                                    st.executeUpdate(query);
                                }
                            }
                            dialog.dismiss();;
                            loadDatas();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }
            }
        });
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
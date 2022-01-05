package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.SharedPreferences.SessionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePassword extends AppCompatActivity {

    EditText edtOldPassword, edtNewPassword, edtRetype;

    Button btnConfirm, btnCancel;

    //Connect to sql server
    Connection connect;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        linkViews();
        addEvents();

    }

    private void addEvents() {
        SessionManager sessionManager = new SessionManager(ChangePassword.this);
        int userID = sessionManager.getSession();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OldPass = edtOldPassword.getText().toString();
                String NewPass = edtNewPassword.getText().toString();
                String Retype = edtRetype.getText().toString();
                String Check = "";

                try{
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionClass();
                    if(connect!= null)
                    {
                        String query = "select * from KhachHang where MaKH = " + userID;
                        Statement st = connect.createStatement();
                        ResultSet rsc = st.executeQuery(query);
                        if (rsc.next()) {
                            Check = rsc.getString(4);
                        }
                        if (!Check.equals(OldPass)) {
                            Toast.makeText(getApplicationContext(), "Sai mật khẩu cũ!", Toast.LENGTH_SHORT).show();
                        }
                        else if(!NewPass.equals(Retype))
                        {
                            Toast.makeText(getApplicationContext(),"Mật khẩu mới không khớp!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String queryy = "UPDATE KhachHang SET Matkhau = N'" + NewPass +"' WHERE MaKH =" + userID;
                            st = connect.createStatement();
                            st.executeUpdate(queryy);
                            finish();
                            Toast.makeText(getApplicationContext(),"Đổi mật khẩu thành công!",Toast.LENGTH_SHORT).show();
                        }
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void linkViews() {
        edtOldPassword = findViewById(R.id.edtOldPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtRetype = findViewById(R.id.edtRetype);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

    }
}
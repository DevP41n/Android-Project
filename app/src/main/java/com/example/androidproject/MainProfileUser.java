package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SharedPreferences.SessionManager;

public class MainProfileUser extends AppCompatActivity {

    TextView txtProfile, txtTransactionHistory, txtLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile_user);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        SessionManager sessionManager = new SessionManager(MainProfileUser.this);
        txtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserInfo.class);
                startActivity(intent);
            }
        });
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Note: Test để check login : Thoát
                    sessionManager.removeSession();
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Đã thoát!", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void linkViews() {
        txtProfile = findViewById(R.id.txtProfile);
        txtTransactionHistory = findViewById(R.id.txtTransactionHistory);
        txtLogout = findViewById(R.id.txtLogout);
    }
}
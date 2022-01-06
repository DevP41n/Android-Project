package com.example.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.model.Cart;

import java.util.ArrayList;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARE_PREF_NAME = "session";
    String SESSION_KEY = "session_user";


    public SessionManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences(SHARE_PREF_NAME,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(int MAKH)
    {
        //lưu session của user khi login
        editor.putInt(SESSION_KEY, MAKH).commit();
    }

    public int getSession()
    {
        //get session user đã login => check
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }

    public void removeSession()
    {
        //logout
        editor.putInt(SESSION_KEY,-1).commit();
//        editor.clear();
//        editor.commit();
    }

}

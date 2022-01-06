package com.example.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.example.model.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SessionCart {

    private static final String CART_KEY = "cart_key";
    public static void WriteListInPref(Context context, List<Cart> list)
    {
        Gson gson = new Gson();
        String jsonString =  gson.toJson(list);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(CART_KEY, jsonString);
        editor.apply();
    }

    public static List<Cart> readListFromPref(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String json = pref.getString(CART_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Cart>>() {}.getType();
        List<Cart> list = gson.fromJson(json, type);
        return list;
    }
}

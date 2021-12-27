package com.example.androidproject;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String username, pass, ip, port, database;

    //Connect SQL Server
    @SuppressLint("NewApi")
    public Connection connectionClass()
    {
        //Copy mấy cái ip của tụi bay bỏ dô đây, vì cái ide này nó k get đc cái ip localhost của pc
        ip = "192.168.5.157"; // IP máy của Tân
//        ip = "192.168.5.38"; // IP máy của Tiến
        database = "TechShop";
        username = "sa";
        pass = "sa";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionUrl = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionUrl= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+username+";password="+pass+";";
            connection = DriverManager.getConnection(connectionUrl);
        }catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}

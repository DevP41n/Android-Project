package com.example.androidproject;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewNew;
    ListView listView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    //Connect to sql server
    Connection connection;
    String connectionResult = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionViewFlipper();

    }


    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStHKxpunWSUMUehOTe0eP0RgQC3Apeyfm59g&usqp=CAU");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9DwlmvWZ6kywSL77GMcHGb2BoIy2IWFoXFQ&usqp=CAU");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6nBd6bszec1rvHGgzqAFTyJHlMQS3SuH2HA&usqp=CAU");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9P4-kKHKpCxpstTnohuIvrlVUARmoNZZi8A&usqp=CAU");
        for(int i =0; i <mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void AnhXa(){
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        recyclerViewNew=findViewById(R.id.recyviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
    }



}
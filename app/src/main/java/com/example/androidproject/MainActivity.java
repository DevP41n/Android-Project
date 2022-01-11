package com.example.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.SharedPreferences.SessionManager;
import com.example.adapter.adapterLoaiSanPham;
import com.example.adapter.adapterSanPham;
import com.example.adapter.adapterTimKiemSanPham;
import com.example.model.LoaiSanPham;
import com.example.model.SanPham;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterFace{
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewNew;
    ListView listView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView imvProfile, imvCart, imvMenu, imvSearch;
    EditText edtSearch;

    ArrayList<SanPham> sp;
    adapterSanPham adapterSanPham;
    ArrayList<LoaiSanPham> loaiSanPham;
    adapterLoaiSanPham adapterLoaiSanPham;
    adapterTimKiemSanPham adapterTimKiemSanPham;

    //Connect to sql server
    Connection connect;
    String connectionResult = "";

    private static int check = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionViewFlipper();
        loadCategory();
        LoadData();
        ProductByCategory();
        AddEvents();
    }

    @Override
    protected void onResume() {
        LoadData();
        loadCategory();
        super.onResume();
    }

    private void AddEvents() {
        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy session
                SessionManager sessionManager = new SessionManager(MainActivity.this);
                int userID = sessionManager.getSession();
                if(userID != -1)
                {
                    //Note: Test để check login : Thoát
//                    sessionManager.removeSession();
//                    Toast.makeText(getApplicationContext(), "Đã thoát!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainProfileUser.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), MainDangNhap.class);
                    startActivity(intent);
                }
            }
        });

        imvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHang.class);
                startActivity(intent);
            }
        });
        imvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check == 1){
                    drawerLayout.openDrawer(GravityCompat.START);
                    check = 2;
                }
                else if(check == 2 ){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    check = 1;
                }
            }
        });

        imvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = edtSearch.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SearchProduct.class);
                intent.putExtra("KeySearch", key);
                startActivity(intent);
            }
        });
    }

    private void ProductByCategory() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductByCategory.class);
                intent.putExtra("ProductByCategory", loaiSanPham.get(position));
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                check = 1;
            }
        });
    }


    private void loadCategory() {

        loaiSanPham = new ArrayList<>();
//        TextView textt;
//        textt = findViewById(R.id.textt);
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect!= null)
            {
                String query ="select * from DanhMuc";
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);

                while (rs.next()){
//                    textt.setText(rs.getString(2));
                    loaiSanPham.add(new LoaiSanPham( rs.getInt(1),rs.getString(2),rs.getInt(3)));
                }
            }
            adapterLoaiSanPham = new adapterLoaiSanPham(MainActivity.this,R.layout.layout_toolbar,loaiSanPham);
            listView.setAdapter(adapterLoaiSanPham);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    private void ActionBar() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//    }

    private void LoadData() {

        sp = new ArrayList<>();
//        TextView textt;
//        textt = findViewById(R.id.textt);
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect!= null)
            {
                String query ="SELECT TOP 8 * FROM SanPham ORDER BY MaSP DESC";
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);

                while (rs.next()){
//                    textt.setText(rs.getString(2));
                    sp.add(new SanPham(rs.getInt(1) , rs.getString(2), rs.getDouble(3)
                            , rs.getString(4), rs.getString(5), rs.getString(6)
                            ,rs.getInt(7),rs.getInt(8), rs.getInt(9)));
                }
            }
            adapterSanPham = new adapterSanPham(MainActivity.this, sp,this);
            recyclerViewNew.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerViewNew.setLayoutManager(layoutManager);
            //
            DividerItemDecoration decoration = new DividerItemDecoration(this, layoutManager.getOrientation());
            recyclerViewNew.addItemDecoration(decoration);
            recyclerViewNew.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            recyclerViewNew.setAdapter(adapterSanPham);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://file.hstatic.net/1000026716/collection/1371x436_1ded6464f79a4ec9adff306657b34423.jpg");
        mangquangcao.add("https://theme.hstatic.net/1000026716/1000440777/14/slideshow_7.jpg?v=23390");
        mangquangcao.add("https://theme.hstatic.net/1000026716/1000440777/14/slideshow_3.jpg?v=23390");
        mangquangcao.add("https://theme.hstatic.net/1000026716/1000440777/14/slideshow_12.jpg?v=23390");
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
        imvMenu = findViewById(R.id.imvMenu);
        viewFlipper = findViewById(R.id.viewflipper);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        recyclerViewNew=findViewById(R.id.rvcviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        imvProfile = findViewById(R.id.imvProfile);
        imvCart = findViewById(R.id.imvCart);
        edtSearch = findViewById(R.id.edtSearch);
        imvSearch = findViewById(R.id.imvSearch);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), MainChiTietSanPham.class);
        intent.putExtra("ProductDetails", sp.get(position));
        startActivity(intent);
    }
}
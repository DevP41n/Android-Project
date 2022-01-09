package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.adapterTranHDetails;
import com.example.model.DonDatHang;
import com.example.model.TranHisDetailsModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TranHisDetails extends AppCompatActivity {

    TextView txtTen, txtSDT, txtDiaChi, txtEmail, txtTongTien;

    ListView lvTranHDetails;
    adapterTranHDetails tranHDetails;
    ArrayList<TranHisDetailsModel> arraytranHis;

    //Connect to sql server
    Connection connect;
    String connectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran_his_details);

        linkViews();
        loadData();
    }

    private void loadData() {
        arraytranHis = new ArrayList<>();
        DonDatHang donDatHang = (DonDatHang) getIntent().getSerializableExtra("MADH");
        String MADH = String.valueOf(donDatHang.getMaDonHang());

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect!= null)
            {
                String query ="select * from ChiTietDonHang where MaDonHang = " + MADH;
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);
                ResultSet rsc;
                String tenSP = "test";
                double tongtien = 0;
                String MaSP = "";
                String queryy="";

                while (rs.next()){

//                    MaSP = String.valueOf(rs.getInt(2));
//                    queryy = "select * from SanPham where MaSP =" + MaSP;
//                    rsc = st.executeQuery(queryy);
//                    if (rsc.next())
//                    {
//                       tenSP = rsc.getString(2);
//                    }
                    tongtien += rs.getDouble(5);
                    arraytranHis.add(new TranHisDetailsModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5), tenSP));
                }

                tranHDetails = new adapterTranHDetails(TranHisDetails.this, R.layout.activity_tran_his_details, arraytranHis);
                lvTranHDetails.setAdapter(tranHDetails);

                query = "select * from DonDatHang where MaDonHang = " + MADH;
                rsc = st.executeQuery(query);
                if(rsc.next())
                {
                    txtTen.setText(rsc.getString(7));
                    txtDiaChi.setText(rsc.getString(9));
                    txtSDT.setText(rsc.getString(8));
                    txtEmail.setText(rsc.getString(10));
                }
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtTongTien.setText("Tổng tiền: " + decimalFormat.format(tongtien) + " VNĐ");
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void linkViews() {
        txtTen = findViewById(R.id.txtTen);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtEmail = findViewById(R.id.txtEmail);
        txtSDT = findViewById(R.id.txtSDT);
        txtTongTien = findViewById(R.id.txtTongTien);

        lvTranHDetails = findViewById(R.id.lvTranHDetails);
    }
}
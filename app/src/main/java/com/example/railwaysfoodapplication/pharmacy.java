package com.example.railwaysfoodapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class pharmacy extends AppCompatActivity {
    Button add2,remove2;
    EditText stid1,hotelid3,hotelname2,item,item6,item7,item8,item9,price,price6,price7,price8,price9;
    SQLiteDatabase db;
    ConstraintLayout c9;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);
        add2=findViewById(R.id.add2);
        c9=findViewById(R.id.c9);
        remove2=findViewById(R.id.remove2);
        c9.setBackgroundResource(R.drawable.foodimage7);
        hotelid3=findViewById(R.id.hotelid3);
        hotelname2=findViewById(R.id.hotelname2);
        item=findViewById(R.id.item);
        item6=findViewById(R.id.item6);
        item7=findViewById(R.id.item7);
        item8=findViewById(R.id.item8);
        item9=findViewById(R.id.item9);
        price=findViewById(R.id.price);
        price6=findViewById(R.id.price6);
        price7=findViewById(R.id.price7);
        price8=findViewById(R.id.price8);
        price9=findViewById(R.id.price9);
        stid1=findViewById(R.id.stationid2);
        db=openOrCreateDatabase("RailwayFood",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE if not exists MedDB(stationid varchar(50),pharamaid int primary key,pharmaname varchar(50) ,item1 char(20),item2 char(20),item3 char(20),item4 char(20),item5 char(20),price1 int,price2 int,price3 int,price4 int,price5 int)");
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stationid=(stid1.getText().toString().trim());
                int hid=Integer.parseInt(hotelid3.getText().toString());
                String hname=hotelname2.getText().toString();
                String i1=item.getText().toString();
                String i2=item6.getText().toString();
                String i3=item7.getText().toString();
                String i4=item8.getText().toString();
                String i5=item9.getText().toString();
                int p1=Integer.parseInt(price6.getText().toString());
                int p2=Integer.parseInt(price.getText().toString());
                int p3=Integer.parseInt(price7.getText().toString());
                int p4=Integer.parseInt(price8.getText().toString());
                int p5=Integer.parseInt(price9.getText().toString());


                Cursor c=db.rawQuery("select * from MedDB where pharamaid=?",new String[]{hid+""});
                if(c.getCount()>0)
                    Toast.makeText(pharmacy.this, "pharmacy already Exists", Toast.LENGTH_SHORT).show();
                else{
                    db.execSQL("insert into MedDB values('"+stationid+"' ,"+hid+",'"+hname+"','"+i1+"','"+i2+"','"+i3+"','"+i4+"','"+i5+"',"+p1+","+p2+","+p3+","+p4+","+p5+" )");
                    Toast.makeText(pharmacy.this, "Congratulations your pharmacy is added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hid=Integer.parseInt(hotelid3.getText().toString());
                db.execSQL("delete from MedDB where pharamaid="+hid);
                Toast.makeText(pharmacy.this, "pharmacy is removed Succesfully", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
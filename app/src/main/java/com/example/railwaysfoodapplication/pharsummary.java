package com.example.railwaysfoodapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class pharsummary extends AppCompatActivity {
    TextView tname,tphone,tnumber,coach,snumber,bili,orderNum;
    Button logout,otherorder,confirmo,refresh;
    SQLiteDatabase db;
    ConstraintLayout c11;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharsummary);
        tname=findViewById(R.id.tname2);
        tphone=findViewById(R.id.tphone2);
        c11=findViewById(R.id.c11);
        c11.setBackgroundResource(R.drawable.foodimage8);
        tnumber=findViewById(R.id.tnm2);
        coach=findViewById(R.id.cnum2);
        snumber=findViewById(R.id.snm2);
        bili=findViewById(R.id.billl2);
        logout=findViewById(R.id.logout2);
        otherorder=findViewById(R.id.otherorder2);
        confirmo=findViewById(R.id.confirmo2);
        orderNum=findViewById(R.id.orderNum2);
        Intent i3=getIntent();
        String nme=i3.getStringExtra("nm1");
        String pnum=i3.getStringExtra("phn1");
        String tnum=i3.getStringExtra("trn1");
        String cnm=i3.getStringExtra("con1");
        String snm=i3.getStringExtra("shn1");
        String biln=i3.getStringExtra("bln1");
        int hotid=i3.getIntExtra("hotelidd1",0);
        String totalam=i3.getStringExtra("bilamm1");
        tname.setText(nme);
        tphone.setText(pnum);
        tnumber.setText(tnum);
        coach.setText(cnm);
        snumber.setText(snm);
        bili.setText(biln);
        db=openOrCreateDatabase("RailwayFood",MODE_PRIVATE,null);

                int randominteger=getRandomInteger(1000000,9999999);
                orderNum.setText(randominteger+"");


        db.execSQL("CREATE TABLE if not exists Billpharma(cname varchar(50),pharmaid int,orderid int,phonenum varchar(10),trainnum varchar(10),coachnum varchar(10),sheatnum varchar(10),bil varchar(200))");
        confirmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ordi=orderNum.getText().toString();
                Cursor c=db.rawQuery("select * from Billpharma where pharmaid=?",new String[]{ordi+""});
                if(c.getCount()>0)
                    Toast.makeText(pharsummary.this, "This OrderID Already Exists", Toast.LENGTH_SHORT).show();
                else {
                    db.execSQL("insert into Billpharma values('" +nme+ "'," + hotid + ",'" + ordi + "','" + pnum + "','" + tnum + "','" + cnm + "','" + snm + "','" +biln + "' )");
                    Toast.makeText(pharsummary.this, "Congratulations Your Order Is Place Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pharsummary.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        otherorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pharsummary.this,Passanger.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });


    }
    private int getRandomInteger(int min,int max){
        return min+(int)(Math.random()*((max-min)+1));
    }

}


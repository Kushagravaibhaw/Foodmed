package com.example.railwaysfoodapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class orderverificationphar extends AppCompatActivity {
    TextView tv6,textView9;
    EditText hid2;
    Button entero,vieworder,sendsms;
    SQLiteDatabase db;
    ConstraintLayout c15;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderverificationphar);
        tv6=findViewById(R.id.textView20);
        hid2=findViewById(R.id.hiddddd2);
        c15=findViewById(R.id.c15);
        c15.setBackgroundResource(R.drawable.foodimage8);
        entero=findViewById(R.id.entero2);
        vieworder=findViewById(R.id.vieworder2);
        textView9=findViewById(R.id.textView21);
        sendsms=findViewById(R.id.sendsms2);
        db=openOrCreateDatabase("RailwayFood", MODE_PRIVATE,null);
        entero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hied=Integer.parseInt(hid2.getText().toString());
                Cursor c=db.rawQuery("select * from MedDB where pharamaid=?",new String[]{hied+""});
                if(c.getCount()==0)
                    Toast.makeText(orderverificationphar.this, "Please Enter a Valid Pharmacie id", Toast.LENGTH_SHORT).show();
                else{
                    c.moveToFirst();
                    String all2="";
                    do{
                        String itm1=c.getString(3);
                        String itm2=c.getString(4);
                        String itm3=c.getString(5);
                        String itm4=c.getString(6);
                        String itm5=c.getString(7);
                        int p1=c.getInt(8);
                        int p2=c.getInt(9);
                        int p3=c.getInt(10);
                        int p4=c.getInt(11);
                        int p5=c.getInt(12);
                        all2=all2+"Available Items Are:\n"+itm1+"\t\t"+p1+"\n"+itm2+"\t\t"+p2+"\n"+itm3+"\t\t"+p3+"\n"+itm4+"\t\t"+p4+"\n"+itm5+"\t\t"+p5+"\n";
                    }while(c.moveToNext());
                    tv6.setText(all2);
                }
            }
        });
        vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hied=Integer.parseInt(hid2.getText().toString());
                Cursor c=db.rawQuery("select * from Billpharma where pharmaid=?",new String[]{hied+""});
                if(c.getCount()==0)
                    Toast.makeText(orderverificationphar.this, "No Order Is Recived Yet", Toast.LENGTH_SHORT).show();
                else{
                    c.moveToFirst();
                    String all3="";
                    do{
                        String nme=c.getString(0);
                        int hotelid=c.getInt(1);
                        String orderid=c.getString(2);
                        String phonenumber=c.getString(3);
                        String trainnumber=c.getString(4);
                        String coachnumber=c.getString(5);
                        String sheatnumber=c.getString(6);
                        String billi=c.getString(7);
                        all3=all3+"PharmacyID:"+hotelid+"\nName:"+nme+"\nOrderID:"+orderid+"\nPhone Number:"+phonenumber+"\nTrain Number:"+trainnumber+"\nCoach Number:"+coachnumber+"\nSheat Number"+sheatnumber+"\nBill:"+billi;
                    }while (c.moveToNext());
                    textView9.setText(all3);
                }
            }
        });
        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hied=Integer.parseInt(hid2.getText().toString());
                Cursor c=db.rawQuery("select * from Billpharma where pharmaid=?",new String[]{hied+""});
                if(c.getCount()==0)
                    Toast.makeText(orderverificationphar.this, "No Order Is Recived Yet", Toast.LENGTH_SHORT).show();
                else{
                    String nme,phonenumber,billi,orderid;
                    c.moveToFirst();
                    String all3="";

                    do{
                        orderid=c.getString(2);
                        phonenumber=c.getString(3);
                    }while (c.moveToNext());
                    SmsManager sms=SmsManager.getDefault();
                    sms.sendTextMessage(phonenumber,null,"Your Order Id:"+orderid+"\nWill be delivered to you within 20 min\nThank You",null,null);
                    Toast.makeText(orderverificationphar.this, "Message is sent Sucessfuly", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}

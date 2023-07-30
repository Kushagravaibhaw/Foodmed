package com.example.railwaysfoodapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class passanger1 extends AppCompatActivity {
    Button searchotel,availablefooditems,q;
    TextView hotelnames,tv;
    SQLiteDatabase db;
    String[] items={"Mysore","Manglore","Udupi","kolkata","Delhi","Punjab","jaipur","hydrabad","Bangalore","Hubbali"};
    AutoCompleteTextView autocompletetext,autocompletetext1;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adaperItemHotels;
    ArrayList<String> addarray=new ArrayList<String>();
    ConstraintLayout passangerl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passanger1);
        q=findViewById(R.id.searchforhotels4);
        searchotel=findViewById(R.id.searchforhotels2);
        hotelnames=findViewById(R.id.presenthotels2);
        autocompletetext=findViewById(R.id.selectstation2);
        autocompletetext1=findViewById(R.id.selecthotel);
        passangerl=findViewById(R.id.l);
        tv=findViewById(R.id.textView12);
        passangerl.setBackgroundResource(R.drawable.foodimage3);
        availablefooditems=findViewById(R.id.availablefooditem2);
        db=openOrCreateDatabase("RailwayFood", MODE_PRIVATE,null);
        adapterItems=new ArrayAdapter<String>(passanger1.this,R.layout.list_phar,items);
        autocompletetext.setAdapter(adapterItems);
        autocompletetext.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                tv.setText(sname);
                Toast.makeText(passanger1.this, ":"+sname, Toast.LENGTH_SHORT).show();
            }
        });
        searchotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hn= tv.getText().toString().trim();
                Cursor c=db.rawQuery("select * from MedDB where stationid=?",new String[]{hn+""});
                if(c.getCount()==0)
                    Toast.makeText(passanger1.this, "No Data Avialable", Toast.LENGTH_SHORT).show();
                else {
                    c.moveToFirst();
                    String all = "";
                    do {
                        int hid=c.getInt(1);
                        String hname=c.getString(2);
                        all=all+hid+"\t\t"+hname+"\n";
                        addarray.add(hname);
                    } while (c.moveToNext());
                    hotelnames.setText("pharmacies on this Station are:"+all);
                }

            }
        });
        adaperItemHotels=new ArrayAdapter<String>(passanger1.this,R.layout.list_phar,addarray);
        autocompletetext1.setAdapter(adaperItemHotels);
        autocompletetext1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sname=adapterView.getItemAtPosition(i).toString();
                tv.setText(sname);
                Toast.makeText(passanger1.this, ""+sname, Toast.LENGTH_SHORT).show();
            }
        });
        availablefooditems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hn=tv.getText().toString();
                db.execSQL("create table if not exists ItemPrice (itemname varchar(50),price int)");
                Cursor c=db.rawQuery("select * from MedDB where pharmaname=?",new String[]{hn+""});
                if(c.getCount()==0)
                    Toast.makeText(passanger1.this, "Please Enter a valid ID", Toast.LENGTH_SHORT).show();
                else{
                    c.moveToFirst();
                    String all="";
                    int hid;
                    do{
                        hid=c.getInt(1);
                        String itm1=c.getString(3);
                        int p1=c.getInt(8);
                        db.execSQL("insert into ItemPrice values('"+itm1+"',"+p1+")");
                        String itm2=c.getString(4);
                        int p2=c.getInt(9);
                        db.execSQL("insert into ItemPrice values('"+itm2+"',"+p2+")");
                        String itm3=c.getString(5);
                        int p3=c.getInt(10);
                        db.execSQL("insert into ItemPrice values('"+itm3+"',"+p3+")");
                        String itm4=c.getString(6);
                        int p4=c.getInt(11);
                        db.execSQL("insert into ItemPrice values('"+itm4+"',"+p4+")");
                        String itm5=c.getString(7);
                        int p5=c.getInt(12);
                        db.execSQL("insert into ItemPrice values('"+itm5+"',"+p5+")");

                        all=all+"Available Items Are:\nItem Name\t\t\t\tCost\n"+itm1+"\t\t\t\t"+p1+"\n"+itm2+"\t\t\t\t"+p2+"\n"+itm3+"\t\t\t\t"+p3+"\n"+itm4+"\t\t\t\t"+p4+"\n"+itm5+"\t\t\t\t"+p5+"\n";
                    }while(c.moveToNext());
                    Intent i=new Intent(passanger1.this,pharmacyorder.class);
                    i.putExtra("food1",all);
                    i.putExtra("HotelID1",hid);
                    startActivityForResult(i,1);
                }
            }
        });
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="));
                startActivity(i);
            }
        });
    }
}

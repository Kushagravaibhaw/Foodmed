package com.example.railwaysfoodapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class passengerpharambill extends AppCompatActivity {
    TextView tv1;
    EditText name,phonenumber,trainnumber,coachnumber,sheatnumber;
    Button confirmorder;
    ConstraintLayout c12;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengerpharambill);
        tv1=findViewById(R.id.tv9);
        Intent i4=getIntent();
        String bil= i4.getStringExtra("Bill1");
        int hied=i4.getIntExtra("hotid1",0);
        tv1.setText(bil+"\n Pharmacy Id :"+hied);
        String bilam=i4.getStringExtra("bilamount1");
        name=findViewById(R.id.name2);
        phonenumber=findViewById(R.id.phone2);
        trainnumber=findViewById(R.id.trainnumber2);
        c12=findViewById(R.id.c12);
        c12.setBackgroundResource(R.drawable.foodimage8);
        coachnumber=findViewById(R.id.coachnumber2);
        sheatnumber=findViewById(R.id.sheatnumber2);
        confirmorder=findViewById(R.id.confirmorder2);
        confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String ph=phonenumber.getText().toString();
                String tn=trainnumber.getText().toString();
                String cn=coachnumber.getText().toString();
                String sn=sheatnumber.getText().toString();
                String bile=tv1.getText().toString();
                Intent i3=new Intent(passengerpharambill.this,pharsummary.class);
                i3.putExtra("nm1",n);
                i3.putExtra("phn1",ph);
                i3.putExtra("trn1",tn);
                i3.putExtra("con1",cn);
                i3.putExtra("shn1",sn);
                i3.putExtra("bln1",bile);
                i3.putExtra("bilamm1",bilam);
                i3.putExtra("hotelidd1",hied);
                startActivityForResult(i3,4);
            }
        });

    }
}

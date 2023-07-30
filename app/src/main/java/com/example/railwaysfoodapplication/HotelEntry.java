package com.example.railwaysfoodapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HotelEntry extends AppCompatActivity {
    Button proceed,orders;
    ConstraintLayout c;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_entry);
        proceed=findViewById(R.id.proceed);
        orders=findViewById(R.id.orders);
        c=findViewById(R.id.c);
        c.setBackgroundResource(R.drawable.foodimage5);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HotelEntry.this,Hotel.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HotelEntry.this,OrderVerification.class));
            }
        });

    }
}
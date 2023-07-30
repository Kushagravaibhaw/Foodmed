package com.example.railwaysfoodapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pharmacyentry extends AppCompatActivity {
    Button  proceed2,orders2;
    ConstraintLayout c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacyentry);
        proceed2=findViewById(R.id.proceed2);
        orders2=findViewById(R.id.orders2);
        c3=findViewById(R.id.c3);
        c3.setBackgroundResource(R.drawable.foodimage5);
        proceed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pharmacyentry.this,pharmacy.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        orders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pharmacyentry.this,orderverificationphar.class));
            }
        });

    }
}

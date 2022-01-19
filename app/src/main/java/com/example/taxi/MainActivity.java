package com.example.taxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        Intent i;
        i = new Intent(this, Orders.class);
        startActivity(i);
    }

    public void onClickCars(View view) {
        Intent i;
        i = new Intent(this, Cars.class);
        startActivity(i);
    }

    public void onClickOrders(View view){
        Intent i;
        i = new Intent(this, thisOrders.class);
        startActivity(i);
    }

    public void onClickBookings(View view){
        Intent i;
        i = new Intent(this, Bookings.class );
        startActivity(i);
    }
}
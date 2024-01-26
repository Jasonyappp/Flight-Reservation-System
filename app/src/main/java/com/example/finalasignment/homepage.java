package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void SendBooking(View view) {
        Intent Booking = new Intent(this, booking_system.class);
        startActivity(Booking);
    }

    public void SendPopular(View view) {
        Intent Popular = new Intent(this, Popular.class);
        startActivity(Popular);
    }

    public void SendPromotion(View view) {
        Intent Promotion = new Intent(this, promotion.class);
        startActivity(Promotion);
    }

    public void logout (View view){
        Intent Logout = new Intent(this, MainActivity.class);
        startActivity(Logout);
    }

    public void Profile (View view){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }
}
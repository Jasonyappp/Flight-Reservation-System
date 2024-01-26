package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLException;

public class Popular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
    }

    public void SendPopular_Image1(View view) {
        Intent Popular_Image1 = new Intent(this, booking_system.class);
        startActivity(Popular_Image1);
    }

    public void SendPopular_Image2(View view) {
        Intent Popular_Image2 = new Intent(this, booking_system.class);
        startActivity(Popular_Image2);
    }
    public void SendPopular_Image3(View view) {
        Intent Popular_Image3 = new Intent(this, booking_system.class);
        startActivity(Popular_Image3);
    }
    public void SendPopular_back(View view) {
        Intent Popular_back = new Intent(this, homepage.class);
        startActivity(Popular_back);
    }

    public void updatePopular(View view) {
        dbmanager dbManager = new dbmanager(this);
        try {
            dbManager.open();
            dbManager.updatePopular("1", "Updated Popular Item 1");
            dbManager.updatePopular("2", "Updated Popular Item 2");
            dbManager.updatePopular("3", "Updated Popular Item 3");
            Toast.makeText(this, "Popular items updated successfully", Toast.LENGTH_SHORT).show();
        } finally {
            dbManager.close();
        }
    }
}
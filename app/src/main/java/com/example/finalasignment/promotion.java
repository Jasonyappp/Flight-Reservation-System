package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class promotion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
    }

    public void SendPromotion_select(View view) {
        Intent Select = new Intent(this, advance_booking.class);
        startActivity(Select);
    }

    public void SendPromotion_select2(View view) {
        Intent Select = new Intent(this, advance_booking.class);
        startActivity(Select);
    }

    public void SendPromotion_back(View view) {
        Intent Promotion_back = new Intent(this, homepage.class);
        startActivity(Promotion_back);
    }

    public void addPromotion(View view) {
        dbmanager dbManager = new dbmanager(this);

        String promotionID = "1";
        String promotionName = "Discount Promotion";
        String discountPercentage = "10%";

        dbManager.open();
        dbManager.insertPromotion(promotionID, promotionName, discountPercentage);
        dbManager.close();
    }
}

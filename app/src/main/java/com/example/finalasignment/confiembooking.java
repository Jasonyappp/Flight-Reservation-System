package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class confiembooking extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confiembooking);

        dbManager = new dbmanager(this);

        TextView getUsername = findViewById(R.id.UserName);
        TextView getFlightClass = findViewById(R.id.flightname);
        TextView getFoodName = findViewById(R.id.mealselection);

        String username = getIntent().getStringExtra("Username");
        getUsername.setText(username);

        dbManager.open();
        Cursor cursor = dbManager.fetchUserBookingDetails();

        if (cursor != null && cursor.moveToFirst()) {
            String flightClass = cursor.getString(cursor.getColumnIndexOrThrow(dbhelper.COLUMN_AIRPORT));
            String foodName = cursor.getString(cursor.getColumnIndexOrThrow(dbhelper.COLUMN_DESTINATION_COUNTRY));

            getFlightClass.setText(flightClass);
            getFoodName.setText(foodName);

            cursor.close();
        }
    }

    public void BackConfirm_Button (View view) {
        Intent BookingSystem_Back = new Intent(this, ticketselection.class);
        startActivity(BookingSystem_Back);
    }

    public void proceedtopayment(View view) {
        Intent proceed_Payment = new Intent(this, paymentpage.class);
        startActivity(proceed_Payment);
    }
}
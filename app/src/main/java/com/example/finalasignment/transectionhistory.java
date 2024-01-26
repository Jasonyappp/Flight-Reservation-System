package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class transectionhistory extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transectionhistory);

        dbManager = new dbmanager(this);
        dbManager.open();

        displayTransactionHistory();
    }

    private void displayTransactionHistory() {
        String username = "your_username_here";

        Cursor transactionCursor = dbManager.fetchTransactionHistory(username);

        if (transactionCursor != null && transactionCursor.moveToFirst()) {
            int phoneNumberIndex = transactionCursor.getColumnIndex(dbhelper.COLUMN_PHONE_NUMBER);
            int airlineIndex = transactionCursor.getColumnIndex(dbhelper.COLUMN_AIRLINE);
            int locationIndex = transactionCursor.getColumnIndex(dbhelper.COLUMN_LOCATION);
            int hotelIndex = transactionCursor.getColumnIndex(dbhelper.COLUMN_HOTEL);
            int flightMealIndex = transactionCursor.getColumnIndex(dbhelper.COLUMN_FLIGHT_MEAL);
            int amountIndex = transactionCursor.getColumnIndex(dbhelper.COLUMN_AMOUNT);

            do {
                String phoneNumber = (phoneNumberIndex != -1) ? transactionCursor.getString(phoneNumberIndex) : "";
                String airline = (airlineIndex != -1) ? transactionCursor.getString(airlineIndex) : "";
                String location = (locationIndex != -1) ? transactionCursor.getString(locationIndex) : "";
                String hotel = (hotelIndex != -1) ? transactionCursor.getString(hotelIndex) : "";
                String flightMeal = (flightMealIndex != -1) ? transactionCursor.getString(flightMealIndex) : "";
                String amount = (amountIndex != -1) ? transactionCursor.getString(amountIndex) : "";

                processTransactionData(phoneNumber, airline, location, hotel, flightMeal, amount);

            } while (transactionCursor.moveToNext());
        } else {
            showToast("No transaction history found for the user.");
        }

        if (transactionCursor != null && !transactionCursor.isClosed()) {
            transactionCursor.close();
        }
    }

    private void processTransactionData(String phoneNumber, String airline, String location, String hotel, String flightMeal, String amount) {

        Log.d("TransactionHistory", "Phone Number: " + phoneNumber + ", Airline: " + airline +
                ", Location: " + location + ", Hotel: " + hotel + ", Flight Meal: " + flightMeal +
                ", Amount: " + amount);
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void go_to_home(View view) {
        Intent go_to_Home = new Intent(this, homepage.class);
        startActivity(go_to_Home);
    }

    public void go_to_payment_method(View view) {
        Intent go_paymentmethod = new Intent(this, paymentpage.class);
        startActivity(go_paymentmethod);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbManager != null) {
            dbManager.close();
        }
    }
}
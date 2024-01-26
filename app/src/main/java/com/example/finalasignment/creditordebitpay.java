package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class creditordebitpay extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView cardNumTextView;
    private TextView amountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditordebitpay);

        usernameTextView = findViewById(R.id.username);
        cardNumTextView = findViewById(R.id.cardNum);
        amountTextView = findViewById(R.id.amount_payment);

        String username = getIntent().getStringExtra("username");

        displayUserDetails(username);
    }

    private void displayUserDetails(String username) {
        dbmanager dbManager = new dbmanager(this);

        try {
            dbManager.open();
            Cursor cursor = dbManager.fetchUserDetails(username);

            if (cursor != null && cursor.moveToFirst()) {
                int cardNumberIndex = cursor.getColumnIndex(dbhelper.COLUMN_CARD_NUMBER);
                int totalAmountIndex = cursor.getColumnIndex(dbhelper.COLUMN_PAYMENT_AMOUNT);

                if (cardNumberIndex >= 0 && totalAmountIndex >= 0) {
                    String cardNumber = cursor.getString(cardNumberIndex);
                    String totalAmount = cursor.getString(totalAmountIndex);

                    usernameTextView.setText(username);
                    cardNumTextView.setText(cardNumber);
                    amountTextView.setText(totalAmount);
                } else {
                    Log.e("ColumnNotFound", "One or both columns not found in the cursor result set.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }
    }
}
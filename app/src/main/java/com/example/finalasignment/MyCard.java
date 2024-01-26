package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyCard extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);

        dbManager = new dbmanager(this).open();
        displayCardInfo();
    }

    private void displayCardInfo() {
        Cursor cursor = dbManager.fetchAddCard();

        if (cursor != null && cursor.moveToFirst()) {
            int cardNumberIndex = cursor.getColumnIndex(dbhelper.COLUMN_CARD_NUMBER);
            int expiryDateIndex = cursor.getColumnIndex(dbhelper.COLUMN_EXPIRY_DATE);

            if (cardNumberIndex != -1 && expiryDateIndex != -1) {
                do {
                    String cardNumber = cursor.getString(cardNumberIndex);
                    String expiryDate = cursor.getString(expiryDateIndex);

                    TextView cardTextView = new TextView(this);
                    cardTextView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    cardTextView.setText(String.format("Card Number: %s\nExpiry Date: %s", cardNumber, expiryDate));

                    LinearLayout cardLayout = findViewById(R.id.cardLayout);
                    cardLayout.addView(cardTextView);
                } while (cursor.moveToNext());

                cursor.close();
            } else {
                Toast.makeText(this, "Card information columns not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No card information available", Toast.LENGTH_SHORT).show();
        }
    }


    public void SendMyCard_Back(View view) {
        Intent Profile_1 = new Intent(this, Profile.class);
        startActivity(Profile_1);
    }

    public void SendMyCard_AddCard(View view) {
        Intent AddCard = new Intent(this, AddCard.class);
        startActivity(AddCard);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
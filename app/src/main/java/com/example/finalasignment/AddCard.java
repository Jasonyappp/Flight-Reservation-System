package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCard extends AppCompatActivity {

    private dbmanager dbManager;
    EditText card_Num;
    EditText expiry_Date;
    EditText cvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        card_Num = findViewById(R.id.editTextNumber);
        expiry_Date = findViewById(R.id.editTextNumber3);
        cvv = findViewById(R.id.editTextNumber2);
    }

    public void SendAddCard_Save(View view) {
        String cardNum = card_Num.getText().toString();
        String expiryDate = expiry_Date.getText().toString();
        String cvvValue = cvv.getText().toString();

        if (TextUtils.isEmpty(cardNum) || TextUtils.isEmpty(expiryDate) || TextUtils.isEmpty(cvvValue)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        dbManager = new dbmanager(this);
        try {
        dbManager.open();
        dbManager.insertAddCard(cardNum, expiryDate, cvvValue);
        dbManager.close();

        Intent MyCard = new Intent(this, MyCard.class);
        MyCard.putExtra("Card Num", cardNum);
        MyCard.putExtra("Date", expiryDate);
        MyCard.putExtra("CVV", cvvValue);
        startActivity(MyCard);
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
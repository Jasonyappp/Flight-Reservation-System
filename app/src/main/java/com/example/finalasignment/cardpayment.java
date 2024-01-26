package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class  cardpayment extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardpayment);

        dbManager = new dbmanager(this);
        dbManager.open();

        TextView username_profile = findViewById(R.id.username_id);
        String Username = getIntent().getStringExtra("Username");
        username_profile.setText(Username);

        TextView card_Num = findViewById(R.id.card_Number);
        String card_NUM = getIntent().getStringExtra("Card Number");
        card_Num.setText(card_NUM);

        TextView Total_Amount = findViewById(R.id.total_amount);
        String Amount = getIntent().getStringExtra("Amount");
        Total_Amount.setText(Amount);
    }

    public void proceed_To_payment (View view){
        boolean isPaymentSuccessful = checkPaymentStatus();

        if (isPaymentSuccessful) {
            savePaymentDetails();
            Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
            Intent paymentSuccessIntent = new Intent(this, paymentsuccess.class);
            startActivity(paymentSuccessIntent);
        } else {
            Toast.makeText(this, "Payment Unsuccessful", Toast.LENGTH_SHORT).show();
            Intent paymentUnsuccessfulIntent = new Intent(this, payment_unsuccessful.class);
            startActivity(paymentUnsuccessfulIntent);
        }
    }

    private void savePaymentDetails() {
        String username = getIntent().getStringExtra("Username");
        String cardNumber = getIntent().getStringExtra("Card Number");
        String totalAmount = getIntent().getStringExtra("Amount");

        String paymentId = "PAY" + System.currentTimeMillis();

        dbManager.insertCardPayment(paymentId, username, cardNumber, totalAmount);
    }

    private boolean checkPaymentStatus() {
        String cardNumber = getIntent().getStringExtra("Card Number");

        if (cardNumber != null && cardNumber.length() > 0) {
            int lastDigit = Integer.parseInt(cardNumber.substring(cardNumber.length() - 1));
            return lastDigit % 2 == 0;
        }
        return false;
    }

    public void back_payment_method4 (View view){
        Intent Back = new Intent(this, paymentpage.class);
        startActivity(Back);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
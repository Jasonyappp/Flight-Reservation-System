package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class cashpayment extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashpayment);

        TextView totalAmountDetails = findViewById(R.id.Amount_Details);
        String amount_details = getIntent().getStringExtra("Amount");
        totalAmountDetails.setText(amount_details);

        dbManager = new dbmanager(this);
        dbManager.open();
    }

    public void proceed_pay (View view){
        String paymentID = "your_generated_id";
        String paymentMethod = "Cash";
        String amount = getIntent().getStringExtra("Amount");

        dbManager.insertPaymentMethod(paymentID, paymentMethod, amount);
        toastmake("Cash payment successful");

        Intent gotosuccess = new Intent(this, paymentsuccess.class);
        startActivity(gotosuccess);
    }

    private void toastmake(String cashPaymentSuccessful) {
    }

    public void back_to_payment_method (View view){
        dbManager.close();

        Intent backtopayment = new Intent(this, paymentpage.class);
        startActivity(backtopayment);
    }
}
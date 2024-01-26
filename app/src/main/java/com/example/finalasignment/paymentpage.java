package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class paymentpage extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentpage);

        dbManager = new dbmanager(this);
        dbManager.open();
    }

    public void cash_Payment (View view){
        Intent cash_Butt = new Intent(this, cashpayment.class);
        startActivity(cash_Butt);
        performCashPaymentOperations();
        Toast.makeText(this, "Cash Payment Successful", Toast.LENGTH_SHORT).show();
    }
    public void card_Payment (View view){
        Intent card_Butt = new Intent(this, cardpayment.class);
        startActivity(card_Butt);
        performCardPaymentOperations();
        Toast.makeText(this, "Card Payment Successful", Toast.LENGTH_SHORT).show();
    }

    public void back_to_confirm (View view){
        Intent back_to_Confirm = new Intent(this, confiembooking.class);
        startActivity(back_to_Confirm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }

    private void performCashPaymentOperations() {
        dbManager.insertPaymentMethod("1", "Cash", "0");
    }

    private void performCardPaymentOperations() {
        dbManager.insertCardPayment("1", "Username", "CardNumber", "TotalAmount");
    }
}
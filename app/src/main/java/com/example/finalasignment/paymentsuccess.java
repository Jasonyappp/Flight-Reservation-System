package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class paymentsuccess extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentsuccess);

        dbManager = new dbmanager(this);
        dbManager.open();
    }
    public void back_to_home_page (View view){
        Intent to_home_page = new Intent(this, homepage.class);
        startActivity(to_home_page);
    }

    public void go_to_history_page (View view){
        Intent to_history = new Intent(this,History.class);
        startActivity(to_history);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }

    public void handlePaymentSuccess(String paymentMethod, String amount) {
        dbManager.insertPaymentMethod("1", paymentMethod, amount);

        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
    }
}
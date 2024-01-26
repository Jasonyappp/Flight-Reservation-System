package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class payment_unsuccessful extends AppCompatActivity {

    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_unsuccessful);

        dbManager = new dbmanager(this);
        dbManager.open();
        paymentUnsuccessful();
    }

    private void paymentUnsuccessful() {
        Toast.makeText(this, "Payment Unsuccessful", Toast.LENGTH_SHORT).show();
    }

    public void back_to_profile4 (View view){
        Intent backtoprofile4 = new Intent(this, homepage.class);
        startActivity(backtoprofile4);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the dbManager to avoid memory leaks
        dbManager.close();
    }
}
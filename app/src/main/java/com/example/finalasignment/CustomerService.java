package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);

        displayCustomerServiceDetails();
    }

    public void SendCustomerService_Back(View view) {
        Intent CustomerService = new Intent(this, Profile.class);
        startActivity(CustomerService);
    }

    private void displayCustomerServiceDetails() {
        TextView phoneNumberTextView = findViewById(R.id.textView12);
        TextView emailTextView = findViewById(R.id.textView15);

        phoneNumberTextView.setText("06 4510 7845");
        emailTextView.setText("windglider9420@gmail.com");

        Toast.makeText(this, "Customer service details displayed", Toast.LENGTH_SHORT).show();
    }
}
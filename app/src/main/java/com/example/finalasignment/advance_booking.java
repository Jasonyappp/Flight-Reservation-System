package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class advance_booking extends AppCompatActivity {

    private Spinner spinnerFlightType;
    private Spinner spinnerSeatType;
    private CheckBox checkBox;
    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_booking);

        dbManager = new dbmanager(this);

        spinnerFlightType = findViewById(R.id.sppeople);
        spinnerSeatType = findViewById(R.id.spclass);
        checkBox = findViewById(R.id.meal1);

        Button confirmButton = findViewById(R.id.confirm_button);

        populateSpinners();

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                confirmButton.setText("Confirm with Meal");
            } else {
                confirmButton.setText("Confirm without Meal");
            }
        });
    }

    public void confirmButtonClick(View view) {
        String selectedFlightType = spinnerFlightType.getSelectedItem().toString();
        String selectedSeatType = spinnerSeatType.getSelectedItem().toString();

        boolean isMealSelected = checkBox.isChecked();
        handleUserSelection(selectedFlightType, selectedSeatType, isMealSelected);

        Intent intent;

        if (isMealSelected) {
            intent = new Intent(this, ChooseMeal.class);
        } else {
            intent = new Intent(this, ticketselection.class);
        }
        startActivity(intent);
    }

    private void populateSpinners () {
        List<String> flightTypes = new ArrayList<>();
        flightTypes.add("Economy");
        flightTypes.add("Business");
        flightTypes.add("First Class");

        ArrayAdapter<String> flightTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, flightTypes);
        flightTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFlightType.setAdapter(flightTypeAdapter);

        List<String> seatTypes = new ArrayList<>();
        seatTypes.add("Window");
        seatTypes.add("Aisle");
        seatTypes.add("Middle");

        ArrayAdapter<String> seatTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, seatTypes);
        seatTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeatType.setAdapter(seatTypeAdapter);
    }

    private void handleUserSelection(String flightType, String seatType, boolean isMealSelected) {
        try {
            dbManager.open();

            dbManager.insertBooking("123", "Airport", "Destination", "Credit Card", "2024-01-30");
            dbManager.insertSeats("456", "Economy", "123");

            if (isMealSelected) {
                dbManager.insertMeals("Meal1");
            }

            Toast.makeText(getApplicationContext(), "Booked Successful", Toast.LENGTH_SHORT).show();

        } finally {
            dbManager.close();
        }
    }

    public void SendAdvanced_Back (View view){
        Intent Advanced_Back = new Intent(this, booking_system.class);
        startActivity(Advanced_Back);
    }
}

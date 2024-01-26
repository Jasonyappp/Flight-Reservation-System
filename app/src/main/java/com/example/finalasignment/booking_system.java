package com.example.finalasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class booking_system extends AppCompatActivity {

    private Spinner spPeople;
    private Spinner spClass;
    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_system);

        spPeople = findViewById(R.id.sppeople);
        spClass = findViewById(R.id.spclass);

        dbManager = new dbmanager(this);
        dbManager.open();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spPeople.setAdapter(adapter);
        spClass.setAdapter(adapter);

        spPeople.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedPeople = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedClass = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    public void SendBookingSystem_Confirm(View view) {
        String selectedPeople = spPeople.getSelectedItem().toString();
        String selectedClass = spClass.getSelectedItem().toString();

        dbManager.open();
        insertBookingData(selectedPeople, selectedClass);
        dbManager.close();

        Intent BookingSystem_Confirm = new Intent(this, advance_booking.class);
        startActivity(BookingSystem_Confirm);
    }

    private void insertBookingData(String selectedPeople, String selectedClass) {
        String bookingId = "1";
        String airport = "YourAirportValue";
        String destinationCountry = "YourDestinationCountryValue";
        String paymentMethod = "YourPaymentMethodValue";
        String flightDate = "YourFlightDateValue";

        dbManager.insertBooking(bookingId, airport, destinationCountry, paymentMethod, flightDate);
    }

    public void SendBookingSystem_Back(View view) {
        Intent BookingSystem_Back = new Intent(this, homepage.class);
        startActivity(BookingSystem_Back);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }

}